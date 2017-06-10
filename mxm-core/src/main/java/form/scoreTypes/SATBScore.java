package form.scoreTypes;

import form.Score;
import form.partTypes.StandardPart;

/**
 * Created by celenp on 6/9/2017.
 */
public class SATBScore extends Score {
    private StandardPart sopranoPart = new StandardPart();
    private StandardPart altoPart = new StandardPart();
    private StandardPart tenorPart = new StandardPart();
    private StandardPart bassPart = new StandardPart();

    public SATBScore() {
        sopranoPart = new StandardPart();
        altoPart    = new StandardPart();
        tenorPart   = new StandardPart();
        bassPart    = new StandardPart();
        parts.add(sopranoPart);
    }
}
