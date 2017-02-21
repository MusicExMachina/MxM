import sklearn
from note import Note
from passage import Passage
from window import Window



p = Passage()

###PARSE PASSAGE###
with Parser(sys.argv[1]) as parser:
	p = parser.createPassage()

oneBeatWindows = {}
twoBeatWindows = {}
fourBeatWindows = {}

###ASSUMING 4/4 (C) MEASURES###

#create windows of length one beat
for t in range(p.start*4, p.end*4):
	oneBeatWindows[t] = Window(p, float(t)/4.0, float(t+1)/4.0)


#create windows of length half measure
for t in range(p.start*2, p.end*2):
        twoBeatWindows[t] = Window(p, float(t)/2.0, float(t+1)/2.0)


#create windows of length full measure
for t in range(p.start, p.end):
        fourBeatWindows[t] = Window(p, float(t), float(t+1))


#get coherent windows
w = WindowScheduler([].extend(oneBeatWindows).extend(twoBeatWindows).extend(fourbeatWindows))

coherentWindows = w.scheduleWindows()


#create matrix of windows/feature extraction

windowMatrix = []



for c in coherentWindows:
	c.get_pitch_importance()
	windowMatrix.append(c.pimportance.values())


#run k-means

clusters = sklearn.kmeans(windowMatrix, 24)


#show clusters (PCA or something)


