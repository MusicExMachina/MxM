package composition;

import base.Harmony;
import base.Pitch;
import base.time.ITime;

public class ProgressionBuilder {

    public LineBuilder add(ITime length) {
        // Add a rest
        latestTime = latestTime.plus(length);
        return this;
    }
    public LineBuilder add(Pitch pitch, ITime length) {
        // Add a note
        latestTime = latestTime.plus(length);
        return this;
    }
    public LineBuilder add(Harmony harmony, ITime length) {
        // Add a note
        latestTime = latestTime.plus(length);
        return this;
    }
}
