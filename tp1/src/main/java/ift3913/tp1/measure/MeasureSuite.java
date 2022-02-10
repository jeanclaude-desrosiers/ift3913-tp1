package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Set of package measures
 *
 * @author jclaude
 */
public enum MeasureSuite {
    EXAMPLE(new PackageMeasureExample()),
    ALL(new PackageMeasureCLOC(), new PackageMeasureLOC(), new PackageMeasureDC());

    private final PackageMeasure[] packageMeasures;

    private MeasureSuite(PackageMeasure... packageMeasures) {
        this.packageMeasures = packageMeasures;
    }

    /**
     * Runs all measures on a given Java package
     *
     * @param projectPath The absolute path to the project root
     * @param path The path of the Java file, relative to projectPath
     * @return All the MeasureResults for all measures on Java packages/classes
     */
    public Collection<MeasureResult> runSuite(Path projectPath, Path path) {
        Collection<MeasureResult> measureResults = new ArrayList<>();

        for (PackageMeasure packageMeasure : packageMeasures) {
            measureResults.addAll(packageMeasure.measure(projectPath, path));
        }

        return measureResults;
    }
}
