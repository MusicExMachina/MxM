\bookpart {
	\tocItem \markup "Afternoon in Paris / John Lewis"

	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "Afternoon in Paris" }
				\fill-line {
					""
					"Music by John Lewis"
				}
				\fill-line {
					"Swing"
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
				c1:6 | c2:m7 f:7 | bes1:6 | bes2:m7 ees:7 | \myEndLine
				aes1:maj7 | d2:m7.5- g:7 | c:maj7 a:m7 | d:m7 g:7 | \myEndLine
				\endPart

				\myMark "A"
				\startPart
				c1:6 | c2:m7 f:7 | bes1:6 | bes2:m7 ees:7 | \myEndLine
				aes1:maj7 | d2:m7.5- g:7 | c1 | a:m7 | \myEndLine
				\endPart

				\myMark "B"
				\startPart
				d1:m7 | g:7.9- | c:6 | a:m7 | \myEndLine
				d:m7 | g:7.9- | cis2:m7.5- fis:7 | d:m7 g:7 | \myEndLine
				\endPart

				\myMark "A"
				\startPart
				c1:6 | c2:m7 f:7 | bes1:6 | bes2:m7 ees:7 | \myEndLine
				aes1 | d2:m7.5- g:7 | c1*2 | \myEndLine
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
					\key c \major

					\partial 4 e'4 |
					%% part "A"
					g8 d r c b c d e | ees g, bes d c4 d | f8 c r bes a bes c d | des f, aes c bes4 c |
					ees8 c aes ees g bes aes4 | f2 \tuplet 3/2 { g4 f g } | e8 g b d a b c e | f a, c e d4 e |
					%% part "A"
					g8 d r c b c d e | ees g, bes d c4 d | f8 c r bes a bes c d | des f, aes c bes4 c |
					ees8 c aes ees g bes aes4 | aes2 \tuplet 3/2 { g4 f g } | e1~ | e2 a4 b |
					%% part "B"
					c2. d4 | e2. d4 | c2. e4 | a,2. b4 |
					c2. d4 | e2. d4 | e1 | f2. e4 |
					%% part "A"
					g8 d r c b c d e | ees g, bes d c4 d | f8 c r bes a bes c d | des f, aes c bes4 c |
					ees8 c aes ees g bes aes4 | f2 \tuplet 3/2 { g4 f g } | e1~ | e2. r4 |
				}
			}
		>>
		
		\layout {
		
		}
	}


	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright © 1955 and Renewal of Copyright 1983 by MJQ Music, Inc." }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}