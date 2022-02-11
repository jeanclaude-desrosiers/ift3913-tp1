package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.List;

/**
 * Measures comment density in a Java package.
 * <br>
 * paquet_DC = paquet_CLOC / paquet_LOC
 * <br>
 * See {@link PackageMeasureCLOC} and {@link PackageMeasureLOC}
 *
 * @author jclaude
 */
public class CompositeMeasureDC extends CompositeMeasure {

    public CompositeMeasureDC() {
        super("paquet_DC", new PackageMeasureCLOC(), new PackageMeasureLOC());
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
        return new ClassMeasureDC();
    }

}
