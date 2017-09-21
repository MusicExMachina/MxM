/*
public abstract class MidiTools
{
    public static void debug() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            Log.debug("MidiTools Sequencer","receivers",sequencer.getReceivers());
            Log.debug("MidiTools Sequencer","transmitters",sequencer.getTransmitters());
            Log.debug("MidiTools System","synthesizer",MidiSystem.getSynthesizer());
            Log.debug("MidiTools System","available instruments", Arrays.asList(MidiSystem.getSynthesizer().getAvailableInstruments()));
            Log.debug("MidiTools System","loaded instruments", Arrays.asList(MidiSystem.getSynthesizer().getLoadedInstruments()));
        } catch (MidiUnavailableException e) {
            Log.warning("MidiTools",e.getMessage());
        }
    }

    public static @NotNull Sequence load (@NotNull String fileName) throws IOException, InvalidMidiDataException {
        MidiFileReader reader = new StandardMidiFileReader();
        return reader.getSequence(new FileInputStream(fileName));
    }

    public static List<Sequence> loadAll (List<String> fileNames) throws IOException, InvalidMidiDataException {
        List<Sequence> toReturn = new ArrayList<>();
        // TODO: Parallelize this
        for(String fileName : fileNames) {
            MidiFileReader reader = new StandardMidiFileReader();
            toReturn.add(reader.getSequence(new FileInputStream(fileName)));
        }
        return toReturn;
    }

    public static Sequence download (String url) throws IOException, InvalidMidiDataException {
        MidiFileReader reader = new StandardMidiFileReader();
        return reader.getSequence(new URL(url));
    }

    public static List<Sequence> downloadAll (List<String> urls) throws IOException, InvalidMidiDataException {
        List<Sequence> toReturn = new ArrayList<>();
        // TODO: Parallelize this
        for(String url : urls) {
            MidiFileReader reader = new StandardMidiFileReader();
            toReturn.add(reader.getSequence(new URL(url)));
        }
        return toReturn;
    }

    public static @NotNull IPassage parse(@NotNull Sequence sequence) {
        // Spawn off a parser object
        MidiReader midiReader = new MidiReader();
        return midiReader.read(sequence);
    }

    public static @NotNull Sequence write(@NotNull IPassage passage) {
        // Spawn off a parser object
        MidiWriter midiWriter = new MidiWriter();
        return midiWriter.run(form);
    }

    public static void play(@NotNull Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();
    }
}
*/