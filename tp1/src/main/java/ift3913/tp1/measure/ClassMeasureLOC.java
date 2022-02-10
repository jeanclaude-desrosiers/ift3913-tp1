package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jclaude
 */
public class ClassMeasureLOC extends ClassMeasure {

    private int count;
    private boolean inComment;

    public ClassMeasureLOC() {
        super("classe_LOC");

        count = 0;
        inComment = false;
    }

    @Override
    public void consumeLine(String line) {
        List<String> tokens = Parser.tokenize(line, inComment);
        boolean lineContainsCode = false;

        for (String token : tokens) {
            if (!inComment && token.startsWith("/*") && !token.endsWith("*/")) {
                inComment = true;
            } else if (!inComment && !token.startsWith("//")) {
                lineContainsCode = true;
            } else if (inComment && token.endsWith("*/")) {
                inComment = false;
            }
        }

        if (lineContainsCode) {
            count++;
        }
    }

    @Override
    public Number getNumericResult() {
        return count;
    }

}
