import ly
from ly import document
from ly import rhythm
from ly import pitch
from ly import lex
import math
import numpy
import re

#TODO: FIX CHORD/RHYTHM PARSER, IMPLEMENT HEADER AND CHORD PROCESSING

#==GLOBAL VARIBALES============================================================

BAR_LENGTH = 1.0 #This needs to change from header parsing


#==PREPROCESSOR HELPER FUNCTIONS===============================================

#Takes a .ly (specifically in the format defined by open real book). Cuts the
#file into individual songs and then again into metadata, chords, and
#notes/rhythms. Returns them as an array of tuples
def file_splitter(file):
    songs_array = [] #Organizes songs into arrays of arrays in this format:
                     #([metadata][chords][notes/rhythms])

    song = ([],[],[])
    while True:
        line = file.readline()

        #This if block determines what part of the song we are in and adds
        #the file line by line into the appropriate part of the song tuple
        
        #if we reached the end of the file, add current our song and stop
        if line == "":
            songs_array.append(song)
            break

        #else if we started a new song, add our current song and continue
        elif line == "\\bookpart {\n": #IS THIS OVERLY SPECIFIC? TURN INTO REGEX
            songs_array.append(song)
            song = ([],[],[])

        #else if we entered the meta data part of the song, then read in song
        #title
        elif line == "\\markup {\n":
            rx = r" *(\\fill-line)"
            while (line != "}\n"):
                line = file.readline()
                if re.match(rx, line, re.I):
                    song[0].append(line)

        #else if we reached the chord part of the song, then read in all lines
        #to chord part of tuple until we reach the end of chordmode
        elif line == "\\chordmode {\n":
            while (line != "}\n"):
                line = file.readline()
                song[1].append(line)

        #else if we are in rhythm/notes part part of the song, then read in all
        #lines to rhythm part of tuple, unless it is meta data (go to meta data)
        elif line == "{\n":
            rx = r" *\\((tempo)|(time)|(key))"
            while (line != "}\n"):
                line = file.readline()
                if re.match(rx, line, re.I):
                    song[0].append(line)
                else:
                    song[2].append(line)

    #Remove the first, empty song and return array
    songs_array.pop(0)
    return songs_array

#Takes any part of the array and puts into a temporary .ly file to be read by
#lilypond functions
def reformat_to_lily(line_array):
    file = open("tempFile.ly","w")
    for i in range(len(line_array)):
        file.write(line_array[i])

    file.close()

    return open("tempFile.ly","r")



#Takes a list of LilyPond rhythm objects, explicitely defines the length of 
#the notes, and seperates each bar into sublists. Also allows for cases of
#tuplets (currently only 3/2 tuplets), which is why we're bringing in pitch
#Returns an array of arrays of note lengths
def make_explicit(r,p):
    #print len(r), len(p)
    #print r,p
    tempList = []
    prevLength = 0
    barCounter = 0.0
    explicitRhythm = []

    for i in xrange(len(r)):
        rx = r"\\tuplet"

        #If this actually a tuplet, then we need to overide where the notes
        #are placed
        if (re.match(rx, p[i], re.I)): #THIS ONLY HANDLES 3/2
            p.pop(i)
            tempList.extend([3,3,3])
            barCounter += 0.5

        #If the length of the note is not explicitly given, then assume it's the
        #previous note's length
        elif (r[i] == ''):
            tempList.append(prevLength)
            barCounter += (1.0/prevLength) 

        #Else, the note length is explicitly given, so set the prevLength to
        #this length
        else:
            prevLength = int(r[i].strip('.'))
            tempList.append(prevLength)
            barCounter += (1.0/prevLength) 

        #If the bar has now been filled, then start on a new bar
        if (barCounter >= BAR_LENGTH):
            barCounter = 0.0
            explicitRhythm.append(tempList)
            tempList = []

        #if (barCounter > BAR_LENGTH and __debug__):
        #   raise ValueError('INVALID NOTE LENGTHS')

    return explicitRhythm, p


#This is the main preprocessor, it takes an array of pitches and rhythms from a
#.ly file, cleans up/removes garbage and returns 2 arrays: pitches and rhythms
def rhythm_parser(song): #THIS IS THE UGLIEST CODE I'VE EVER WRITTEN WILL FIX
    #print song
    #Loads a lilypond file into a ly document object
    reformat_to_lily(song[2])
    d = ly.document.Document().load("tempFile.ly")
    cursor = ly.document.Cursor(d)
    #Returns a list of the length of each note
    r = ly.rhythm.rhythm_extract(cursor)
    p = ly.pitch.PitchIterator(cursor).pitches()

    pitches_temp = []
    pitches = []
    rhythms = []
    chords = []

    #THIS REGEX COULD BE BROKEN
    rx = r"([a-g]|[r])|\\tuplet" #Will only accept notes a-g with optional
                                 #' character or a tuplet or a rest

    #Bring in the individual pitches or rests of the file
    for item in ly.rhythm.music_items(cursor,True,True):
        pitches_temp.append(item.tokens)
    temp_pitches = ["".join(tokens) for tokens in pitches_temp]

    #Sanitize pitches: only bring in stuff determined by rx
    for item in temp_pitches:
        if re.match(rx, item, re.I):
            pitches.append(item)

    try:
        rhythm,pitches = make_explicit(r,pitches)
    except ValueError as e:
        print(e)
        raise

    return rhythm, pitches


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


#==MAIN========================================================================


if __name__ == '__main__':

    #Replace this file with "../../test/resources/realbook.ly" to test out the
    #entire real book
    f = open("../../test/resources/test5.ly")
    songs = file_splitter(f)

    rhythm,pitches = rhythm_parser(songs[0])
    
    #v is the final vector of pitches and rhythms
    v = []

    #Find the size of all of the notes by going into the array of arrays
    #SHOULD PROBABLY REPLACE THIS WITH A RETURN VARIABLE FROM RHYTHM_PARSER
    rhythm_length = 0
    for i in xrange(len(rhythm)):
        rhythm_length += len(rhythm[i])

`   #The main processing function call
    for i in xrange(len(pitches)):
        v.append(rhythm_to_vector(rhythm[i],pitches))

    if __debug__:
        print(v)

    
    print("Working")


