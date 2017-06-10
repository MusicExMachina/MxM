package form.scoreTypes;

import form.Score;
import form.partTypes.StandardPart;

/**
 * Created by celenp on 6/9/2017.
 */
public class SATBScore extends Score {

    private StandardPart sopranoPart;
    private StandardPart altoPart;
    private StandardPart tenorPart;
    private StandardPart bassPart;

    public SATBScore() {
        sopranoPart = new StandardPart(null);
        altoPart    = new StandardPart(null);
        tenorPart   = new StandardPart(null);
        bassPart    = new StandardPart(null);
        parts.add(sopranoPart);
        parts.add(altoPart);
        parts.add(tenorPart);
        parts.add(bassPart);
    }

    public StandardPart getSopranoPart() {
        return sopranoPart;
    }

    public StandardPart getAltoPart() {
        return altoPart;
    }

    public StandardPart getTenorPart() {
        return tenorPart;
    }

    public StandardPart getBassPart() {
        return bassPart;
    }
}
