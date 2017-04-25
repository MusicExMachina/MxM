def var(data):
	"""
	Find the variance of a list
	:param data: a list of floats
	:return:
	"""
	m = float(sum(data)) / float(len(data)+1)
	return sum(map(lambda d: (d - m) ** 2, data)) / len(data)


class WindowScheduler():
	"""
	This class schedules windows for determining harmonic sequence
	"""
	def __init__(self, windows):
		self.windows = windows
		self.owindows = {4:[], 2:[], 1:[]}
		self.organize_by_length()
		self.start = -1
		self.end = -1
		self.calc_window_info()

	def organize_by_length(self):
		"""
		4/4 ASSUMED
		creates owindows, organized by length (in beats)
		:return:
		"""
		lens = map(lambda x: x.get_t_len(), self.windows)
		for i in range(len(self.windows)):
			if lens[i] == 1:
				self.owindows[4].append(self.windows[i])
			elif lens[i] == .5:
				self.owindows[2].append(self.windows[i])
			elif lens[i] == .25:
				self.owindows[1].append(self.windows[i])
		#print len(self.owindows[1])
		#print len(self.owindows[2])
		#print len(self.owindows[4])

	def calc_window_info(self):
		"""
		Calculate the window start and end times (initialization)
		:return:
		"""
		minStart = 10000000
		maxEnd = 0
		for window in self.windows:
			if window.get_t_start() < minStart:
				minStart = window.get_t_start()
			if window.get_t_end() > maxEnd:
				maxEnd = window.get_t_end()
		self.start = minStart
		self.end = maxEnd

	def select_windows(self, maximize=var):
		"""
		4/4 ASSUMED
		Select Windows for a contiguous stretch of music, maximizing variance per beat
		a -> set of all windows of size 1 beat
		b -> set of all windows of size 2 beats
		c -> set of all windows of size 4 beats

		DYNAMIC ALGORITHM:
		OPT(i) = max{OPT(i-1) + var(a_(i-1)), OPT(i-2) + var(b_(i-2))*2, OPT(i-4) + var(c_(i-1))*4}
		"""

		opt = [0 for i in range(int(self.start*4), int(self.end*4))]
		#print len(opt)
		windowSet = [[] for i in range(len(opt))]
		for i in range(1, len(opt)):
			#print i
			options = [0,0,0,0,0]
			woptions = {1:[], 2:[], 4:[]}
			#print map(lambda x: x.pitch, self.owindows[1][i - 1].notes)
			if i >= 1:
				options[1] = opt[i - 1] + maximize(map(lambda x: x.pitch, self.owindows[1][i - 1].notes))
				woptions[1].extend(windowSet[i-1])
				woptions[1].append(self.owindows[1][i])
			if i >= 2:
				options[2] = opt[i - 2] + maximize(map(lambda x: x.pitch, self.owindows[2][i - 2].notes))
				woptions[2].extend(windowSet[i-2])
				woptions[2].append(self.owindows[2][i-2])
			if i >= 4:
				options[4] = opt[i - 4] + maximize(map(lambda x: x.pitch, self.owindows[4][i - 4].notes))
				woptions[4].extend(windowSet[i-4])
				woptions[4].append(self.owindows[4][i-4])
			opt[i] = max(options)
			windowSet[i] = woptions[options.index(max(options))]
			#print windowSet
		return windowSet[-1]
