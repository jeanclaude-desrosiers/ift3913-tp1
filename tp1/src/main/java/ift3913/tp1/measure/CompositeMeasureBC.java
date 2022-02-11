package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.List;

/**
 * Measures how well commented a Java package is.
 * <br>
 * paquet_BC = paquet_DC / paquet_WMC
 * <br>
 * See {@link CompositeMeasureDC} and {@link PackageMeasureWCP}
 *
 * @author jclaude
 */
public class CompositeMeasureBC extends CompositeMeasure {

    public CompositeMeasureBC() {
        super("paquet_BC", new CompositeMeasureDC(), new PackageMeasureWCP());
    }

    @Override
    public MeasureResult aggregate(List<MeasureResult> measureResults) {
        Number packageDC = measureResults.get(0).getNumericResult();
        Number packageWCP = measureResults.get(1).getNumericResult();

        Number packageBC = packageDC.doubleValue()
                / packageWCP.doubleValue();

        return new MeasureResult().withNumericResult(packageBC);
    }

    @Override
    public ClassMeasure getClassMeasure() {
        return new ClassMeasureBC();
    }

}
