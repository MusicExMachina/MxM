class Harmony:
	"""
	Class for Harmony storage in context of harmony detection
	"""
	def __init__(self):
		self.note_importance = {}   #dict for note importance in harmony
	
	def add_note_with_importance(self, note, importance):
		self.note_importance[note] = importance
