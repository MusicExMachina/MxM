package form.events;

import form.Score;

public interface IScoreEvent extends IMusicEvent {
    Score getScore();
}
