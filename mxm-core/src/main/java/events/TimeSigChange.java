package events;

import time.Time;

/**
 * Created by celenp on 6/11/2017.
 */
public class TimeSigChange implements MusicEvent {

    @Override
    public Time getStart() {
        return null;
    }

    @Override
    public int compareTo(MusicEvent o) {
        return 0;
    }

    @Override
    public int compare(MusicEvent o1, MusicEvent o2) {
        return 0;
    }
}
