import json
import numpy as np
import matplotlib.pyplot as plt
import math

def sigmoid(x,i,j,c):
    # print(x,i,j,c)
    result = 1.0/(1.0+np.e**(c*(i-x))) + 1.0/(1.0+np.e **( -c*(j-x)))  - 1

    return result

def pfunc(x, tune_list):
    s = 0
    for k in tune_list:
        for i in tune_list[k]:
            s += sigmoid(x, i[0], i[1], 1000)
    return s



#we make a 12 member dictionary to record the lasting time of each harmony
#record in "#" instead of flat
#only dealing with "#" format in json file
harmonys = {"A":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "A#":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "B":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "C":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "C#":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "D":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "D#":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "E":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "F":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "F#":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "G":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]},
            "G#":{1:[], 2:[], 3:[],4:[], 5:[],6:[],7:[],8:[],9:[]}}

with open('ray_pnjson.json') as f:
    line = f.readline()
    for line in f:
        line = line.strip()
        if (line[1:5] == "name"):
            if(line[10:11] == "#"):
                node = line[9:11]
                for k in harmonys:
                    # are we consider same harmony in different pitch with different function?
                    if(node == k):
                        pitch = int(line[11:12])
                        for key in harmonys[k]:
                            if(pitch == key):
                                f.readline()
                                #"start time"
                                start = float(f.readline().strip()[8:-1])
                                start = round(start,4)
                                #"velocity"
                                f.readline()
                                #"duration"
                                duration = float(f.readline().strip()[12:])
                                end = start+duration
                                end = round(end,4)
                                harmonys[k][key].append((start,end))
                                
            else:
                node = line[9:10]           
                for k in harmonys:
                    # are we consider same harmony in different pitch with different function?
                    if(node == k):
                        pitch = int(line[10:11])
                        #print(pitch)
                        for key in harmonys[k]:
                            if(pitch == key):
                                f.readline()
                                #"start time"
                                start = float(f.readline().strip()[8:-1])
                                start = round(start,4)
                                #"velocity"
                                f.readline()
                                #"duration"
                                duration = float(f.readline().strip()[12:])
                                end = start+duration
                                end = round(end,4)
                                harmonys[k][key].append((start,end))
                                
    f.close()

# now we need to transfer the duration time to function value of 1 and 0
#i = 1
for nodes in harmonys:
    ## this are three tunes that are not showed in our 12 bar piece
    # if(nodes == "B" or nodes == "C#" or nodes =="F#"):
    #     continue;
    if(nodes == "C"):
        x = np.linspace(0,90,1000)
        # get rid of empty pitch area
        y = pfunc(x,harmonys[nodes])
        #plt.subplot(3,3,i)
        plt.plot(x,y)
        #i += 1
plt.show()















