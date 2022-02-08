package ift3913.tp1.data;

import java.nio.file.Path;

/**
 * A simple data object holding the result of a measure.
 *
 * @author jclaude
 */
public class MeasureResult {

    /**
     * Name of the measure used to produce this MeasureResult
     */
    private String name;
    /**
     * Path to the object measured
     */
    private Path path;
    /**
     * Description of the object measured
     */
    private String description;
    /**
     * Numeric result of the measure on the object
     */
    private Number numericResult;

    public MeasureResult() {
    }

    public String getName() {
        return name;
    }

    public MeasureResult withName(String name) {
        this.name = name;

        return this;
    }

    public Path getPath() {
        return path;
    }

    public MeasureResult withPath(Path path) {
        this.path = path;

        return this;
    }

    public String getDescription() {
        return description;
    }

    public MeasureResult withDescription(String description) {
        this.description = description;

        return this;
    }

    public Number getNumericResult() {
        return numericResult;
    }

    public MeasureResult withNumericResult(Number numericResult) {
        this.numericResult = numericResult;

        return this;
    }

    @Override
    public String toString() {
        return "MeasureResult{"
                + "name=" + name
                + ", path=" + path
                + ", description=" + description
                + ", numericResult=" + numericResult + '}';
    }

}
