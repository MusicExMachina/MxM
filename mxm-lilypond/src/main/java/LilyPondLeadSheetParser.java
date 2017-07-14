import form.Score;
import io.Reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by celenp on 5/20/2017.
 */
public class LilyPondLeadSheetParser extends Reader<Score> {

    @Override
    public Score read(String filename) {
        return null;
    }

    public static int main(String[] args) {
        String pathStr = "src/tests/resources/realBookEntries";

        // Get the path to the resource we want
        Path curPath = Paths.get("").toAbsolutePath();
        String curPathStr = curPath.toString();
        Path path = Paths.get(curPathStr, pathStr);
        Charset charset = Charset.forName("UTF-8");

        // Get the resources folder
        File folder = new File(path.toString());
        File[] allFiles = folder.listFiles();

        File file = allFiles[0];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parse(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(String inputString) {
        String[] blocks = inputString.split("{}");
        for(String string : blocks) {
            System.out.println(">\t" + string);
        }
    }

    public void parseBookPart() {

    }

    public void parseChordMode() {

    }
}
