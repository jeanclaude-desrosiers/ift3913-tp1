package ift3913.tp1.app;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.measure.MeasureSuite;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Explores a Java project by running a suite of measures on its
 * packages/classes
 *
 * @author jclaude
 */
public class ProjectExplorer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectExplorer.class);

    private final Path basePath;
    private final MeasureSuite measureSuite;

    public ProjectExplorer(Path basePath) {
        this.basePath = basePath.toAbsolutePath().normalize();
        measureSuite = MeasureSuite.EXAMPLE;
    }

    /**
     * Explores the Java project pointed by this ProjectExplorer's basePath.
     * Recursively goes through every directory in the project, and runs the
     * MeasureSuite on every directory that's a Java package
     *
     * @return All of the collected MeasureResults
     */
    public Collection<MeasureResult> explore() {
        Collection<MeasureResult> measureResults = new ArrayList<>();
        Deque<Path> directoryDeque = new ArrayDeque<>();
        directoryDeque.addLast(basePath);

        Path nextDirectory;
        while ((nextDirectory = directoryDeque.pollFirst()) != null) {
            if (isJavaPackage(nextDirectory)) {
                LOGGER.trace("Java Package [" + basePath.relativize(nextDirectory) + "]");

                measureResults.addAll(measureSuite.runSuite(basePath, basePath.relativize(nextDirectory)));
            }

            try {
                directoryDeque.addAll(Files
                        .list(nextDirectory)
                        .filter(Files::isDirectory)
                        .map(Path::toAbsolutePath)
                        .collect(Collectors.toList()));
            } catch (IOException ex) {
                LOGGER.error("Could not list sub-directories", ex);
            }
        }

        return measureResults;
    }

    /**
     * Checks if a given directory contains at least one *.java file
     *
     * @param path directory to check
     * @return true if at least one Java file, false otherwise
     */
    public boolean isJavaPackage(Path path) {
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

    public Path getBasePath() {
        return basePath;
    }

}
