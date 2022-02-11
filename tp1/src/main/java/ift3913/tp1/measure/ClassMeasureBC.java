package ift3913.tp1.measure;

/**
 * Measures how well commented a Java class is.
 * <br>
 * classe_BC = classe_DC / classe_WMC
 * <br>
 * See {@link ClassMeasureDC} and {@link ClassMeasureWMC}
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
