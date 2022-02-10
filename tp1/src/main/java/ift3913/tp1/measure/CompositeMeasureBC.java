package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.List;

/**
 *
 * @author jclaude
 */
public class CompositeMeasureBC extends CompositeMeasure {

    public CompositeMeasureBC() {
        super("paquet_BC", new CompositeMeasureDC(), new PackageMeasureWCP());
    }

    @Override
    public MeasureResult aggregate(List<MeasureResult> measureResults) {
        Number packageCLOC = measureResults.get(0).getNumericResult();
        Number packageLOC = measureResults.get(1).getNumericResult();

        Number packageDC = packageCLOC.doubleValue()
                / packageLOC.doubleValue();

        return new MeasureResult().withNumericResult(packageDC);
    }

    @Override
    public ClassMeasure getClassMeasure() {
        return new ClassMeasureBC();
    }

}
