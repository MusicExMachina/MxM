from passage import Passage

class Window:
	
	def __init__(self, passage, time_start, time_end):
		self.notes = []
		for note in passage.notes:
			print note.notestart
			print note.notestart+note.length
			print note.length
			print note.pitch
			if note.notestart >= time_start or note.notestart+note.length < time_end:
				self.notes.append(note)
			if note.notestart < time_start and note.notestart+note.length >= time_end:
		self.time_start = time_start
		self.time_end = time_end
		self.importance = {}
		self.create_importance_dict()

	def create_importance_dict(self):
		for note in self.notes:
			self.importance[note] = note.get_importance()
