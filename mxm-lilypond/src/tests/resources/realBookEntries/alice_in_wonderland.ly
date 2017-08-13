\bookpart {
	\tocItem \markup "Alice In Wonderland / Sammy Fain, Bob Hilliard"

	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "Alice In Wonderland" }
				\fill-line {
					"Lyrics by Bob Hilliard"
					"Music by Sammy Fain"
				}
				\fill-line {
					"Med."
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
				\repeat volta 2 {
					d2.:m7 | g:7 | c:maj7 | f:maj7 | \myEndLine
					b:m7.5- | e:7 | a:m7 | ees:7 | \myEndLine
					d:m7 | g:7 | e:m7 | a:m7 | \myEndLine
					d:m7 | g:7 |
				}
				\alternative {
					{
						e4.:m7 a:7 | d:m7 g:7 | \myEndLineVoltaNotLast
					}
					{
						c2.:maj7 | a:m7 | \myEndLineVoltaLast
					}
				}
				\endPart

				\myMark "B"
				\startPart
				d2.:7 | g:7 | e:m7 | a:m7 | \myEndLine
				d:m7 | g:7 | c:maj7 | f:maj7 | \myEndLine
				fis:m7.5- | b:7.9- | e:m7 | a:7 | \myEndLine
				d2:m7 a4:7 | d2:m7 a4:7 | d2:m7 aes4:7 | g2.:7 | \myEndLine
				\endPart

				\myMark "A"
				\startPart
				d2.:m7 | g:7 | c:maj7 | f:maj7 | \myEndLine
				b:m7.5- | e:7 | a:m7 | ees:7 | \myEndLine
				d:m7 | g:7 | e:m7 | a:m7 | \myEndLine
				d:m7 | g:7 | c2.*2:maj7 | \myEndLine
				\endPart

				\endSong
				\endChords
			}

			\new Staff="Melody" {
				\new Voice="Voice"
					\relative c' 
					{
					\tempo "Allegro" 4 = 130
					\time 3/4
					\key c \major
					
					%% part "A"
					\repeat volta 2 {
						g'2. | g'2 f4 | e2 c4 | g2. |
						d'4 e f | e2 d4 | e2 c4 | g2. |
						d'4 e f | e2 d4 | e2 g4 | c2 a4 |
						g2 f4 | e2 d4 |
					}
					\alternative {
						{ g2.~ | g |		}
						{ c,2.~ | c2 cis4 |	}
					}
					%% part "B"
					d2. | a' | g | c, |
					d4 e f | g2 a4 | b2 g4 | e2. |
					fis | c' | b2 g4 | e2. |
					a2 g4 | f2 e4 | d2 c4 | b2. |
					%% part "A"
					g2. | g'2 f4 | e2 c4 | g2. |
					d'4 e f | e2 d4 | e2 c4 | g2. |
					d'4 e f | e2 d4 | e2 g4 | c2 a4 |
					g2 f4 | e2 d4 | c2.~ | c |
				}
			}

			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				A -- lice In Won -- der -- land
				How do you get to won -- der -- land
				O -- ver the hill or un -- der -- land
				or just be -- hind the tree __
			}

			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				When clouds go rol -- ling by
				They roll a -- way and leave the sky
				Where is the land be -- yond the eye
				That peo -- ple can -- not _ see __
				%% part "B"
				And where do stars go
				Where is the sil -- ver cre -- scent moon
				They must be some -- where
				in the sun -- ny af -- ter -- noon
				%% part "A"
				A -- lice In Won -- der -- land
				Where is the path to won -- der -- land
				O -- ver the hill or here or there
				I real -- ly won -- der where __
			}
		>>
		
		\layout {
		
		}
	}

	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "-- help me fill it out this copyright notice --" }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}