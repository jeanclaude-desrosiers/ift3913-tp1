package ift3913.tp1.writer;

import com.opencsv.CSVWriter;
import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of MeasureResultWriter for CSV files
 *
 * @author jclaude
 */
public class MeasureResultWriterCSV implements MeasureResultWriter {

    /**
     * Where the CSV file is written
     */
    private final Path writePath;

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasureResultWriterCSV.class);

    public MeasureResultWriterCSV(Path writePath) {
        this.writePath = writePath;
    }

    @Override
    public void write(Collection<MeasureResult> measureResults, MeasureResultType type) {
        String[] headers = generateHeaders(measureResults, type);

        /*
         * The list of entries also include the header
         */
        List<String[]> entries = generateEntries(headers, measureResults);

        try (CSVWriter writer = new CSVWriter(new FileWriter(writePath.toString()))) {
            entries.forEach(writer::writeNext);
        } catch (IOException ex) {
            LOGGER.error("Could not write to file", ex);
        }
    }

    /**
     * Generates the header for the CSV file.
     *
     * @param measureResults all the MeasureResults to write in the CSV file,
     *                       used to gte their measure name
     * @param type           type of MeasureResult being written to file
     *
     * @return the first line to be written in the CSV file
     */
    private String[] generateHeaders(Collection<MeasureResult> measureResults, MeasureResultType type) {
        List<String> headers = new ArrayList<>();
        headers.add("chemin");

        switch (type) {
            case CLASS:
                headers.add("class");
                break;
            case PACKAGE:
                headers.add("paquet");
                break;
            default:
                headers.add("description");
                LOGGER.warn("Unknown MeasureResultType [" + type + "]");
                break;
        }

        headers.addAll(measureResults
                .stream()
                .map(MeasureResult::getName)
                .distinct()
                .collect(Collectors.toList()));

        return headers.toArray(String[]::new);
    }

    /**
     * Generates the list of entries (rows) to write in the CSV.
     *
     * @param headers        already generated by generateHeaders()
     * @param measureResults results to write
     *
     * @return a list of rows to write
     */
    private List<String[]> generateEntries(String[] headers, Collection<MeasureResult> measureResults) {
        List<String[]> entries = new ArrayList<>();
        entries.add(headers);

        /**
         * This is similar to a GROUP BY in SQL. Instead of having a row for
         * every measure, every object measured has a row and the columns are
         * the measure results
         */
        Map<Path, List<MeasureResult>> groupedMeasureResults
                = measureResults
                        .stream()
                        .collect(Collectors.groupingBy(MeasureResult::getPath));

        for (Path path : groupedMeasureResults.keySet()) {
            String[] entry = new String[headers.length];

            entry[0] = path.toString();

            for (MeasureResult measureResult : groupedMeasureResults.get(path)) {
                entry[1] = measureResult.getDescription();

                int column = indexOf(headers, measureResult.getName());
                entry[column] = formatNumber(measureResult.getNumericResult());
            }

            entries.add(entry);
        }

        return entries;
    }

    /**
     * Helper method to get the index of an element in a String array
     *
     * @param array
     * @param element
     *
     * @return the index found, or -1
     */
    private static int indexOf(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Formats the number to a String (does rounding).
     *
     * @param num
     *
     * @return
     */
    private static String formatNumber(Number num) {
        return String.valueOf(Math.round(num.doubleValue() * 100) / 100.);
    }

    public Path getWritePath() {
        return writePath;
    }

}
