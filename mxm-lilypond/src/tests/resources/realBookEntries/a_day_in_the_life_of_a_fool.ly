\bookpart {
	\tocItem \markup "A Day In The Life Of A Fool / Luiz Bonfa, Carl Sigman"

	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "A Day In The Life Of A Fool" }
				\fill-line { \large \smaller \bold \larger "Manha De Carnaval / Black Orpheus" }
				\fill-line {
					"Lyrics by Carl Sigman"
					"Music by Luiz Bonfa"
				}
				\fill-line {
					"Slow Bossa Nova"
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

				\myMark "A"
				\startPart
				a1:m | b2:m7.5- e:7.9- | a1:m | b2:m7.5- e:7.9- | \myEndLine
				a1:m | d2:m7 g:7 | c1:maj7 | e2:m7.5- a:7.9- | \myEndLine
				d1:m7 | g:7 | c:maj7 | f:maj7 | \myEndLine
				b:m7.5- | e:7.9- | a:m | b2:m7.5- e:7.9- | \myEndLine
				\endPart

				\myMark "A'"
				\startPart
				a1:m | b2:m7.5- e:7.9- | a1:m | b2:m7.5- e:7.9- | \myEndLine
				e1:m7.5- | a:7.9- | d1*2:m | \myEndLine
				d2:m d:m/c | b:m7.5- e:7.9- | a:m a:m/g | f1:maj7 | \myEndLine
				b:m7.5- | e:7 | a2:m d:m7 | a1:m | \myEndLine
				\endPart

				\myMark "B"
				\startPart
				d2:m7 a:m7 | d:m7 a:m7 | d:m7 e:m7 | a1*2:m6 | \myEndLine
				\endPart

				\endSong
				\endChords
			}

			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c' 
				{
					\tempo "Moderato" 4 = 120
					\time 4/4
					\key c \major

					\partial 4 e4 |
					
					%% part "A"
					c'2~ \tuplet 3/2 { c4 b a } | a2~ \tuplet 3/2 { a4 gis b } | e,1~ | e2 r4 e4 |
					c'2~ \tuplet 3/2 { c4 b a } | a2~ \tuplet 3/2 { a4 g b } | e,1~ | e2 r8 e f g |
					a4. d,8 d2~ | d r8 d e f | g4. c,8 c2~ | c r8 c d e |
					f4. b,8 b2~ | b \tuplet 3/2 { b4 c d } | e1~ | e2 r4 e |

					%% part "A'"
					c'2~ \tuplet 3/2 { c4 b a } | a2~ \tuplet 3/2 { a4 gis b } | e,1~ | e2 r4 e4 |
					bes'2~ \tuplet 3/2 { bes4 a g } | g2~ \tuplet 3/2 { g4 f e } | a1~ | a |
					r4 d, \tuplet 3/2 { d e f } | b1 | r4 c, \tuplet 3/2 { c d e } | a2. g4 |
					e1~ | e2 \tuplet 3/2 { e4 gis b } | a1~ | a2 \tuplet 3/2 { r4 a b } |

					%% part "B"
					\tuplet 3/2 { c d c } \tuplet 3/2 { b a b } | \tuplet 3/2 { c d c } \tuplet 3/2 { b a b } |
					\tuplet 3/2 { c d c } \tuplet 3/2 { b a g } | a1~ | a2. r4 |
				}
			}
			
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				A Day __ In The Life __ Of A Fool, __
				a sad __ and a long, __ lone -- ly day, __
				I walk the av -- e -- nue __ and hope I'll run in -- to __
				the wel -- come sight of you __ com -- ing my way. __

				%% part "A'"
				I stop __ just a -- cross __ from your door __
				but you're __ nev -- er home __ an -- y -- more. __
				So back to my room and there in the gloom
				I cry __ tears of good -- bye. __

				%% part "B"
				'Til you come back to me,
				that's the way it will be
				ev -- 'ry day in the life of a fool. __
			}
		>>
			
		\layout {
		
		}
	}

	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright 1959 by Nouvelles Editions Meridian" }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}