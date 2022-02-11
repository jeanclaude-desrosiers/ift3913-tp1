package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Measures the number of lines with code on it, in a Java package.
 *
 * @author jclaude
 */
public class PackageMeasureLOC extends PackageMeasure {

    public PackageMeasureLOC() {
        super("paquet_LOC", false);
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
        return new ClassMeasureLOC();
    }

}
