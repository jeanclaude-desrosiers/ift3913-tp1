package ift3913.tp1.writer;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.util.Collection;

/**
 * Interface for the process of writing all MeasureResults
 *
 * @author jclaude
 */
public interface MeasureResultWriter {

    /**
     * Writes all the MeasureResults
     *
     * @param measureResults
     */
    public void write(Collection<MeasureResult> measureResults, MeasureResultType type);
}
