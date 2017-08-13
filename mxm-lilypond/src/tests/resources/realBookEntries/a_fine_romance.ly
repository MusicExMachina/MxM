\bookpart {
	\tocItem \markup "A Fine Romance / Jerome Kern, Dorothy Fields"
	
	\markup {
		\column {
			\override #'(baseline-skip . 3.5)
			\column {
				\huge \larger \bold
				\fill-line { \larger "A Fine Romance" }
				\fill-line {
					"Lyrics by Dorothy Fields"
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

				\partial 4 s4 |

				\repeat volta 2 {
					\myMark "A"
					\startPart
					c1:6 | cis:dim7 | g:7/d | dis:dim7 | \myEndLine
					e:m7 | a:m7 | d:m7 | g:7 | \myEndLine
					\endPart

					\myMark "B"
					\startPart
					c:maj7 | c2:7 a4:13 aes:13 | g2:13 d:m7 | g:dim7 g:7 | \myEndLine
					c1:6 | a2:7 fis4:13 f:13 | e2:7 a:7 | d:7 g:7 | \myEndLine
					\endPart

					\myMark "A"
					\startPart
					c1:6 | cis:dim7 | g:7/d | dis:dim7 | \myEndLine
					e:m7 | a:m7 | d:m7 | g:7 | \myEndLine
					\endPart

					\myMark "C"
					\startPart
					c:6 | c2:7 a:7 | d:m7 a:7 | d:m7 dis:dim7 | \myEndLine
					e:m7 a:7 | d:m7 g:7.9- |

				}
				\alternative {
					{
						c:6 a:m7 | d:m7 g:7 | \myEndLineVoltaNotLast
					}
					{
						c:6 f:7 | c1:6.9 | \myEndLineVoltaLast
					}
				}
				\endPart
			}

			\new Staff="Melody" {
				\new Voice="Voice"
				\relative c'
				{
					\tempo "Presto" 4 = 196
					\time 4/4
					\key c \major

					\partial 4 e4 |

					\repeat volta 2 {
						%% part "A"
						c1 | a'4 g2 a4 | b,1 | b'4 a2 b4 |
						e,1 | d'4 c2 e,4 | g1 | e'4 d2 g,4 |
						%% part "B"
						c4 b c d | \tuplet 3/2 { c b! bes } a aes | g2 g~ | g2. g4 |
						a gis a b | \tuplet 3/2 { a aes g! } fis f | e2 e~ | e2. e4 |
						%% part "A"
						c1 | a'4 g2 a4 | b,1 | b'4 a2 b4 |
						e,1 | d'4 c2 e,4 | g1 | e'4 d2 g,4 |
						%% part "C"
						c4 b c d | \tuplet 3/2 { e d c } b! bes | a1 | \tuplet 3/2 { c4 b bes } a! aes |
						g1 | \tuplet 3/2 { b4 a aes } g! d' |
					}
					\alternative {
						{	c1 | r2 r4 e,4 |	}
						{	c'1~ | c2. r4 |		}
					}
				}
			}
			
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				A Fine Ro -- mance with no kis -- es!
				A Fine Ro -- mance, my friend, this is!
				%% part "B"
				We should be like a cou -- ple of hot to -- ma -- toes, __
				but you're as cold as yes -- ter -- day's mashed po -- ta -- toes. __
				%% part "A"
				A Fine Ro -- mance you won't nest -- le.
				A Fine Ro -- mance, you won't wrest -- le!
				%% part "C"
				I might as well play bridge with my old maid aunts!
				I have -- n't got a chance. This is A Fine Ro -- mance. A
			}
			\new Lyrics="Lyrics" \lyricsto "Voice"
			\lyricmode {
				%% part "A"
				_ Fine Ro -- mance my good fel -- low!
				You take ro -- mance, I'll take jel -- lo!
				%% part "B"
				You're calm -- er than the seal in the Arc -- tic O -- cean, __
				at least they flap their fins to ex -- press e -- mo -- tion. __
				%% part "A"
				A Fine Ro -- mance with no quar -- rels,
				With no in -- sults, and all mor -- als!
				%% part "C"
				I've nev -- er mussed the crease in your blue serge pants!
				I nev -- er get the chance. This is A Fine Ro -- _ _ mance. __
			}
		>>
	
		\layout {
		}
	}
	
	\noPageBreak
	\markup \column {
		\null
		\fill-line {
			\smaller \smaller { "Copyright © 1936 T.B. Harms Company. Copyright Renewed." }
		}
		\fill-line {
			\smaller \smaller { "Typeset by Mark Veltzer <mark@veltzer.net>" }
		}
	}
}
