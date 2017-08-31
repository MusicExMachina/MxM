package form.musicEvents;

import form.Score;

public interface IScoreEvent extends IMusicEvent {
    Score getScore();
}
