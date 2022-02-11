package ift3913.tp1.measure;

/**
 * Measures comment density in a Java class.
 * <br>
 * classe_DC = classe_CLOC / classe_LOC
 * <br>
 * See {@link ClassMeasureCLOC} and {@link ClassMeasureLOC}
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
