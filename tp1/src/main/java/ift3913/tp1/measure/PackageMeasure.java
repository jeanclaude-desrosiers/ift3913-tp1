package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 *
 * @author jclaude
 */
public abstract class PackageMeasure extends Measure {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageMeasure.class);

    public PackageMeasure(String name) {
        super(name);
    }

    public final Collection<MeasureResult> measure(Path projectPath, Path path) {
        Path fullPath = projectPath.resolve(path);
        Collection<MeasureResult> measureResults = new ArrayList<>();

        if (Files.isDirectory(fullPath)) {
            try {
                Collection<MeasureResult> classMeasureResults = Files
                        .list(fullPath)
                        .filter(Files::isRegularFile)
                        .map(Path::getFileName)
                        .filter(filePath -> filePath.toString().endsWith(".java"))
                        .map(filePath -> getClassMeasure().measure(fullPath, filePath))
                        .collect(Collectors.toList());
                measureResults.addAll(classMeasureResults);

                MeasureResult packageMeasureResult = aggregate(classMeasureResults)
                        .withName(getName())
                        .withPath(path)
                        .withDescription(getPackageDescription(path));
                measureResults.add(packageMeasureResult);
            } catch (IOException ex) {
                LOGGER.error("Could not list files", ex);
            }
        }

        return measureResults;
    }

    private String getPackageDescription(Path path) {
        StringBuilder packageDescription = new StringBuilder();

        path.iterator().forEachRemaining(section -> {
            packageDescription.append(section.toString()).append('.');
        });

        return packageDescription.substring(0, packageDescription.length() - 1);
    }

    public abstract MeasureResult aggregate(Collection<MeasureResult> numericResults);

    public abstract ClassMeasure getClassMeasure();
}
