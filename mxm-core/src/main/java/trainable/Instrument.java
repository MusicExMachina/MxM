package trainable;

import basic.Pitch;
import basic.Technique;

import java.util.ArrayList;
import java.util.Set;

/**
 * A class for storing information about
 * a specific instrument. Note that this
 * is specific enough to be "Horn in F"
 * instead of just "Horn" given that different
 * transpositions of the "same" instrument
 * have different tendencies.
 */
public class Instrument {

    private static final ArrayList<Instrument> GeneralMIDIInstrumentSet = new ArrayList<>();

    public static final Instrument ACOUSTIC_GRAND_PIANO = new Instrument("Acoustic Grand Piano");
    public static final Instrument BRIGHT_ACOUSTIC_PIANO = new Instrument("Bright Acoustic Piano");
    public static final Instrument ELECTRIC_GRAND_PIANO = new Instrument("Electric Grand Piano");
    public static final Instrument HONKY_TONK_PIANO = new Instrument("Honky-Tonk Piano");
    public static final Instrument ELECTRIC_PIANO1 = new Instrument("Electric Piano 1");
    public static final Instrument ELECTRIC_PIANO2 = new Instrument("Electric Piano 2");
    public static final Instrument HARPSICHORD = new Instrument("Harpsichord");
    public static final Instrument CLAVI = new Instrument("Clavichord");
    public static final Instrument CELESTA = new Instrument("Celesta");
    public static final Instrument GLOCKENSPIEL = new Instrument("Glockenspiel");
    public static final Instrument MUSIC_BOX = new Instrument("Music Box");
    public static final Instrument VIBES = new Instrument("Vibraphone");
    public static final Instrument MARIMBA = new Instrument("Marimba");
    public static final Instrument XYLOPHONE = new Instrument("Xylophone");
    public static final Instrument TUBULAR_BELLS = new Instrument("Tubular Bells");
    public static final Instrument DULCIMER = new Instrument("Dulcimer");
    public static final Instrument DRAW_BAR_ORGAN = new Instrument("Drawbar Organ");
    public static final Instrument PERCUSSIVE_ORGAN = new Instrument("Percussive Organ");
    public static final Instrument ROCK_ORGAN = new Instrument("Rock Organ");
    public static final Instrument CHURCH_ORGAN = new Instrument("Churh Organ");
    public static final Instrument REED_ORGAN = new Instrument("Reed Organ");
    public static final Instrument ACCORDION = new Instrument("Accordion");
    public static final Instrument HARMONICA = new Instrument("Harmonica");
    public static final Instrument TANGO_ACCORDION = new Instrument("Tango Accordion");
    public static final Instrument ACOUSTIC_NYLON_GUITAR = new Instrument("Acoustic Guitar (Nylon)");
    public static final Instrument ACOUSTIC_STEEL_GUITAR = new Instrument("Acoustic Guitar (Steel)");
    public static final Instrument ELECTRIC_JAZZ_GUITAR = new Instrument("Electric Guitar (Jazz)");
    public static final Instrument ELECTRIC_CLEAN_GUITAR = new Instrument("Electric Guitar (Clean)");
    public static final Instrument ELECTRIC_MUTED_GUITAR = new Instrument("Electric Guitar (Muted)");
    public static final Instrument OVERDRIVEN_GUITAR = new Instrument("Overdriven Guitar");
    public static final Instrument DISTORTION_GUITAR = new Instrument("Distortion Guitar ");
    public static final Instrument GUITAR_HARMONICS = new Instrument("Guitar Harmonics");
    public static final Instrument ACOUSTIC_BASS = new Instrument("Acoustic Bass");
    public static final Instrument ELECTRIC_FINGER_BASS = new Instrument("Electric Bass (Finger)");
    public static final Instrument ELECTRIC_PICKED_BASS = new Instrument("Electric Bass (Picked)");
    public static final Instrument FRETLESS_BASS = new Instrument("Fretless Bass");
    public static final Instrument SLAPBASS_1 = new Instrument("Slap Bass 1");
    public static final Instrument SLAPBASS_2 = new Instrument("Slap Bass 2");
    public static final Instrument SYNTHBASS_1 = new Instrument("Synth Bass 1");
    public static final Instrument SYNTHBASS_2 = new Instrument("Synth Bass 2");
    public static final Instrument VIOLIN = new Instrument("Violin");
    public static final Instrument VIOLA = new Instrument("Viola");
    public static final Instrument CELLO = new Instrument("Cello");
    public static final Instrument CONTRABASS = new Instrument("Contrabass");
    public static final Instrument TREMOLO_STRINGS = new Instrument("Tremolo Strings");
    public static final Instrument PIZZICATO_STRINGS = new Instrument("Pizzicato Strings");
    public static final Instrument ORCHESTRAL_HARP = new Instrument("Orchestral Harp");
    public static final Instrument TIMPANI = new Instrument("Timpani");
    public static final Instrument STRING_ENSEMBLE_1 = new Instrument("String Ensemble 1");
    public static final Instrument STRING_ENSEMBLE_2 = new Instrument("String Ensemble 2");
    public static final Instrument SYNTH_STRINGS1 = new Instrument("Synth Strings 1");
    public static final Instrument SYNTH_STRINGS2 = new Instrument("Synth Strings 2");
    public static final Instrument AAHS = new Instrument("Choral Aahs");
    public static final Instrument OOHS = new Instrument("Voice Oohs");
    public static final Instrument SYNTH_VOICE = new Instrument("Synth Voice");
    public static final Instrument ORCHESTRA_HIT = new Instrument("Orchestra Hit");
    public static final Instrument TRUMPET = new Instrument("Trumpet");
    public static final Instrument TROMBONE = new Instrument("Trombone");
    public static final Instrument TUBA = new Instrument("Tuba");
    public static final Instrument MUTED_TRUMPET = new Instrument("Muted Trumpet");
    public static final Instrument FRENCHORN = new Instrument("French Horn");
    public static final Instrument BRASSS_ECTION = new Instrument("Brass Section");
    public static final Instrument SYNTH_BRASS_1 = new Instrument("Synth Brass 1");
    public static final Instrument SYNTH_BRASS_2 = new Instrument("Synth Brass 2");
    public static final Instrument SOPRANO_SAX = new Instrument("Soprano Sax");
    public static final Instrument ALTO_SAX = new Instrument("Alto Sax");
    public static final Instrument TENOR_SAX = new Instrument("Tenor Sax");
    public static final Instrument BARI_SAX = new Instrument("Bari Sax");
    public static final Instrument OBOE = new Instrument("Oboe");
    public static final Instrument ENGLISH_HORN = new Instrument("English Horn");
    public static final Instrument BASSOON = new Instrument("Bassoon");
    public static final Instrument CLARINET = new Instrument("Clarinet");
    public static final Instrument PICCOLO = new Instrument("Piccolo");
    public static final Instrument FLUTE = new Instrument("Flute");
    public static final Instrument RECORDER = new Instrument("Recorder");
    public static final Instrument PAN_FLUTE = new Instrument("Pan Flute");
    public static final Instrument BLOWN_BOTTLE = new Instrument("Blown Bottle");
    public static final Instrument SHAKUHACHI = new Instrument("Shakuhachi");
    public static final Instrument WHISTLE = new Instrument("Whistle");
    public static final Instrument OCARINA = new Instrument("Ocarina");
    public static final Instrument SQUARE_LEAD_1 = new Instrument("Square Lead 1");
    public static final Instrument SAWTOOTH_LEAD_2 = new Instrument("Sawtooth Lead 2");
    public static final Instrument CALLIOPE_LEAD_3 = new Instrument("Calliope Lead 3");
    public static final Instrument CHIFF_LEAD_4 = new Instrument("Chieff Lead 4");
    public static final Instrument CHARANG_LEAD_5 = new Instrument("Charang Lead 5");
    public static final Instrument VOICE_LEAD_6 = new Instrument("Voice Lead 6");
    public static final Instrument FIFTHS_LEAD_7 = new Instrument("Fifths Lead 7");
    public static final Instrument BASS_LEAD_8 = new Instrument("Bass Lead 8");
    public static final Instrument NEWAGE_PAD_1 = new Instrument("New Age Pad 1");
    public static final Instrument WARM_PAD_2 = new Instrument("Warm Pad 2");
    public static final Instrument POLY_SYNTH_PAD_3 = new Instrument("Polysynth Pad 3");
    public static final Instrument CHOIR_PAD_4 = new Instrument("Choir Pad 4");
    public static final Instrument BOWED_PAD_5 = new Instrument("Bowed Pad 5");
    public static final Instrument METALLIC_PAD_6 = new Instrument("Metallic Pad 6");
    public static final Instrument HALO_PAD_7 = new Instrument("Halo Pad 7");
    public static final Instrument SWEEP_PAD_8 = new Instrument("Sweep Pad 8");
    public static final Instrument RAIN_FX_1 = new Instrument("Rain FX 1");
    public static final Instrument SOUND_TRACK_FX_2 = new Instrument("Soundtrack FX 2");
    public static final Instrument CRYSTAL_FX_3 = new Instrument("Crystal FX 3");
    public static final Instrument ATMOSPHERE_FX_4 = new Instrument("Atmosphere FX 4");
    public static final Instrument BRIGHTNESS_FX_5 = new Instrument("Brightness FX 5");
    public static final Instrument GOBLIN_SFX_6 = new Instrument("Goblins FX 6");
    public static final Instrument ECHOE_SFX_7 = new Instrument("Echoes FX 7");
    public static final Instrument SCIFI_FX_8 = new Instrument("Sci-Fi FX 8");
    public static final Instrument SITAR = new Instrument("Sitar");
    public static final Instrument BANJO = new Instrument("Banjo");
    public static final Instrument SHAMISEN = new Instrument("Shamisen");
    public static final Instrument KOTO = new Instrument("Koto");
    public static final Instrument KALIMBA = new Instrument("Kalimba");
    public static final Instrument BAGPIPE = new Instrument("Bagpipe");
    public static final Instrument FIDDLE = new Instrument("Fiddle");
    public static final Instrument SHANAI = new Instrument("Shanai");
    public static final Instrument TINKLE_BELL = new Instrument("Tinkle bell");
    public static final Instrument AGOGO = new Instrument("Agogo");
    public static final Instrument STEEL_DRUMS = new Instrument("Steel Drums");
    public static final Instrument WOOD_BLOCK = new Instrument("Wood Block");
    public static final Instrument TAIKO_DRUM = new Instrument("Taiko Drum");
    public static final Instrument MELODIC_TOM = new Instrument("Melodic Tom");
    public static final Instrument SYNTH_DRUM = new Instrument("Synth Drum");
    public static final Instrument REVERSE_CYMBAL = new Instrument("Reverse Cymbal");
    public static final Instrument GUITAR_FRETN_OISE = new Instrument("Guitar Fret Noise");
    public static final Instrument BREATH_NOISE = new Instrument("Breath Noise");
    public static final Instrument SEASHORE = new Instrument("Seashore");
    public static final Instrument BIRD_TWEET = new Instrument("Bird Tweet");

    public static final Instrument DEFAULT = ACOUSTIC_GRAND_PIANO;

    private String name;
    private Set<Pitch> possiblePitches;
    private Set<Technique> possibleTechniques;

    public Instrument(String name) {
        this.name = name;
        GeneralMIDIInstrumentSet.add(this);
    }

    public static Instrument getGeneralMIDIInstrument(int number) {return GeneralMIDIInstrumentSet.get(number);}

    @Override
    public String toString() {
        return name;
    }
}
