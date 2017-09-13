package form.events;

import form.timelines.AbstractScore;

public interface IScoreEvent extends IMusicEvent {
    AbstractScore getScore();
}
