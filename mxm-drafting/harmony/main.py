import sklearn.cluster as cls
from wscheduler import WindowScheduler
from passage_parser import PassageParser
import sys
from passage import Passage
from window import Window



p = Passage()

###PARSE PASSAGE###
with PassageParser(sys.argv[1]) as parser:
	p = parser.read()

oneBeatWindows = {}
twoBeatWindows = {}
fourBeatWindows = {}

###ASSUMING 4/4 (C) MEASURES###

#create windows of length one beat
for t in range(p.start*4, p.end*4):
	oneBeatWindows[t] = Window(p, float(t)/4.0, float(t+1)/4.0)


#create windows of length half measure
for t in range(p.start*4, p.end*4):
	twoBeatWindows[t] = Window(p, float(t)/4.0, float(t+2)/4.0)


#create windows of length full measure
for t in range(p.start*4, p.end*4):
	fourBeatWindows[t] = Window(p, float(t)/4.0, float(t+4)/4.0)


#get coherent windows
w = WindowScheduler([].extend(oneBeatWindows).extend(twoBeatWindows).extend(fourBeatWindows))

coherentWindows = w.select_windows()


#create matrix of windows/feature extraction

windowMatrix = []



for c in coherentWindows:
	c.get_pitch_importance()
	windowMatrix.append(c.pimportance.values())


#run k-means


clusters = cls.k_means(coherentWindows, 12)

#TODO: Outlier analysis - strip harmonies that are barely used get core harmonies (maybe)

#TODO: Cross-Validation (LOOCV) & Model Selection to choose cluster number: (max 24) is probably too large

#show clusters (PCA or something)


