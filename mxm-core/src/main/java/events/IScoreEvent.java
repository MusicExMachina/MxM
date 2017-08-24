package events;

import com.sun.istack.internal.NotNull;
import passage.Score;

public interface IScoreEvent extends IMusicEvent {
    Score getScore();
}
