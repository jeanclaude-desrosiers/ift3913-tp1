package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jclaude
 */
public class ClassMeasureDC extends ClassMeasure {

    private final ClassMeasureCLOC cloc = new ClassMeasureCLOC();
    private final ClassMeasureLOC loc = new ClassMeasureLOC();

    public ClassMeasureDC() {
        super("classe_DC");
    }

    @Override
    public void consumeLine(String line) {
        cloc.consumeLine(line);
        loc.consumeLine(line);
    }

    @Override
    public Number getNumericResult() {
        return cloc.getNumericResult().doubleValue()
                / loc.getNumericResult().doubleValue();
    }

}
