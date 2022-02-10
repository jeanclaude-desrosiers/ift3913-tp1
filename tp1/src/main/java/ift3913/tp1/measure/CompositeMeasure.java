package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Describes a measure on a Java package, as defined by an aggregation of Java
 * class source file measures
 *
 * @author jclaude
 */
public abstract class CompositeMeasure extends Measure {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeMeasure.class);
    private final List<Measure> subMeasures;

    public CompositeMeasure(String name, Measure... subMeasures) {
        super(name);
        this.subMeasures = Arrays.asList(subMeasures);
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

        if (Files.isDirectory(fullPath)) {
            try {
                // First measured all files which are Java classes ...
                Files.list(fullPath)
                        .filter(filePath -> filePath.toString().endsWith(".java"))
                        .filter(Files::isRegularFile)
                        .map(filePath -> getClassMeasure().measureClass(projectPath, projectPath.relativize(filePath)))
                        .forEach(measureResults::add);

                // ... Then measure with all subMeasures ...
                List<MeasureResult> subMeasureResults = subMeasures
                        .stream()
                        .flatMap(measure -> measure.measure(projectPath, path).stream())
                        .filter(measureResult -> measureResult.getType() == MeasureResultType.PACKAGE)
                        .collect(Collectors.toList());

                // ... Then aggregate into one composite measure
                MeasureResult packageMeasureResult = aggregate(subMeasureResults)
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

    /**
     * Aggregates the results for given sub measures into a single composed
     * measure result.
     *
     * @param measureResults MeasureResults on the contained sub measures
     * @return
     */
    public abstract MeasureResult aggregate(List<MeasureResult> measureResults);

    /**
     * Gets the class measure related to this package measure. This indicates
     * which measure to run on all the contained Java class files
     *
     * @return the class measure
     */
    public abstract ClassMeasure getClassMeasure();
}
