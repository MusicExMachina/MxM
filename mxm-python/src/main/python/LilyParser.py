import ly
from ly import document
from ly import rhythm
from ly import pitch
from ly import lex
import math
import numpy
import re

#==GLOBAL VARIBALES============================================================

BAR_LENGTH = 1.5 #This needs to change from header parsing


#==PREPROCESSOR HELPER FUNCTIONS===============================================

#Takes a list of LilyPond rhythm objects, explicitely defines the length of 
#the notes, and seperates each bar into sublists
def make_explicit(rhythm):
    tempList = []
    prevLength = 0
    barCounter = 0.0
    explicitRhythm = []

    for i in xrange(len(r)):
        print r[i]
        if (r[i] == ''):
            tempList.append(prevLength)
        else:
            prevLength = int(r[i].strip('.'))
            tempList.append(prevLength)

        barCounter += (1.0/prevLength) 

        #If the bar has now been filled
        if (barCounter >= BAR_LENGTH):
            barCounter = 0.0
            explicitRhythm.append(tempList)
            tempList = []

        #if (barCounter > BAR_LENGTH and __debug__):
        #   raise ValueError('INVALID NOTE LENGTHS')

    return explicitRhythm



#==MAIN HELPER FUNCTIONS=======================================================

#Take in a lilypond list of rhythm objects and turn into a vector of rhythms
#that can be read in by our neural nets
def rhythm_to_vector(r,p):
    #Base case 1: sublist is empty, in which case don't do anything
    if len(r) == 0:
        return

    #Base case 2: sublist is a singleton, then just return a singleton
    elif len(r) == 1:
        return [(1,r[0],p.pop(0))]

    #Otherwise chunk list into multiple sublists based on the first prime that
    #evenly divides the list and recurse
    else:
        length = len(r)
        prime = first_prime(length)
        l = [(prime,None,None)]

        chunks = numpy.array_split(r,prime)#Split list by the found prime
        for i in xrange(len(chunks)):
            l += rhythm_to_vector(chunks[i],p)
        return l

#OPTIMIZE THIS CODE
def first_prime(length):
    for i in range(2,102):
        if is_prime(i) and (length % i) == 0:
            return i

def is_prime(n):
    return all(n % i for i in xrange(2, n))

def file_splitter(file):
    print "here"
    songs_array = [] #Organizes songs into arrays of arrays in this format:
                    #[[metadata][chords][notes/rhythms]]
    while True:
        line = file.readline()
        if line == "": #Reached the end of the file
            break
        song = [[],[],[]]
        if line == "\\bookpart {\n": #IS THIS OVERLY SPECIFIC?
            print "Got a hit"
            songs_array.append(song)
            song = [[],[],[]]
    print songs_array
    return songs_array


#==MAIN========================================================================

if __name__ == '__main__':
    f = open("../../test/resources/realbook.ly")
    file = file_splitter(f)
    #Loads a lilypond file into a ly document object
    d = ly.document.Document().load("../../test/resources/realbook.ly")
    cursor = ly.document.Cursor(d)
    #Returns a list of the length of each note
    r = ly.rhythm.rhythm_extract(cursor)
    p = ly.pitch.PitchIterator(cursor).pitches()

    pitches_temp = []
    pitches = []
    rhythms = []
    chords = []

    rx = r"(([a-g]|[r])('?))|\\tuplet" #Will only accept notes a-g with optional ' character or a rest

    #Bring in the individual pitches or rests of the file
    for item in ly.rhythm.music_items(cursor,True,True):
        pitches_temp.append(item.tokens)
    temp_pitches = ["".join(tokens) for tokens in pitches_temp]

    for item in temp_pitches:
        if re.match(rx, item, re.I):
            pitches.append(item)

    #print pitches
    #print r
    try:
        rhythm = make_explicit(r)
    except ValueError as e:
        print(e)
        raise

    print rhythm
    v = []
    rhythm_length = 0
    for i in xrange(len(rhythm)):
        rhythm_length += len(rhythm[i])
    print rhythm_length, len(pitches)
    for i in xrange(100): #Hack solution, only run on the first 100 bars
        v.append(rhythm_to_vector(rhythm[i],pitches))
    #print pitches
    #print rhythms
    #if __debug__:
    #    print(v)

    print("Working")


