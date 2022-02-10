package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Parent class of all types of measures. Handles the 'name of the measure'
 * attribute that is common to all measures
 *
 * @author jclaude
 */
public abstract class Measure {

    /**
     * Name of the measure (and not the object(s) it measures)
     */
    private final String name;

    public Measure(String name) {
        this.name = name;
    }

    public abstract Collection<MeasureResult> measure(Path projectPath, Path path);

    public String getName() {
        return name;
    }
    
    /**
     * Gets the String package description from a given path.
     *
     * @param path The path of the Java package, relative to projectPath
     * @return The string (e.g. "org.mypackage.mysubpackage")
     */
    public static final String getPackageDescription(Path path) {
        StringBuilder packageDescription = new StringBuilder();

        path.iterator().forEachRemaining(section -> {
            packageDescription.append(section.toString()).append('.');
        });

        return packageDescription.substring(0, packageDescription.length() - 1);
    }
    
    /**
     * Gets the String class description from a given path.
     *
     * @param path The path of the Java file, relative to projectPath
     * @return The string (e.g. "org.mypackage.myclass")
     */
    public static final String getClassDescription(Path path) {
        StringBuilder packageDescription = new StringBuilder();

        path.iterator().forEachRemaining(section -> {
            // The split() is used for the filename only, it will discard the .java part
            packageDescription.append(section.toString().split("\\.")[0]).append('.');
        });

        return packageDescription.substring(0, packageDescription.length() - 1);
    }
}
