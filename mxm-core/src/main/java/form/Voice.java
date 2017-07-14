package form;

import instruments.Instrument;

/**
 * Voices represent the more abstract use of the word "part"- first violin, third horn, snare, etc. Voices are part of
 * instrumentation, and map 1:1 with parts, as each voice will have one part. Note that multiple instruments may const-
 * itute a voice, such as in a full section of violas.
 */
public class Voice {
    Instrument instrument;
    int partInsection;
}
