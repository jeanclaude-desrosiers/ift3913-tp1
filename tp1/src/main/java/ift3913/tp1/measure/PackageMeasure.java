package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
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

    /**
     * A non-recursive package aggregates all the measures of contained Java
     * class, while a recursive one will also aggregate the measure of contained
     * Java packages
     */
    private final boolean recursive;

    public PackageMeasure(String name, boolean recursive) {
        super(name);

        this.recursive = recursive;
    }

    /**
     * Measures a Java package. Also measures all contained Java classes
     *
     * @param projectPath The absolute path to the project root
     * @param path        The path of the Java file, relative to projectPath
     *
     * @return The MeasureResult on the given package and all of the
     *         MeasureResult for contained Java classes
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

                if (recursive) {
                    // ... optionally measure subpackages ...
                    Files.list(fullPath)
                            .filter(Files::isDirectory)
                            .filter(PackageMeasure::isJavaPackage)
                            .flatMap(dirPath -> this.measure(projectPath, projectPath.relativize(dirPath)).stream())
                            .filter(measureResult -> measureResult.getType() == MeasureResultType.PACKAGE)
                            .forEach(measureResults::add);
                }

                // ... Then aggregate into one package measure
                MeasureResult packageMeasureResult = aggregate(measureResults)
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
     * Checks if a given directory contains at least one *.java file
     *
     * @param path directory to check
     *
     * @return true if at least one Java file, false otherwise
     */
    public static boolean isJavaPackage(Path path) {
        try {
            return Files.list(path)
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .filter(filePath -> filePath.toString().endsWith(".java"))
                    .count() > 0;
        } catch (IOException ex) {
            LOGGER.error("Could not check Java package", ex);
        }

        return false;
    }

    /**
     * Aggregates the given class measures into a single package measure.
     * <br>
     * If the implementation is a measure that counts the number of lines in a
     * Java package, then this method would sum the numeric results of all the
     * given MeasureResults
     *
     * @param measureResults MeasureResults on the contained Java class
     *
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
