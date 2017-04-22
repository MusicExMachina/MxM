import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class MidiTest {

    /*
    * Tests MidiTools' ability to read download midi files from the internet.
     */
    @Test
    public void loadTest() {
        String path = "src/tests/resources/";

        // Get the path to the resource we want
        Path curPath = Paths.get("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.get(curPathStr, path);
        Charset charset = Charset.forName("UTF-8");

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();

        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isFile()) {
                String fileName = allFiles[i].getName();
                if(fileName.contains("midi")) {
                    try {
                        MidiTools.download(path + fileName);
                    } catch (IOException e) {
                        System.err.println("Could not open "+fileName+"!");
                        System.err.println(e);
                    } catch (InvalidMidiDataException e) {
                        System.err.println("Could not load midi file! (from "+fileName+")");
                        System.err.println(e);
                    }
                }
            } else if (allFiles[i].isDirectory()) {
                System.out.println("Directory " + allFiles[i].getName());
            }
        }
    }

    /*
    * Tests MidiTools' ability to read download midi files from the internet.
     */
    @Test
    public void sequentialDownloadTest() {

        // The file that holds all the urls we're going to download from
        String filename = "src/tests/resources/url_sources.txt";

        // Get the path to the resource we want
        Path curPath = Paths.get("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.get(curPathStr, filename);
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

    /*
    * Tests MidiTools' ability to read download midi files from the internet.
     */
    @Test
    public void concurrentDownloadTest() {

        // The file that holds all the urls we're going to download from
        String filename = "src/tests/resources/url_sources.txt";

        // Get the path to the resource we want
        Path curPath = Paths.get("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.get(curPathStr, filename);
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
}
