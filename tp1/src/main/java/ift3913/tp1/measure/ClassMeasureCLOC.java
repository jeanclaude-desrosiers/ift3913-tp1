package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author jclaude
 */
public class ClassMeasureCLOC extends ClassMeasure {

    private int count;
    private boolean inComment;

    public ClassMeasureCLOC() {
        super("classe_CLOC");

        count = 0;
        inComment = false;
    }

    @Override
    public void consumeLine(String line) {
        List<String> tokens = Parser.tokenize(line, inComment);
        boolean lineContainsComment = inComment && !tokens.isEmpty();

        for (String token : tokens) {
            if (!inComment && token.startsWith("/*")) {
                lineContainsComment = true;

                if (!token.endsWith("*/")) {
                    inComment = true;
                }
            } else if (!inComment && token.startsWith("//")) {
                lineContainsComment = true;
            } else if (inComment && token.endsWith("*/")) {
                inComment = false;
            }
        }

        if (lineContainsComment) {
            count++;
        }
    }

    @Override
    public Number getNumericResult() {
        return count;
    }

}
