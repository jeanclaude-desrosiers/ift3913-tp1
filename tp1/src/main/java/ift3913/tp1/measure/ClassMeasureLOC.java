package ift3913.tp1.measure;

/**
 *
 * @author jclaude
 */
public class ClassMeasureLOC extends ClassMeasure {

    private int count;

    public ClassMeasureLOC() {
        super("classe_LOC");

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
