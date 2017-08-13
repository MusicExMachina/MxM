\bookpart {
	\tocItem \markup "A Foggy Day / George Gershwin, Ira Gershwin"
	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "A Foggy Day" }
				\fill-line { \large \smaller \bold \larger "From 'A Damsel In Distress'" }
				\fill-line {
					"Lyrics by Ira Gershwin"
					"Music by George Gershwin"
				}
				\fill-line {
					"Medium Swing"
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

				\partial 4 s4 |

				\repeat volta 2 {
					\myMark "A"
					\startPart
					f1:maj7 | a2:m7.5- d:7.9- | g1:m7 | c:7 | \myEndLine
					f2. d4:m7.5- | d1:m7.5- | g:7 | g2:m7 c:7 | \myEndLine
					f1:maj7 | c2:m7 f:7 | bes1:maj7 | bes:m6 | \myEndLine
					f:maj7 | a2:m7 d:7 | g1:7.9 | g2:m7 c:7 | \myEndLine
					\endPart

					\myMark "B"
					\startPart
					f1:maj7 | a2:m7.5- d:7.9- | g1:m7 | c:7 | \myEndLine
					f2. d4:m7.5- | d1:m7.5- | g:7 | g2:m7 c:7 | \myEndLine
					c1:m7 | f:7 | bes:maj7 | ees:7 | \myEndLine
					f2 g:m7 | a:m7 bes:m6 | a:m7 d:m7 | g:m7 c:7 | \myEndLine
				}
				\alternative {
					{	f1 | g2:m7 c:7 | \myEndLineVoltaNotLast				}
					{	f1 | bes2:7 bes:m6 | f1:maj7 | \myEndLineVoltaLast	}
				}
				\endPart

				\endSong
				\endChords
			}

			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c' 
				{
					\tempo "Allegro" 4 = 130
					\time 4/4
					\key f \major

					\partial 4 c4 |

					\repeat volta 2 {
						%% part "A"
						c c2 ees4~ | ees2. ees4 | d d2 a'4~ | a1 |
						f2 f4 aes~ | aes2. aes4 | g2 g4 d'~ | d1 |
						r4 e e e | c c2. | a2 a4 f~ | f2. f4 |
						a a a c~ | c c2 c4 | a2 a4 d,~ | d2. c4 |
						%% part "B"
						c2 c4 ees~ | ees ees2 ees4 | d2 d4 a'~ | a2 a4 a |
						f2 f4 aes~ | aes bes aes2 | g g4 d'~ | d2. d4 |
						f2 f4 d~ | d2. d4 | c2 c4 a~ | a2 a4 bes |
						c f, g bes | a f g bes | a2 f' | f, g |
					}
					\alternative {
						{	f1 | r2 r4 c |	}
						{	f1~ | f~ | f |	}
					}
				}
			}
		
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				A Fog -- gy Day __ in Lon -- don town __ had me low __ and had me down. __
				I viewed the morn -- ing with a -- larm, __ the Brit -- ish Mu -- se -- um had lost its charm. __
				%% part "B"
				How long I won -- dered could this thing last? __ But the age of mir -- a -- cles had -- n't passed, __
				for sud -- den -- ly, __ I saw you there __ and through fog -- gy Lon -- don town the sun was shin -- ing ev -- 'ry where.
				%% part "Volta"
				A where. __
			}
		>>
		
		\layout {
		
		}
	}

	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright © 1937, Gershwin Publishing Corporation" }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}