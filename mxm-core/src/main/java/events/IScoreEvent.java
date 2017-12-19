package events;

import form.AbstractScore;

public interface IScoreEvent extends IMusicEvent {
    AbstractScore getScore();
}
