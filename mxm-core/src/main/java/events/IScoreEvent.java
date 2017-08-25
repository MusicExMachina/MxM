package events;

import org.jetbrains.annotations.NotNull;
import passage.Score;

public interface IScoreEvent extends IMusicEvent {
    Score getScore();
}
