from note import Note

class Passage:

	def __init__(self, timesig):
		self.start=None
		self.end=None
		self.notes = []
		self.timesig = timesig

	def add_notes(self,note):
		self.notes.append(note)
