package form;

/**
 * Scores are slightly-more-formal Passages, meaning that they contain metadata about their composer, their name, so on
 * and so forth. As a subclass of IPassage, however, Scores can be excerpted to form other Passages (just not other
 * Scores, of course).
 */
public class Score implements IPassage {

    // METADATA
    private String title;
    private String composer;
    private String style;

    public Score(Score score) {
        super(score);
    }

    public String getTitle() {
        return title;
    }

    public String getComposer() {
        return composer;
    }

    public String toString() {
        return title + " by " + composer + " full score";
    }
}