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

    private final Path writePath;

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasureResultWriterCSV.class);

    public MeasureResultWriterCSV(Path writePath) {
        this.writePath = writePath;
    }

    @Override
    public void write(Collection<MeasureResult> measureResults, MeasureResultType type) {
        String[] headers = generateHeaders(measureResults, type);
        
        String a = "";//";
        /*   //*/

        List<String[]> entries = generateEntries(headers, measureResults);

        try (CSVWriter writer = new CSVWriter(new FileWriter(writePath.toString()))) {
            entries.forEach(writer::writeNext);
        } catch (IOException ex) {
            LOGGER.error("Could not write to file", ex);
        }
    }

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

    private List<String[]> generateEntries(String[] headers, Collection<MeasureResult> measureResults) {
        List<String[]> entries = new ArrayList<>();
        entries.add(headers);

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
                entry[column] = measureResult.getNumericResult().toString();
            }

            entries.add(entry);
        }

        return entries;
    }

    private int indexOf(String[] array, String element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public Path getWritePath() {
        return writePath;
    }

}
