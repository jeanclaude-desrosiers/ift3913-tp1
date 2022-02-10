package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jclaude
 */
public class ClassMeasureBC extends ClassMeasure {

    private final ClassMeasureDC dc = new ClassMeasureDC();
    private final ClassMeasureWMC wmc = new ClassMeasureWMC();

    public ClassMeasureBC() {
        super("classe_BC");
    }

    @Override
    public void consumeLine(String line) {
        dc.consumeLine(line);
        wmc.consumeLine(line);
    }

    @Override
    public Number getNumericResult() {
        return dc.getNumericResult().doubleValue()
                / wmc.getNumericResult().doubleValue();
    }

}
