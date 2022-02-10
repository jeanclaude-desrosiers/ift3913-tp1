package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import static ift3913.tp1.measure.PackageMeasure.getPackageDescription;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jclaude
 */
public class PackageMeasureDC extends PackageMeasure {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageMeasureDC.class);

    private final PackageMeasureCLOC cloc = new PackageMeasureCLOC();
    private final PackageMeasureLOC loc = new PackageMeasureLOC();

    public PackageMeasureDC() {
        super("paquet_DC");
    }

    /**
     * Measures a Java package. Also measures all contained Java classes
     *
     * @param projectPath The absolute path to the project root
     * @param path The path of the Java file, relative to projectPath
     * @return The MeasureResult on the given package and all of the
     * MeasureResult for contained Java classes
     */
    @Override
    public Collection<MeasureResult> measure(Path projectPath, Path path) {
        Path fullPath = projectPath.resolve(path);
        Collection<MeasureResult> measureResults = new ArrayList<>();

        MeasureResult clocMeasureResult = cloc
                .measure(projectPath, path)
                .stream()
                .filter(measureResult -> measureResult.getType() == MeasureResultType.PACKAGE)
                .findFirst().orElseThrow();
        MeasureResult locMeasureResult = loc
                .measure(projectPath, path)
                .stream()
                .filter(measureResult -> measureResult.getType() == MeasureResultType.PACKAGE)
                .findFirst().orElseThrow();

        if (Files.isDirectory(fullPath)) {
            try {
                // First measured all files which are Java classes ...
                Collection<MeasureResult> classMeasureResults = Files
                        .list(fullPath)
                        .filter(filePath -> filePath.toString().endsWith(".java"))
                        .filter(Files::isRegularFile)
                        .map(filePath -> getClassMeasure().measure(projectPath, projectPath.relativize(filePath)))
                        .collect(Collectors.toList());
                measureResults.addAll(classMeasureResults);

                // ... Then aggregate into one package measure
                Number dc = clocMeasureResult.getNumericResult().doubleValue()
                        / locMeasureResult.getNumericResult().doubleValue();
                MeasureResult packageMeasureResult
                        = new MeasureResult().withNumericResult(dc)
                                .withName(getName())
                                .withPath(path)
                                .withDescription(getPackageDescription(path))
                                .withType(MeasureResultType.PACKAGE);
                measureResults.add(packageMeasureResult);
            } catch (IOException ex) {
                LOGGER.error("Could not list files", ex);
            }
        }

        return measureResults;
    }

    @Override
    public MeasureResult aggregate(Collection<MeasureResult> measureResults) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClassMeasure getClassMeasure() {
        return new ClassMeasureDC();
    }

}
