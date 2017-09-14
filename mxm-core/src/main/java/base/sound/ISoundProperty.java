package base.sound;

import base.INoteProperty;

/**
 * ISoundProperty represents any "type" of sound that can be produced. Note that pitches are "types" of sound that have definite
 * ordering and relationships (called intervals). Other sounds, however- such as a bass drum hit- may have no easily-
 * definable relationship to other sounds produced on the same instrument (such as a drum roll).
 *
 * Note that the ISoundProperty interface often wraps around the MIDI sound class, given that different drum sounds for example,
 * are assigned to different pitches on a MIDI keyboard. This class, however, prevents a learning algorithm from
 * identifying characteristics such as "an octave" in such examples as no such relations exist.
 *
 * Note that sounds do not necessarily have inherent comparators, though some implementations (like Pitch) might.
 *
 * @author Patrick Celentano
 */
public interface ISoundProperty extends INoteProperty {

}
