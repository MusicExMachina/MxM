package form.events;

import form.timelines.Score;

public interface IScoreEvent extends IMusicEvent {
    Score getScore();
}
