import sklearn.cluster as cls
import sklearn.decomposition as pca_comp
from wscheduler import WindowScheduler
from passage_parser import PassageParser
import sys
from passage import Passage
from window import Window
import matplotlib.pyplot as plt


p = None

###PARSE PASSAGE###
with PassageParser(sys.argv[1]) as parser:
	p = parser.create_passage()

oneBeatWindows = []
twoBeatWindows = []
fourBeatWindows = []

###ASSUMING 4/4 (C) MEASURES###

#create windows of length one beat

for t in range(p.start * 4, p.end * 4):
	oneBeatWindows.append(Window(p, float(t)/4.0, float(t+1)/4.0))


#create windows of length half measure
for t in range(p.start * 4, p.end * 4-2):
	twoBeatWindows.append(Window(p, float(t)/4.0, float(t+2)/4.0))


#create windows of length full measure
for t in range(p.start*4, p.end*4-4):
	fourBeatWindows.append(Window(p, float(t)/4.0, float(t+4)/4.0))


#get coherent windows
wins = oneBeatWindows
wins.extend(twoBeatWindows)
wins.extend(fourBeatWindows)
w = WindowScheduler(wins)

coherentWindows = w.select_windows()

print coherentWindows


#create matrix of windows/feature extraction

windowMatrix = []



for c in coherentWindows:
	c.get_pitch_importance()
	windowMatrix.append(c.pimportance.values())


#run k-means


clusters = cls.k_means(windowMatrix, 20)
clus_of_chords = clusters[1]

#TODO: Outlier analysis - strip harmonies that are barely used get core harmonies (maybe)

#TODO: Cross-Validation (LOOCV) & Model Selection to choose cluster number: (max 24) is probably too large

#show clusters (PCA or something)

pca = pca_comp.PCA(n_components=2)
pca.fit(windowMatrix)

X = pca.transform(windowMatrix)
print X

plt.scatter(X[:,0], X[:,1])
plt.show()