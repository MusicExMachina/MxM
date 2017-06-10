package readers;

import form.compositionTypes.LeadSheet;
import io.Reader;

/**
 * This class reads lead sheets based on OpenBook's formatting (available at https://github.com/veltzer/openbook) and
 * converts them into MxM's internal lead sheet class. Note that this is simply a dressing-up of LilyPond data that
 * allows us to more quickly and easily understand what's going on at a given moment.
 */
public class LilyPondLeadSheetReader implements Reader<LeadSheet> {
    @Override
    static LeadSheet read(String filename) {
        return null;
    }
}
