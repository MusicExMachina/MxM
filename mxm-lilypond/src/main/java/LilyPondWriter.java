import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by celenp on 5/3/2017.
 */
public class LilyPondWriter {
    public String rhythmTreeToLilyPond() {
        String lilyPondString = "\\version \"2.18.2\" \\n \\score{ \\n {";


        /* c'8 d' e' f' g' a' b' c'' */


        return lilyPondString + "}\n\n\\layout{}\\midi{}\n};";
    }

    public static void main(String args[]) {
        engrave();
    }

    public static void engrave() {
        try {
            // Run LilyPond
            String lilyPondPath     = "C:\\Program Files (x86)\\LilyPond\\usr\\bin\\lilypond.exe";
            String resourcesPath    = "mxm-lilypond/src/tests/resources/";
            Path curPath            = Paths.get("").toAbsolutePath();
            Path path               = Paths.get(curPath.toString(), resourcesPath);
            Runtime runtime         = Runtime.getRuntime();

            System.out.println(path.toString());
            // Get the resources folder
            File folder = new File(path.toString());
            File[] allFiles = folder.listFiles();

            for (int i = 0; i < allFiles.length; i++) {
                if (allFiles[i].isFile()) {
                    String fileName = allFiles[i].getName();
                    if(fileName.contains(".ly")) {
                        try {
                            // Wait for LilyPond to do its thing
                            String command          = lilyPondPath + " " + path + "\\" + fileName;
                            Process process         = runtime.exec(command);
                            process.waitFor();

                            // Get the LilyPond's command line output
                            StringBuilder output    = new StringBuilder();
                            BufferedReader reader   = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                            String line;
                            while ((line = reader.readLine())!= null) {
                                output.append(line).append("\n");
                            }
                            System.out.println("LilyPond: " + output);

                        } catch (IOException e) {
                            System.err.println("Could not open "+fileName+"!");
                            System.err.println(e);
                        }
                    }
                }
            }




            System.out.println("Completed!");

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
