package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Describes a measure on a Java package, as defined by an aggregation of Java
 * class source file measures
 *
 * @author jclaude
 */
public abstract class PackageMeasure extends Measure {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageMeasure.class);

    public PackageMeasure(String name) {
        super(name);
    }

    /**
     * Measures a Java package. Also measures all contained Java classes
     *
     * @param projectPath The absolute path to the project root
     * @param path The path of the Java file, relative to projectPath
     * @return The MeasureResult on the given package and all of the
     * MeasureResult for contained Java classes
     */
    public Collection<MeasureResult> measure(Path projectPath, Path path) {
        Path fullPath = projectPath.resolve(path);
        Collection<MeasureResult> measureResults = new ArrayList<>();

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
                MeasureResult packageMeasureResult = aggregate(classMeasureResults)
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
     * Gets the String package description from a given path.
     *
     * @param path The path of the Java package, relative to projectPath
     * @return The string (e.g. "org.mypackage.mysubpackage")
     */
    public static String getPackageDescription(Path path) {
        StringBuilder packageDescription = new StringBuilder();

        path.iterator().forEachRemaining(section -> {
            packageDescription.append(section.toString()).append('.');
        });

        return packageDescription.substring(0, packageDescription.length() - 1);
    }

    /**
     * Aggregates the given class measures into a single package measure.
     * <br>
     * If the implementation is a measure that counts the number of lines in a
     * Java package, then this method would sum the numeric results of all the
     * given MeasureResults
     *
     * @param measureResults MeasureResults on the contained Java class
     * @return
     */
    public abstract MeasureResult aggregate(Collection<MeasureResult> measureResults);

    /**
     * Gets the class measure related to this package measure. This indicates
     * which measure to run on all the contained Java class files
     *
     * @return the class measure
     */
    public abstract ClassMeasure getClassMeasure();
}
