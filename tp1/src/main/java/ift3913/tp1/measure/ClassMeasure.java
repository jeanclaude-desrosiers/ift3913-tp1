package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jclaude
 */
public abstract class ClassMeasure extends Measure {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassMeasure.class);

    public ClassMeasure(String name) {
        super(name);
    }

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

    private String getClassDescription(Path path) {
        String filename = path.getFileName().toString();

        return filename.split("\\.")[0];
    }

    public abstract void consumeLine(String line);

    public abstract Number getNumericResult();
}
