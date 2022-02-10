package ift3913.tp1.measure;

/**
 *
 * @author jclaude
 */
public class ClassMeasureCLOC extends ClassMeasure {

    private int count;

    public ClassMeasureCLOC() {
        super("classe_CLOC");

        count = 0;
    }

    @Override
    public void consumeLine(String line) {
        count++;
    }

    @Override
    public Number getNumericResult() {
        return count;
    }

}
