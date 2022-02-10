package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
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
    public MeasureResult measureClass(Path projectPath, Path path) {
        MeasureResult measureResult = new MeasureResult()
                .withName(getName())
                .withPath(path)
                .withDescription(getClassDescription(path))
                .withType(MeasureResultType.CLASS);

        try {
            Path fullPath = projectPath.resolve(path);
            Files.lines(fullPath).forEachOrdered(this::consumeLine);

            measureResult.withNumericResult(getNumericResult());
        } catch (IOException ex) {
            LOGGER.error("Could not read Java source file", ex);
        }

        return measureResult;
    }

    @Override
    public Collection<MeasureResult> measure(Path projectPath, Path path) {
        Collection<MeasureResult> measureResults = new ArrayList<>();
        measureResults.add(measureClass(projectPath, path));

        return measureResults;
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
