package io;

import model.form.Passage;
import javax.sound.midi.Sequence;

/**
 * MidiWriter is a class which does exactly what you'd expect.
 * Note that each MidiWriter composes exactly *one* midi Sequence.
 * This means that the MidiTools class instantiates one for every
 * single Passage to be written. This class could potentially be
 * absorbed into MidiTools, but is separated for the code cleanness.
 */
class MidiWriter {
    public Sequence run(Passage passage) {
        return null;
    }
}
