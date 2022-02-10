package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;

/**
 *
 * @author jclaude
 */
public class PackageMeasureCLOC extends PackageMeasure {

    public PackageMeasureCLOC() {
        super("paquet_lignes");
    }

    @Override
    public MeasureResult aggregate(Collection<MeasureResult> measureResults) {
        int sum = 0;

        for (MeasureResult measureResult : measureResults) {
            sum += measureResult.getNumericResult().intValue();
        }

        return new MeasureResult().withNumericResult(sum);
    }

    @Override
    public ClassMeasure getClassMeasure() {
        return new ClassMeasureCLOC();
    }

}
