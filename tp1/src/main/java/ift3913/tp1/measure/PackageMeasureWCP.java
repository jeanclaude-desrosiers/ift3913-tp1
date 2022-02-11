package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Measures the sum of all of the average McCabe cyclomatic complexity, for
 * methods in each Java class and Java sub-packages.
 *
 * @author jclaude
 */
public class PackageMeasureWCP extends PackageMeasure {

    public PackageMeasureWCP() {
        super("WCP", true);
    }

    @Override
    public MeasureResult aggregate(Collection<MeasureResult> measureResults) {
        double sum = measureResults
                .stream()
                .map(MeasureResult::getNumericResult)
                .collect(Collectors.summingDouble(Number::doubleValue));

        return new MeasureResult().withNumericResult(sum);
    }

    @Override
    public ClassMeasure getClassMeasure() {
        return new ClassMeasureWMC();
    }

}
