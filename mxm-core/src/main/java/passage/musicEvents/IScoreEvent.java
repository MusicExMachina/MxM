package passage.musicEvents;

import passage.Score;

public interface IScoreEvent extends IMusicEvent {
    Score getScore();
}
