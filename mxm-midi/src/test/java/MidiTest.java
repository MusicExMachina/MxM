import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/*
public class MidiTest {

    @general.Test
    public void sequentialLoadTest() {
        String pathStr = "src/tests/resources/";

        // Get the path to the resource we want
        Path curPath = Paths.of("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.of(curPathStr, pathStr);
        Charset charset = Charset.forName("UTF-8");

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();

        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isFile()) {
                String fileName = allFiles[i].getName();
                if(fileName.contains("midi")) {
                    try {
                        MidiTools.load(path + "\\" + fileName);
                    } catch (IOException e) {
                        System.err.println("Could not open "+fileName+"!");
                        System.err.println(e);
                    } catch (InvalidMidiDataException e) {
                        System.err.println("Could not load midi file! (from "+fileName+")");
                        System.err.println(e);
                    }
                }
            }
        }
    }
    @general.Test
    public void concurrentLoadTest() {
        String pathStr = "src/tests/resources/";

        // Get the path to the resource we want
        Path curPath = Paths.of("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.of(curPathStr, pathStr);
        Charset charset = Charset.forName("UTF-8");

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();
        List<String> allFilenames = new ArrayList<>();

        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isFile()) {
                String fileName = allFiles[i].getName();
                if(fileName.contains("midi")) {
                    allFilenames.add(path + "\\" + fileName);
                }
            }
        }

        try {
            MidiTools.loadAll(allFilenames);
        }
        catch (IOException e) {
            System.err.println("Could not open file!");
            System.err.println(e);
        }
        catch (InvalidMidiDataException e) {
            System.err.println("Could not load midi file!");
            System.err.println(e);
        }
    }
    @general.Test
    public void sequentialDownloadTest() {

        // The file that holds all the urls we're going to download from
        String filename = "src/tests/resources/url_sources.txt";

        // Get the path to the resource we want
        Path curPath = Paths.of("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.of(curPathStr, filename);
        Charset charset = Charset.forName("UTF-8");

        // Read each line in the sources file
        String line = "";
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            while ((line = reader.readLine()) != null ) {
                // We don't need to save this data
                MidiTools.download(line);
            }
        }
        catch (IOException e) {
            System.err.println("Could not open "+filename+"!");
            System.err.println(e);
        } catch (InvalidMidiDataException e) {
            System.err.println("Could not download midi file! (from "+line+")");
            System.err.println(e);
        }
    }
    @general.Test
    public void concurrentDownloadTest() {

        // The file that holds all the urls we're going to download from
        String filename = "src/tests/resources/url_sources.txt";

        // Get the path to the resource we want
        Path curPath = Paths.of("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.of(curPathStr, filename);
        Charset charset = Charset.forName("UTF-8");

        // Read each line in the sources file
        List<String> lines = new ArrayList<>();
        String line = "";
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            MidiTools.downloadAll(lines);
        }
        catch (IOException e) {
            System.err.println("Could not open "+filename+"!");
            System.err.println(e);
        } catch (InvalidMidiDataException e) {
            System.err.println("Could not download midi file! (from "+line+")");
            System.err.println(e);
        }
    }
    @general.Test
    public void playTest() {
        String pathStr = "src/tests/resources/";

        // Get the path to the resource we want
        Path curPath = Paths.of("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.of(curPathStr, pathStr);
        Charset charset = Charset.forName("UTF-8");

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();

        try {
            MidiTools.play(MidiTools.load(allFiles[0].getPath()));
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @general.Test
    public void parseTest() {
        String pathStr = "src/tests/resources/";

        // Get the path to the resource we want
        Path curPath = Paths.of("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.of(curPathStr, pathStr);
        Charset charset = Charset.forName("UTF-8");

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();

        try {
            MidiTools.parse(MidiTools.load(allFiles[0].getPath()));
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/