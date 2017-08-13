\bookpart {
	\tocItem \markup "All Or Nothing At All / Arthur Altman, Jack Lawrence"

	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "All Or Nothing At All" }
				\fill-line {
					"Lyrics by Jack Lawrence"
					"Music by Arthur Altman"
				}
				\fill-line {
					"Moderately Slow"
					""
				}
			}
		}
	}
	\noPageBreak

	\score {
		<<
			\new ChordNames="Chords"
			\with {
				\remove "Bar_engraver"
			}
	
			\chordmode {
				\startChords
				\startSong

				\myMark "A"
				\startPart
				a1*2:m | a1:m7 | a:m6 | \myEndLine
				a2:m a:m6 | a1:m | bes2:9 bes:6 | bes:aug bes:7 | \myEndLine
				g1:m | e2:m7.5- a:7.9- | d1*2:m | \myEndLine
				g1:7 | g2.:7 g4:7.5+ | c1:maj7 | b2:m7.5- e:7.9- | \myEndLine
				\endPart

				\myMark "A"
				\startPart
				a1*2:m | a1:m7 | a2:m6 a:m | \myEndLine
				a:m a:m6 | a1:m | bes2:9 bes:6 | bes:aug bes:7 | \myEndLine
				g1:m | e2:m7.5- a:7.9- | d1*2:m | \myEndLine
				g1:7 | g2.:7 g4:7.5+ | c1:maj7 | bes2:m7 ees:7 | \myEndLine
				\endPart

				\myMark "B"
				\startPart
				aes aes:aug | aes:6 aes:aug | aes aes:aug | aes:6 aes4:aug ees:7 | \myEndLine
				aes2 aes:aug | des aes | ees1:9 | ees2:aug ees:7 | \myEndLine
				bes:m7 ees:7 | bes:m7 ees:7 | bes:m7 ees:7 | g:m7.5- c:7 | \myEndLine
				f1:m | des:7 | c:7 | e:7 | \myEndLine
				\endPart

				\myMark "A"
				\startPart
				a1*2:m | a1:m7 | a:m6 | \myEndLine
				a1*2:m | bes2:9 bes:6 | bes:aug bes:7 | \myEndLine
				g1:m | e2:m7.5- a:7.9- | d1:m | b2:m7.5- e:7 | \myEndLine
				a1:m | d2:m7.5- g:7.11 | c1 | c:6 | \myEndLine
				\endPart

				\endSong
				\endChords
			}

			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c'{
				
					\tempo "Allegro" 4 = 168
					\time 4/4
					\key c \major

					%% part "A"
					e1~ | e4 e \tuplet 3/2 { e f c } | e1~ | e2. r4 |
					e2 e | e \tuplet 3/2 { e4 g c, } | d4. d8 d2~ | d2. r4 |
					d2 d | d \tuplet 3/2 { e4 cis d } | a'4. a8 a2~ | a b4 a |
					g g2 g4 | g g2 g4 | g1 | r |
					%% part "A"
					e~ | e4 e \tuplet 3/2 { e f c } | e1~ | e2. r4 |
					e2 e | e \tuplet 3/2 { e4 g c, } | d4. d8 d2~ | d2. r4 |
					d4. d8 d4. d8 | d4. d8 \tuplet 3/2 { e4 cis d } | a'4. a8 a2~ | a b4 a |
					g g2 g4 | g g2 g4 | g1~ | g4 r r ees |
					%% part "B"
					c' c c c | c4. c8 \tuplet 3/2 { c4 des aes } | c1~ | c2. ees,4 |
					c'4. c8 c4. c8 | des2 \tuplet 3/2 { c4 bes aes } | g1~ | g2. ees4 |
					\tuplet 3/2 { bes' bes bes } bes4. bes8 | \tuplet 3/2 { bes4 bes bes } \tuplet 3/2 { bes c g } | bes1~ | bes2 c4 bes |
					aes2 aes4 aes | aes aes2 aes4 | g1 | r4 d c b |
					%% part "A"
					e1 | \tuplet 3/2 { e4 f c } \tuplet 3/2 { e f c } | e1~ | e2. r4 |
					e2 e | e \tuplet 3/2 { e4 g c, } | d4. d8 d2~ | d2. r4 |
					d2 d | d4. d8 \tuplet 3/2 { e4 cis d } | a'1 | b |
					c1~ | c4 c \tuplet 3/2 { c c c } | c1~ | c2. r4 |
				}
			}
			
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				All, __ Or Noth -- ing At All. __
				Half a love nev -- er ap -- pealed to me. __
				If your heart nev -- er could yield to me, __
				then I'd rath -- er have noth -- ing at all!
				%% part "A"
				All __ Or Noth -- ing At All! __
				If it's love, there is no in -- be -- tween. __
				Why be -- gin, then cry for some -- thing that might have been. __
				No, I'd rath -- er have noth -- ing at all. __
				%% part "B"
				But, please, don't bring your lips so close to my cheek. __
				Don't smile or I'll be lost be -- yond re -- call. __
				The kiss in your eyes, the touch of your hand makes me weak, __
				And my heart may grow diz -- zy and fall.
				%% part "A"
				And if I fell un -- der the spell of your call. __
				I would be caught in the un -- der -- tow. __
				So, you see, I've got to say: No! No!
				All __ Or Noth -- ing At All! __
			}
		>>
			
		\layout {
		
		}
	}

	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright © 1930, 1940 by MCA MUSIC PUBLISHING, A Division of MCA Inc. Copyright Renewed" }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}