import sklearn.cluster as cls
from sklearn.cluster import KMeans
import sklearn.decomposition as pca_comp
from wscheduler import WindowScheduler
from passage_parser import PassageParser
import sys
from passage import Passage
from window import Window
import matplotlib.pyplot as plt
from scipy.spatial.distance import cdist, pdist
from sklearn.model_selection import cross_val_score
import numpy as np


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

#print map(lambda x: (x.time_start, x.time_end), wins)
#print map(lambda x: x.pitch, wins[0].notes)

#coherentWindows = w.select_windows()

#print len(coherentWindows)
#print len(wins)


#create matrix of windows/feature extraction

windowMatrix = []

pwindows = []
for i in range(len(fourBeatWindows)):
	if i%2 == 0:
		pwindows.append(fourBeatWindows[i])

for c in pwindows:
	c.get_pitch_importance()
	row = map(lambda x: x["length"], c.pimportance.values())
	row.extend(map(lambda x: x["mposition"], c.pimportance.values()))
	windowMatrix.append(row)

#print windowMatrix

#run k-means

pca = pca_comp.PCA(n_components=2)
pca.fit(windowMatrix)
X = pca.transform(windowMatrix)

#print X

windowMatrixS = [windowMatrix[i*5] for i in range(len(windowMatrix)/5)]

windowMatrixT = []
for i in windowMatrix:
	if i not in windowMatrixS:
		windowMatrixT.append(i)

km = KMeans(n_clusters=10).fit(windowMatrix)
clusters = km.labels_
print km.predict(windowMatrix)
print km.score(windowMatrix)
print "hi"
K = range(1,50)
KM = [KMeans(n_clusters=k).fit(dt_trans) for k in K]
centroids = [k.cluster_centers_ for k in KM]

D_k = [cdist(dt_trans, cent, 'euclidean') for cent in centroids]
cIdx = [np.argmin(D,axis=1) for D in D_k]
dist = [np.min(D,axis=1) for D in D_k]
avgWithinSS = [sum(d)/dt_trans.shape[0] for d in dist]
wcss = [sum(d**2) for d in dist]
tss = sum(pdist(dt_trans)**2)/dt_trans.shape[0]
bss = tss-wcss
#clusters = cls.k_means(windowMatrixT, 10)


ks = [1,2,4,8,16]
clustersPoly = map(lambda k: cls.k_means(windowMatrix, k)[0], ks)
#clus_of_chords = clusters[1]
clus_of_chords = clusters
print clusters
#print len(clusters[1])
# for j in range(len(ks)):
# 	k = ks[j]
# 	with open("test_data/bachpf" + str(k) + ".csv", "w") as writer:
# 		for vec in clustersPoly[j]:
# 			for i in vec:
# 				writer.write(str(i) + ",")
# 			writer.write("\n")
#
print zip(map(lambda x: str(x.time_len) + str(x), pwindows), clus_of_chords)
#print cross_val_score(clusters, windowMatrix, clusters)
testC = range(len(clus_of_chords))

#TODO: Outlier analysis - strip harmonies that are barely used get core harmonies (maybe)

#TODO: Cross-Validation (LOOCV) & Model Selection to choose cluster number: (max 24) is probably too large

#show clusters (PCA or something)


#print X
#print windowMatrix[:][0]

plt.scatter(X[:,0], X[:,1], c=clus_of_chords, s=100)
plt.show()