package ift3913.tp1.measure;

/**
 * An example of a class measure.
 * <br>
 * Counts the number of lines
 *
 * @author jclaude
 */
public class ClassMeasureExample extends ClassMeasure {

    private int count = 0;

    public ClassMeasureExample() {
        super("classe_lignes");
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
