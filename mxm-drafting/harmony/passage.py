from note import Note

class Passage:
	"""
	Class for storing passage of music
	(simplified from mxm main)
	"""
	def __init__(self, timesig):
		"""
		Initialize with time signature
		:param timesig:
		:return:
		"""
		self.start=None
		self.end=None
		self.notes = []
		self.timesig = timesig

	def add_notes(self,note):
		"""
		Create passage by adding notes
		:param note:
		:return:
		"""
		self.notes.append(note)
