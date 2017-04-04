def var(data):
	"""
	Find the variance of a list
	:param data: a list of floats
	:return:
	"""
	m = float(sum(data)) / float(len(data))
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

		opt = [0 for i in range(self.start, self.end)]
		windowSet = [[] for i in range(len(opt))]
		for i in range(1, len(opt)):
			options = []
			woptions = []
			if i >= 1:
				options.append(opt[i - 1] + maximize(self.owindows[1][i - 1]))
				woptions.extend(windowSet[i-1]).append(self.owindows[i])
			if i >= 2:
				options.append(opt[i - 2] + maximize(self.owindows[1][i / 2.0 - 1]))
				woptions.extend(windowSet[i-2]).append(self.owindows[i/2.0])
			if i >= 4:
				options.append(opt[i - 4] + maximize(self.owindows[1][i / 4.0 - 1]))
				woptions.extend(windowSet[i-2]).append(self.owindows[i/4.0])

			windowSet[i] = woptions.index(max(options))
		return windowSet[-1]
