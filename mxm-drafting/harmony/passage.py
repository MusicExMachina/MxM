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

	def calculate_passage_details(self):
		beg = 100000
		end = 0
		for note in self.notes:
			if note.notestart < beg:
				beg = note.notestart
			if note.notestart + note.length:
				end = note.notestart + note.length
		self.start = int(beg)
		self.end = int(end)

	def add_notes(self,note):
		"""
		Create passage by adding notes
		:param note:
		:return:
		"""
		self.notes.append(note)
