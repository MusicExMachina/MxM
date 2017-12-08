from __future__ import print_function, division
import tensorflow as tf
import numpy as np


def randomize(dataset, labels):
	permutation = np.random.permutation(labels.shape[0])
	shuffled_dataset = dataset[permutation, :, :]
	shuffled_labels = labels[permutation]
	return shuffled_dataset, shuffled_labels


def one_hot_encode(np_array):
	return (np.arange(10) == np_array[:, None]).astype(np.float32)


def reformat_data(dataset, labels, image_width, image_height, image_depth):
	np_dataset_ = np.array(
		[np.array(image_data).reshape(image_width, image_height, image_depth) for image_data in dataset])
	np_labels_ = one_hot_encode(np.array(labels, dtype=np.float32))
	np_dataset, np_labels = randomize(np_dataset_, np_labels_)
	return np_dataset, np_labels


def flatten_tf_array(array):
	shape = array.get_shape().as_list()
	return tf.reshape(array, [shape[0], shape[1] * shape[2] * shape[3]])


def accuracy(predictions, labels):
	return 100.0 * np.sum(np.argmax(predictions, 1) == np.argmax(labels, 1)) / predictions.shape[0]


def train_1d_cnn(train_xs, train_ys, test_xs, test_ys):
	num_steps = 10001
	disp_step = 1000
	eta = 0.5

	graph = tf.Graph()
	with graph.as_default():
		# 1) First we put the input data in a tensorflow friendly form.
		train_xs_r = tf.placeholder(tf.float32, shape=(batch_size, image_width, image_height, image_depth))
		train_ys_r = tf.placeholder(tf.float32, shape=(batch_size, num_labels))
		test_xs_r = tf.constant(test_xs, tf.float32)

		# 2) Then, the weight matrices and bias vectors are initialized
		# as a default, tf.truncated_normal() is used for the weight matrix and tf.zeros() is used for the bias vector.
		weights = tf.Variable(tf.truncated_normal([image_width * image_height * image_depth, num_labels]), tf.float32)
		bias = tf.Variable(tf.zeros([num_labels]), tf.float32)

		def model(data, weights, bias):
			return tf.matmul(flatten_tf_array(data), weights) + bias

		logits = model(train_xs_r, weights, bias)

		# 4) calculate the loss, which will be used in the optimization of the weights
		loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=logits, labels=train_ys_r))

		# 5) Choose an optimizer. Many are available.
		optimizer = tf.train.GradientDescentOptimizer(eta).minimize(loss)

		# 6) The predicted values for the images in the train dataset and test dataset are assigned to the variables train_prediction and test_prediction.
		# It is only necessary if you want to know the accuracy by comparing it with the actual values.
		train_prediction = tf.nn.softmax(logits)
		test_prediction = tf.nn.softmax(model(test_xs_r, weights, bias))

	with tf.Session(graph=graph) as session:
		tf.global_variables_initializer().run()
		print('Initialized')
		for step in range(num_steps):
			_, l, predictions = session.run([optimizer, loss, train_prediction])
			if (step % disp_step == 0):
				train_accuracy = accuracy(predictions, train_labels[:, :])
				test_accuracy = accuracy(test_prediction.eval(), test_labels)
				message = "step {:04d} : loss is {:06.2f}, accuracy on training set {:02.2f} %, accuracy on test set {:02.2f} %".format(
					step, l, train_accuracy, test_accuracy)
				print(message)

# !/usr/bin/env python
"""
Example of using Keras to implement a 1D convolutional neural network (CNN) for timeseries prediction.
"""

from keras.layers import Convolution1D, Dense, MaxPooling1D, Flatten
from keras.models import Sequential

__date__ = '2016-07-22'


def make_timeseries_regressor(wsize, filter_size, num_input_series=1, num_outputs=1, num_filt=4):
	""":Return: a Keras Model pred next value in a time series
    :param int wsize: num previous time steps
    :param int num_input_series: number of series;
    :param int num_outputs: number of output logits
    :param int filter_size: filter size
    :param int num_filt: The number of different filters to learn (roughly, input patterns to recognize).
    """
	model = Sequential((
		Convolution1D(nb_filter=num_filt, filter_length=filter_size, activation='relu', input_shape=(wsize, num_input_series)),
		MaxPooling1D(),
		Convolution1D(nb_filter=num_filt, filter_length=filter_size, activation='relu'),
		MaxPooling1D(),
		Flatten(),
		Dense(num_outputs, activation='linear'),
	))
	model.compile(loss='mse', optimizer='adam', metrics=['mae'])
	#model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['binary_accuracy']) #binary classification
	return model


def make_timeseries_instances(timeseries, wsize):
	"""Make input features and prediction targets from a `timeseries` for use in machine learning.
    :return: A tuple of `(X, y, q)`.  `X` are the inputs to a predictor, a 3D ndarray with shape
      ``(timeseries.shape[0] - window_size, window_size, timeseries.shape[1] or 1)``.  For each row of `X`, the
      corresponding row of `y` is the next value in the timeseries.  The `q` or query is the last instance, what you would use
      to predict a hypothetical next (unprovided) value in the `timeseries`.
    :param ndarray timeseries: Either a simple vector, or a matrix of shape ``(timestep, series_num)``, i.e., time is axis 0 (the
      row) and the series is axis 1 (the column).
    :param int wsize: The number of samples to use as input prediction features (also called the lag or lookback).
    """
	timeseries = np.asarray(timeseries)
	assert 0 < wsize < timeseries.shape[0]
	X = np.atleast_3d(
		np.array([timeseries[start:start + wsize] for start in range(0, timeseries.shape[0] - wsize)]))
	y = timeseries[wsize:]
	q = np.atleast_3d([timeseries[-wsize:]])
	return X, y, q


def evaluate_timeseries(timeseries, window_size):
	"""Create a 1D CNN regressor to predict the next value in a `timeseries` using the preceding `window_size` elements
    as input features and evaluate its performance.
    :param ndarray timeseries: Timeseries data with time increasing down the rows (the leading dimension/axis).
    :param int window_size: The number of previous timeseries values to use to predict the next.
    """
	filter_length = 5
	nb_filter = 4
	timeseries = np.atleast_2d(timeseries)
	if timeseries.shape[0] == 1:
		timeseries = timeseries.T  # Convert 1D vectors to 2D column vectors

	nb_samples, nb_series = timeseries.shape
	print('\n\nTimeseries ({} samples by {} series):\n'.format(nb_samples, nb_series), timeseries)
	model = make_timeseries_regressor(wsize=window_size, filter_size=filter_length, nb_input_series=nb_series,
	                                  num_outputs=nb_series, num_filt=nb_filter)
	print('\n\nModel with input size {}, output size {}, {} conv filters of length {}'.format(model.input_shape,
	                                                                                          model.output_shape,
	                                                                                          nb_filter, filter_length))
	model.summary()

	X, y, q = make_timeseries_instances(timeseries, window_size)
	print('\n\nInput features:', X, '\n\nOutput labels:', y, '\n\nQuery vector:', q, sep='\n')
	test_size = int(0.01 * nb_samples)  # In real life you'd want to use 0.2 - 0.5
	X_train, X_test, y_train, y_test = X[:-test_size], X[-test_size:], y[:-test_size], y[-test_size:]
	model.fit(X_train, y_train, nb_epoch=25, batch_size=2, validation_data=(X_test, y_test))

	pred = model.predict(X_test)
	print('\n\nactual', 'predicted', sep='\t')
	for actual, predicted in zip(y_test, pred.squeeze()):
		print(actual.squeeze(), predicted, sep='\t')
	print('next', model.predict(q).squeeze(), sep='\t')


def main():
	"""Prepare input data, build model, evaluate."""
	np.set_printoptions(threshold=25)
	ts_length = 1000
	window_size = 50

	print('\nSimple single timeseries vector prediction')
	timeseries = np.arange(ts_length)  # The timeseries f(t) = t
	evaluate_timeseries(timeseries, window_size)

	print('\nMultiple-input, multiple-output prediction')
	timeseries = np.array([np.arange(ts_length), -np.arange(ts_length)]).T  # The timeseries f(t) = [t, -t]
	evaluate_timeseries(timeseries, window_size)
