import properties.sound.Pitch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class OtherTest {
    public static int NUM_PITCHES = 1000000;
    public static int SAMPLES = 10000;
    public static int FACTOR = 1000000;

    public static void main(String[] args) {
        List<Pitch> pitches = new ArrayList<>();

        long startTime;
        long elapsedTime;
        long totalTime;

        // INITIALIZE
        System.out.println("Initialize pitches");
        startTime = System.nanoTime();
        IntStream.range(0,NUM_PITCHES).forEach(i -> pitches.add(Pitch.random()));
        elapsedTime = System.nanoTime() - startTime;

        System.out.println("");
        System.out.println("====================================");
        System.out.println("Time elapsed: " + elapsedTime);

        // GET THE MAX PITCH, SERIAL
        System.out.println("");
        System.out.println("====================================");
        System.out.println("Max Serial");
        totalTime = 0;
        for(int i = 0; i < SAMPLES; i++) {
            startTime = System.nanoTime();
            Pitch p1 = pitches.stream().max(Pitch::compareTo).orElseGet(null);
            elapsedTime = System.nanoTime() - startTime;
            totalTime += elapsedTime;
        }
        System.out.println("Average time elapsed: " + (totalTime/SAMPLES)/ FACTOR);

        // GET THE MAX PITCH, PARALLEL
        System.out.println("");
        System.out.println("====================================");
        System.out.println("Max Parallel");
        totalTime = 0;
        for(int i = 0; i < SAMPLES; i++) {
            startTime = System.nanoTime();
            Pitch p2 = pitches.parallelStream().max(Pitch::compareTo).orElseGet(null);
            elapsedTime = System.nanoTime() - startTime;
            totalTime += elapsedTime;
        }
        System.out.println("Average time elapsed: " + (totalTime/SAMPLES)/FACTOR);
    }
}
