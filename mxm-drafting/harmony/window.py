from passage import Passage

class Window:
	"""
	This is a class describing a hypothetical Harmonic Window
	We will use windows of various sizes to describe this
	"""

	def __init__(self, passage, time_start, time_end):
		"""
		Initialize the window with a passage, and a start & end time. Automatically populates window
		:param passage:
		:param time_start:
		:param time_end:
		:return:
		"""
		self.notes = []
		for note in passage.notes:
			# print note.notestart
			# print note.notestart+note.length
			# print note.length
			# print note.pitch
			if note.notestart >= time_start or note.notestart+note.length < time_end:
				self.notes.append(note)
			if note.notestart < time_start and note.notestart+note.length >= time_end:
				pass
		self.time_start = time_start
		self.time_end = time_end
		self.time_len = time_end-time_start
		self.importance = {}
		self.create_importance_dict()
		self.pimportance = {}
		self.window_multipliers = {}

	def create_importance_dict(self):
		"""
		Create dictionary of importance by note for use in Window object
		:return:
		"""
		for note in self.notes:
			self.importance[note] = note.get_importance()

	def calculate_window_multipliers(self):
		"""
		Create set of window multipliers to enhance the pitch importance values based on position in Window object
		:return:
		"""
		for n in self.importance:
			self.window_multipliers[n] = 1 #NONE



	def get_pitch_importance(self):
		"""
		Calculate actual importance of the each pitch class in a passage
		:return:
		"""
		self.calculate_window_multipliers()
		for p in range(12):
			self.pimportance[p] = 0
		#for 12 pitch classes
		for x in self.importance:
			self.pimportance[x.pitch] += self.importance[x] * self.window_multipliers[x]

	def get_t_start(self):
		return self.time_start

	def get_t_end(self):
		return self.time_end

	def get_t_len(self):
		return self.time_len