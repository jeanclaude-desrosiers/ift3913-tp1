package ift3913.tp1.app;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import ift3913.tp1.measure.MeasureSuite;
import ift3913.tp1.writer.MeasureResultWriter;
import ift3913.tp1.writer.MeasureResultWriterCSV;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.ext.java7.PathArgumentType;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starting point of the Application. Parses command-line arguments and
 * initialize a ProjectExplorer
 *
 * @author jclaude
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * Entry-point of the program
     *
     * @param args
     */
    public static void main(String[] args) {
        ArgumentParser parser = getParser();

        try {
            Namespace namespace = parser.parseArgs(args);
            LOGGER.debug(namespace.toString());

            run(namespace);
        } catch (ArgumentParserException exc) {
            parser.handleError(exc);
        }
    }

    private static ArgumentParser getParser() {
        ArgumentParser parser = ArgumentParsers.newFor("tp1").build();

        parser.addArgument("-m", "--measures")
                .dest("measure_suite")
                .type(MeasureSuite.class)
                .setDefault(MeasureSuite.ALL)
                .help("Select a set of measures to run");

        parser.addArgument("project_path")
                .type(new PathArgumentType())
                .metavar("PATH")
                .required(true)
                .help("Specify the path of the project to measure");

        parser.addArgument("-od", "--out-directory")
                .dest("dir")
                .metavar("DIR")
                .setDefault("./")
                .help("Specify the directory where the out file will be saved");

        parser.addArgument("-o", "--out-file-suffix")
                .dest("suffix")
                .metavar("FILE")
                .setDefault("")
                .help("Specify the suffix of the result file names "
                        + "(e.g. FILE is \"jfreechart_\" then files would be "
                        + "\"jfreechart_classes.csv\" and \"jfreechart_paquets.csv\"");

        return parser;
    }

    private static void run(Namespace namespace) {
        /*
         * Setup ProjectExplorer to explore the given project path,with a given
         * set of measures
         */
        Path projectPath = namespace.get("project_path");
        MeasureSuite measureSuite = namespace.get("measure_suite");
        ProjectExplorer projectExplorer = new ProjectExplorer(projectPath, measureSuite);
        LOGGER.trace("Created ProjectExplorer");

        /*
         * Setup output writers for class and package measure results
         */
        Path classMeasurePath = Paths.get(namespace.get("dir"),
                namespace.get("suffix") + "classes.csv");
        MeasureResultWriter classMeasureResultWriter = new MeasureResultWriterCSV(classMeasurePath);

        Path packageMeasurePath = Paths.get(namespace.get("dir"),
                namespace.get("suffix") + "paquets.csv");
        MeasureResultWriter packageMeasureResultWriter = new MeasureResultWriterCSV(packageMeasurePath);
        LOGGER.trace("Created MeasureResultWriters");

        /*
         * Collect and separate results
         */
        Collection<MeasureResult> measureResults = projectExplorer.explore();
        Collection<MeasureResult> classMeasureResults = measureResults
                .stream()
                .filter(measure -> measure.getType() == MeasureResultType.CLASS)
                .collect(Collectors.toList());
        Collection<MeasureResult> packageMeasureResults = measureResults
                .stream()
                .filter(measure -> measure.getType() == MeasureResultType.PACKAGE)
                .collect(Collectors.toList());
        LOGGER.trace("Measured with ProjectExplorer");

        /*
         * Write results
         */
        classMeasureResultWriter.write(classMeasureResults, MeasureResultType.CLASS);
        packageMeasureResultWriter.write(packageMeasureResults, MeasureResultType.PACKAGE);
        LOGGER.trace("Wrote MeasureResults");
    }
}
