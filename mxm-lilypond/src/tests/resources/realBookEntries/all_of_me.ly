\bookpart {
	\tocItem \markup "All Of Me / Seymour Simons, Gerald Marks"
	
	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "All Of Me" }
				\fill-line {
					""
					"Lyrics and Music by Seymour Simons, Gerald Marks"
				}
				\fill-line {
					"Mod. Swing"
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
				c1*2:maj7 | e:7 | \myEndLine
				a:7 | d:m | \myEndLine
				e:7 | a:m | \myEndLine
				d:7 | d1:m7 | g:7 | \myEndLine
				\endPart

				\myMark "B"
				\startPart
				c1*2:maj7 | e:7 | \myEndLine
				a:7 | d:m | \myEndLine
				f1 | f:m | c2:maj7 e:m7 | a1:7 | \myEndLine
				d:m7 | g:7 | c2:6 \LPC ees:dim | d:m7 \RPC g:7 | \myEndLine
				\endPart

				\endSong
				\endChords
			}

			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c' 
				{
					\tempo "Allegro" 4 = 168
					\time 4/4
					\key c \major

					%% part "A"
					c'4 g8 e~ e2~ | e \tuplet 3/2 { c'4 d c } | b gis8 e~ e2~ | e1 |
					a4. g8 e2~ | e4 dis \tuplet 3/2 { e bes' a } | g2 f~ | f1 |
					e4. ees8 d2~ | d \tuplet 3/2 { e4 gis b } | d2 c~ | c1 |
					b4. bes8 a2~ | a \tuplet 3/2 { a4 d b } | a1 | b |
					%% part "B"
					c4 g8 e~ e2~ | e \tuplet 3/2 { c'4 d c } | b gis8 e~ e2~ | e1 |
					a4. g8 e2~ | e4 dis \tuplet 3/2 { e bes' a } | g2 f~ | f1 |
					d'2 c4 b | d2. c4 | b2 e,4 g | b2. a4 |
					c2 a4 c | e2 e | c1~ | c |
				}
			}
			
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				All of me __
				why not take all of me __
				Can't you see __
				I'm no good with -- out you __
				Take my lips __
				I want to lose them __
				Take my arms __
				I'll ne -- ver use them __
				%% part "B"
				Your good -- bye __
				left me with eyes that cry __
				How can I __
				get a -- long with -- out you __
				You took the part
				that once was my heart
				So why not
				take all of me __
			}
		>>
		
		\layout {
		
		}
	}

	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright © 1931 Bourne Co. Copyright Renewed" }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}