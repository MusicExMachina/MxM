import ly
from ly import document
from ly import rhythm
from ly import pitch
from ly import lex
import math
import numpy
import re

'''KNOWN BUGS: Tuplets are not detected if immediately preceded by a note 
               extension.
   
   TODO:       Convert to python 3.
               Add header parsing for different chord length/absolute pitches.
               Clean up code, make function flow less confusing.
'''


#==GLOBAL VARIBALES============================================================

BAR_LENGTH = 1.0 #This needs to change from header parsing


#==FILE PREPROCESSOR HELPER FUNCTIONS==========================================

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
            metaRx = r" *\\((tempo)|(time)|(key))"
            partialRx = r" *\\partial"

            while (line != "}\n"):
                line = file.readline()
                if re.match(metaRx, line, re.I):
                    song[0].append(line)
                elif re.match(partialRx, line, re.I):
                    continue
                else:
                    song[2].append(line)


    #print song[1]
    #Remove the first, empty song and return array
    songs_array.pop(0)
    return songs_array

#Takes any part of the array and puts into a temporary .ly file to be read by
#lilypond functions
def reformat_to_lily(line_array):
    file = open("tempFile.ly","w")
    file.write("{\n")
    for i in range(len(line_array)):
        file.write(line_array[i])

    file.close()

    return open("tempFile.ly","r")


#==RHYTHM/PITCH PREPROCESSOR HELPER FUNCTIONS==================================

#Takes a list of LilyPond rhythm objects, explicitely defines the length of 
#the notes, and seperates each bar into sublists. Also allows for cases of
#tuplets (currently only 3/2 tuplets), which is why we're bringing in pitch
#Returns an array of arrays of note lengths
def make_explicit(r,p):
    tempList = []
    prevLength = 0
    barCounter = 0.0
    explicitRhythm = []

    ran = min(len(r),len(p))
    i = 0

    while i < min(len(r),len(p)):
        rx = r"\\tuplet"

        #If this actually a tuplet, then we need to overide where the notes
        #are placed
        if (re.match(rx, p[i], re.I)): #THIS ONLY HANDLES 3/2
            p.pop(i)
            r.pop(i)

            r.pop(i)
            r.pop(i)
            r.pop(i)
            tempList.extend([1.5,1.5,1.5])
            barCounter += 0.5

        #If the length of the note is not explicitly given, then assume it's the
        #previous note's length
        elif (r[i] == ''):
            tempList.append(prevLength)
            barCounter += (1.0/prevLength) 

        #Else, the note length is explicitly given, so set the prevLength to
        #this length
        else:
            prevLength = int((r[i]).strip('~.!'))
            tempList.append(prevLength)
            barCounter += (1.0/prevLength) 

        #If the bar has now been filled, then start on a new bar
        if (barCounter >= BAR_LENGTH):
            barCounter = 0.0
            explicitRhythm.append(tempList)
            tempList = []

        i+=1

        #if (barCounter > BAR_LENGTH and __debug__):
        #   raise ValueError('INVALID NOTE LENGTHS')

    return explicitRhythm, p

def chord_to_vector(chord):
    if chord == None:
        return

    if len(str.split(chord,":")) <= 1:
        return
    tonic = str.split(chord,":")[0]
    #print chord
    start = 0
    vector = [0]*12
    modifier = [1]+[0]*11
    #print tonic
    if ( re.search(r"bis",tonic)):
        start = 0
    elif ( re.search(r"cis",tonic)):
        start = 1
    elif ( re.search(r"dis",tonic)):
        start = 3
    elif ( re.search(r"eis",tonic)):
        start = 5
    elif ( re.search(r"fis",tonic)):
        start = 6
    elif ( re.search(r"gis",tonic)):
        start = 8
    elif ( re.search(r"ais",tonic)):
        start = 10
    elif ( re.search(r"des",tonic)):
        start = 1
    elif ( re.search(r"ees",tonic)):
        start = 3
    elif ( re.search(r"fes",tonic)):
        start = 4
    elif ( re.search(r"ges",tonic)):
        start = 6
    elif ( re.search(r"aes",tonic)):
        start = 8
    elif ( re.search(r"bes",tonic)):
        start = 10
    elif ( re.search(r"ces",tonic)):
        start = 11
    elif ( re.search(r"c",tonic)):
        start = 0
    elif ( re.search(r"d",tonic)):
        start = 2
    elif ( re.search(r"e",tonic)):
        start = 4
    elif ( re.search(r"f",tonic)):
        start = 5
    elif ( re.search(r"g",tonic)):
        start = 7
    elif ( re.search(r"a",tonic)):
        start = 9
    elif ( re.search(r"b",tonic)):
        start = 11
    else:
        print "CHORD NOT PARSED CORRECTLY"
        return
    modifierString = str.split(chord,":")[1]
    if (re.search(r"9-",modifierString)):
        modifier[1] = 1

    elif (re.search(r"9",modifierString)):
        modifier[2] = 1

    if (re.search(r"maj7",modifierString)):
        modifier[11] = 1

    elif (re.search(r"maj",modifierString)):
        modifier[4] = 1

    elif (re.search(r"m", modifierString)):
        modifier[3] = 1

    elif (re.search(r"7",modifierString)):
        modifier[10] = 1

    if (re.search(r"11",modifierString)):
        modifier[5] = 1

    if (re.search(r"sus4",modifierString)):
        modifier[5] = 1

    if (re.search(r"-5",modifierString)):
        modifier[6] = 1

    else:
        modifier[7] = 1

    if (re.search(r"6-",modifierString)):
        modifier[8] = 1

    elif (re.search(r"6",modifierString)):
        modifier[9] = 1

    if (re.search(r"13",modifierString)):
        modifier[9] = 1

    for i in xrange(12):
        vector[(i+start)%12] = modifier[i]

    # time = chord[1]
    # if time == ":":
    #     time = 0
    # else:
    #     time = int(time)
    #vectorWithTime = [time,vector]
    #print vector
    return vector #DONT FORGET TO RETURN VECTOR WITH TIME
    #return vectorWithTime


def chord_parser(song):
    chordLines  = song[1]
    chords      = []
    tempLine    = []
    tempBar     = []
    totalLength = 0

    partialRx   = r" *\\partial"
    startRx     = r" *\\start"
    markRx      = r" *\\myMark"
    slashRx     = r" *\\"
    newLineRx   = r"\\n"
    newBarRx    = r"|"

    for i in xrange(len(chordLines)):
        if (re.match(slashRx, chordLines[i]) or\
            re.match(newLineRx, chordLines[i])):
            continue


        tempLine = str.split(chordLines[i])
        if tempLine == []:
            continue
        for j in xrange(len(tempLine)):
            if tempLine[j] == '|':#re.match(newBarRx, tempLine[j]):
                chords.append(tempBar)
                tempBar = []
                continue
            tempBar.append(chord_to_vector(tempLine[j]))

    return chords


def color_rhythm_with_chords(rhythm, chords):

    lastChordLen = chords[0][0][0]
    currChordLen = lastChordLen
    returnChords = []
    for i in range( min(len(rhythm),len(chords)) ):
        #print rhythm[i]
        #currChord = chords[i][0][0]
        currChordNum = 0
        for j in range( len(rhythm[i]) ):
            if (currChordLen <= 0):
                pass



#This is the main preprocessor, it takes an array of pitches and rhythms from a
#.ly file, cleans up/removes garbage and returns 2 arrays: pitches and rhythms
def rhythm_parser(song): #THIS IS THE UGLIEST CODE I'VE EVER WRITTEN WILL FIX

    #Loads a lilypond file into a ly document object
    reformat_to_lily(song[2])
    d = ly.document.Document().load("tempFile.ly")
    cursor = ly.document.Cursor(d)
    ly.rhythm.rhythm_explicit(cursor)

    #Returns a list of the length of each note
    r = ly.rhythm.rhythm_extract(cursor)
    p = ly.pitch.PitchIterator(cursor)

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

    # if (len(pitches) < len(r)):
    #     r = r[:len(pitches)]

    try:
        rhythm,pitches = make_explicit(r,pitches)
    except ValueError as e:
        #print(e)
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
    f = open("../../test/resources/realbook.ly")
    songs = file_splitter(f)

    fh = open("../../test/resources/chords.txt","w")

    for x in xrange(10):
        #rhythm,pitches = rhythm_parser(songs[x])
        chords = chord_parser(songs[x])
        for i in range(len(chords)):
            for j in range(len(chords[i])):
                if chords[i][j] != None:
                    fh.write(str(chords[i][j]))
                    fh.write("\n")
        fh.write("\n\n\n\n")
        continue


        #print chords

        #print len(rhythm), len(chords)

        #chords = color_rhythm_with_chords(rhythm,chords)
        #v is the final vector of pitches and rhythms
        v = []

        #Find the size of all of the notes by going into the array of arrays
        #SHOULD PROBABLY REPLACE THIS WITH A RETURN VARIABLE FROM RHYTHM_PARSER
        rhythm_length = 0
        for i in xrange(len(rhythm)):
            rhythm_length += len(rhythm[i])

        #The main processing function call
        for i in xrange(len(rhythm)):

            v.append(rhythm_to_vector(rhythm[i],pitches))

        if __debug__:
            print(v) #Add -O option to remove this debug statement

    
    print("Working")


