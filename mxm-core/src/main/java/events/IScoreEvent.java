package events;

import form.score.AbstractScore;

public interface IScoreEvent extends IMusicEvent {
    AbstractScore getScore();
}
