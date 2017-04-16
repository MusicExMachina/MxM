def importanceFuncSimple(l, s):
	return l

class Note:

	def __init__(self, pitch, length, notestart, importanceFunc = importanceFuncSimple):
		self.pitch = pitch #as pitch-class
		self.length = length #as float
		self.notestart = notestart #note start as float
		self.importanceFunc = importanceFunc

	def get_importance(self):
		return self.importanceFunc(self.length, self.notestart)

