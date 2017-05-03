import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by celenp on 5/3/2017.
 */
public class LilyPondTools {

    public static void main(String args[]) {
        engrave();
    }

    public static void engrave() {
        try {
            // Run LilyPond
            String target   = "C:\\Program Files (x86)\\LilyPond\\usr\\bin\\lilypond.exe";
            Runtime rt      = Runtime.getRuntime();
            Process proc    = rt.exec(target);

            // Wait for LilyPond to do its thing
            proc.waitFor();

            // Get the LilyPond's command line output
            StringBuilder output    = new StringBuilder();
            BufferedReader reader   = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line).append("\n");
            }
            System.out.println("### " + output);


            System.out.println("Completed!");

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
