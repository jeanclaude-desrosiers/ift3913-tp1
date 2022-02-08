package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Describes a measure on a single Java class source file
 *
 * @author jclaude
 */
public abstract class ClassMeasure extends Measure {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassMeasure.class);

    public ClassMeasure(String name) {
        super(name);
    }

    /**
     * Measures the source file of a single Java class.
     *
     * @param projectPath The absolute path to the project root
     * @param path The path of the Java file, relative to projectPath
     * @return The MeasureResult on the given file
     */
    public final MeasureResult measure(Path projectPath, Path path) {
        MeasureResult measureResult = new MeasureResult()
                .withName(getName())
                .withPath(path)
                .withDescription(getClassDescription(path));

        try {
            Path fullPath = projectPath.resolve(path);
            Files.lines(fullPath).forEachOrdered(this::consumeLine);

            measureResult.withNumericResult(getNumericResult());
        } catch (IOException ex) {
            LOGGER.error("Could not read Java source file", ex);
        }

        return measureResult;
    }

    /**
     * Gets the String class description from a given path.
     *
     * @param path The path of the Java file, relative to projectPath
     * @return The string (e.g. "org.mypackage.myclass")
     */
    private String getClassDescription(Path path) {
        StringBuilder packageDescription = new StringBuilder();

        path.iterator().forEachRemaining(section -> {
            // The split() is used for the filename only, it will discard the .java part
            packageDescription.append(section.toString().split("\\.")[0]).append('.');
        });

        return packageDescription.substring(0, packageDescription.length() - 1);
    }

    /**
     * Consumes the line to compute the measure. This is called on every line
     * (in-order) of the Java file being measured.
     * <br>
     * If the implementation is a measure that counts the number of lines in a
     * Java file, then this method would increment some kind of internal
     * counter, every time it is called
     *
     * @param line
     */
    public abstract void consumeLine(String line);

    /**
     * Gets the numeric result of the measure, as of right now. This is called
     * when the entire Java file has been traversed
     * <br>
     * If the implementation is a measure that counts the number of lines in a
     * Java file, then this method would return some kind of internal counter
     * that had been incremented by consumeLine()
     *
     * @return the result
     */
    public abstract Number getNumericResult();
}
