/*
import io.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LilyPondLeadSheetParser extends Reader {

    @Override
    public TraditionalScore read(String filename) {
        return null;
    }

    public static void main(String[] args) {
        String pathStr = "mxm-lilypond/src/tests/resources/realBookEntries";

        // Get the path to the resource we want
        Path curPath = Paths.get("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.get(curPathStr, pathStr);
        Charset charset = Charset.forName("UTF-8");

        System.out.println(path);

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();

        LilyPondLeadSheetParser parser = new LilyPondLeadSheetParser();

        File file = allFiles[0];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parser.parse(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(String inputString) {
        String[] blocks = inputString.split("-P");
        for(String string : blocks) {
            System.out.println(">\t" + string.trim());
        }
    }

    public void parseBookPart() {

    }

    public void parseChordMode() {

    }
}
*/