def importanceFuncSimple(l, s):
	return l

class Note:

	def __init__(self, pitch, length, notestart, actual_pitch, importanceFunc = importanceFuncSimple):
		self.pitch = pitch #as pitch-class
		self.length = length #as float
		self.notestart = notestart #note start as float
		self.actual_pitch = actual_pitch
		self.importanceFunc = importanceFunc
		#print notestart, length, pitch

	def get_importance(self):
		return self.importanceFunc(self.length, self.notestart)

	def __str__(self):
		return str(self.pitch)

	def __repr__(self):
		return self.__str__()

