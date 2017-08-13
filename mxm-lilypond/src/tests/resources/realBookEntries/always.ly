\bookpart {
	\tocItem \markup "Always / Irving Berlin"
	
	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "Always" }
				\fill-line {
					""
					"Lyrics and Music by Irving Berlin"
				}
				\fill-line {
					"Waltz"
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
				\startSong
				\startChords

				\repeat volta 2 {
					\myMark "A"
					\startPart
					f2.*4 | \myEndLine
					c2.*2:7 | f | \myEndLine
					f2. | f2 f4:7 | a2.*2 | \myEndLine
					e:7 | a2. | c2.:7 | \myEndLine
					\endPart

					\myMark "B"
					\startPart
					f2.*2 | f2.:7 | f4:7/f f:7/e f:7/ees | \myEndLine
					d2.*2:7 | c4:m g2:m | g2.:m | \myEndLine
					g:m | bes:m | f | g:9 | \myEndLine
					c2.*2:7 |
				}
				\alternative {
					{	f2. | c:7 | \myEndLineVoltaNotLast	}
					{	f2.*2 | \myEndLineVoltaLast			}
				}
				\endPart

				\endChords
				\endSong
			}
			
			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c' {
					\tempo "Andante" 4 = 88
					\time 3/4
					\key f \major

					\repeat volta 2 {
						%% part "A"
						c4. d8 f g | a2. | c4 a2~ | a2. |
						c,4. d8 e f | g2. | a4 f2~ | f2. |
						c4. d8 f g | a2. | e4. fis8 a b | cis2. |
						e,4. fis8 gis b | d2. | cis4 a2 | a4 g2 |
						%% part "B"
						c,4. d8 f g | a2. | c4 a2~ | a2. |
						d,4. e8 fis a | d2. | es4 d2~ | d2. |
						bes4. a8 g a | bes2. | a4. g8 f g | a2. |
						g4. f8 e f | g2 c,4 |
					}
					\alternative {
						{	a'4 f2~ | f4 r r |	}
						{	a4 f2~ | f2 r4 |	}
					}
				}
			}
			
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				I'll be lov -- ing you, Al -- ways __
				With a love that's true, Al -- ways __
				When the things you've planned
				Need a help -- ing hand,
				I will un -- der -- stand, Al -- ways, Al -- ways.
				%% part "B"
				Days may not be fair, Al -- ways __
				That's when I'll be there, Al -- ways. __
				Not for just and hour, Not for just a day,
				Not for just a year, But Al -- ways. __
				%% part "Volta"
				Al -- ways. __
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
			\smaller \smaller { "Typeset by Jordan Eldredge <JordanEldredge@gmail.com>" }
		}
	}
}