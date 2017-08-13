\bookpart {
	\tocItem \markup "All The Things You Are / Jerome Kern, Oscar Hammerstein II"

	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "All The Things You Are" }
				\fill-line { \large \smaller \bold \larger "From 'Very Warm For May'" }
				\fill-line {
					"Lyrics by Oscar Hammerstein II"
					"Music by Jerome Kern"
				}
				\fill-line {
					"Moderately"
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
				f1:m7 | bes:m7 | ees:7 | aes:maj7 | \myEndLine
				des:maj7 | g:7 | c1*2:maj7 | \myEndLine
				c1:m7 | f:m7 | bes:7 | ees:maj7 | \myEndLine
				aes:maj7 | a2:m7.5- d:7.9- | g1:maj7 | g2:maj7 e:7.9+ | \myEndLine
				\endPart

				\myMark "B"
				\startPart
				a1:m7 | d:7 | g:maj7 | c:maj7 | \myEndLine
				fis:m7.5- | b:7 | e:maj7 | c:7.5+ | \myEndLine
				\endPart

				\myMark "C"
				\startPart
				f:m7 | bes:m7 | ees:7 | aes:maj7 | \myEndLine
				des:maj7 | des2:m7 ges:7.9 | aes1:maj7 | b:dim7 | \myEndLine
				bes:m7 | ees:7.9 | aes1*2:maj7 | \myEndLine
				\endPart

				\endSong
			}

			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c' {
				
					\tempo "Allegro" 4 = 130
					\time 4/4
					\key aes \major

					%% part "A"
					aes'1 | des2. aes4 | g g g g | g c2 g4 |
					f f f f | f b2 f4 | e1~ | e |
					ees | aes2. ees4 | d d d d | d g2 d4 |
					c c c c | c d8 ees d4 c | b1~ | b4 d g d' |
					%% part "B"
					d4. c8 c2~ | c4 dis, e c' | b1~ | b4 d, g b |
					b4. a8 a2~ | a4 bes,? b a' | gis1 | r |
					%% part "C"
					aes | des2. aes4 | g g g g | g c2 g4 |
					f1 | ees'2. des4 | ees, ees \tuplet 3/2 { ees ees ees } | g2. f4 |
					des des f aes | f'2 g, | aes1~ | aes2. r4 |
				}
			}
	
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				You are the prom -- ised kiss of spring -- time
				That makes the lone -- ly win -- ter seem long. __
				You are the breath -- less hush of eve -- ning
				That trem -- bles on the brink of a love -- ly song. __
				%% part "B"
				You are the an -- gel glow __ that lights a star. __
				The dear -- est things I know __ are what you are.
				%% part "C"
				Some day my hap -- py arms will hold you,
				And some day I'll know that mo -- ment di -- vine,
				When All The Things You Are, are mine. __
			}
		>>
		
		\layout {
	
		}
	}
	
	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright © 1939 T.B. Harms Company. Copyright Renewed." }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}