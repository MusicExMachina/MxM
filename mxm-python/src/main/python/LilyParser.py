import ly
from ly import document
from ly import rhythm
from ly import pitch
from ly import lex
import math
import numpy
import re

#==GLOBAL VARIBALES============================================================

BAR_LENGTH = 1.5


#==PREPROCESSOR HELPER FUNCTIONS===============================================

#Takes a list of LilyPond rhythm objects, explicitely defines the length of 
#the notes, and seperates each bar into sublists
def make_explicit(rhythm):
    tempList = []
    prevLength = 0
    barCounter = 0.0
    explicitRhythm = []

    for i in xrange(len(r)):
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

        #elif (barCounter > BAR_LENGTH):
        #   raise ValueError('INVALID NOTE LENGTHS')

    return explicitRhythm



#==MAIN HELPER FUNCTIONS=======================================================

#Take in a lilypond list of rhythm objects and turn into a vector of rhythms
#that can be read in by our neural nets
def rhythm_to_vector(r):
    #print len(r)

    #Base case 1: sublist is empty, in which case don't do anything
    if len(r) == 0:
        return

    #Base case 2: sublist is a singleton, then just return a singleton
    elif len(r) == 1:
        return [(1,r[0])]

    #Otherwise chunk list into multiple sublists based on the first prime that
    #evenly divides the list and recurse
    else:
        length = len(r)
        prime = first_prime(length)
        l = [(prime,None)]

        chunks = numpy.array_split(r,prime)#Split list by the found prime
        for i in xrange(len(chunks)):
            l += rhythm_to_vector(chunks[i])
        return l

#OPTIMIZE THIS CODE
def first_prime(length):
    for i in range(2,102):
        if is_prime(i) and (length % i) == 0:
            return i

def is_prime(n):
    return all(n % i for i in xrange(2, n))


#==MAIN========================================================================

if __name__ == '__main__':

    #Loads a lilypond file into a ly document object
    d = ly.document.Document().load("../../test/resources/test4.ly")
    cursor = ly.document.Cursor(d)
    #Returns a list of the length of each note
    r = ly.rhythm.rhythm_extract(cursor)
    p = ly.pitch.PitchIterator(cursor).pitches()

    pitches_temp = []
    pitches = []
    rhythms = []
    chords = []

    rx = r"u'[a-g]'?'" #Will only accept notes a-g with optional ' character

    #Bring in the individual pitches or rests of the file
    for item in ly.rhythm.music_items(cursor,True,True):
        #print item.tokens
        pitches_temp.append(item.tokens)
        #print "yo"
    test = ["".join(tokens) for tokens in pitches_temp]
    #print "WAZZUP",test
    print(type(test[0]))

    #REGULAR EXPRESSION IS BROKEN
    for item in test:
        if re.match(rx, item, re.I):
            pitches.append(item)
    print "BRO",pitches


    try:
        rhythm = make_explicit(r)
    except ValueError as e:
        print(e)
        raise

    v = []
    for i in xrange(len(rhythm)):
        v.append(rhythm_to_vector(rhythm[i]))
    #print(v)

    print("Working")


