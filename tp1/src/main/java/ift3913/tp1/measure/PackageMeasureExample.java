package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * An example of a package measure.
 * <br>
 * Counts the number of lines
 *
 * @author jclaude
 */
public class PackageMeasureExample extends PackageMeasure {

    public PackageMeasureExample() {
        super("paquet_lignes", false);
    }

    @Override
    public MeasureResult aggregate(Collection<MeasureResult> measureResults) {
        int sum = measureResults
                .stream()
                .map(MeasureResult::getNumericResult)
                .collect(Collectors.summingInt(Number::intValue));

        return new MeasureResult().withNumericResult(sum);
    }

    @Override
    public ClassMeasure getClassMeasure() {
        return new ClassMeasureExample();
    }

}
