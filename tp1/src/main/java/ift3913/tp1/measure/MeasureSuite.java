package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author jclaude
 */
public enum MeasureSuite {
    ALL(new PackageMeasureLOC());
    
    private final PackageMeasure[] packageMeasures;
    
    private MeasureSuite(PackageMeasure... packageMeasures) {
        this.packageMeasures = packageMeasures;
    }
    
    public Collection<MeasureResult> runSuite(Path projectPath, Path path) {
        Collection<MeasureResult> measureResults = new ArrayList<>();
        
        for(PackageMeasure packageMeasure : packageMeasures) {
            measureResults.addAll(packageMeasure.measure(projectPath, path));
        }
        
        return measureResults;
    }
}
