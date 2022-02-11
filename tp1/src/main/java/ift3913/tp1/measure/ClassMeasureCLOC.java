package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.List;

/**
 * Measures the number of lines with a comment, in a Java class.
 * <br>
 * The class {@link Parser} does most of the heavy-lifting
 *
 * @author jclaude
 */
public class ClassMeasureCLOC extends ClassMeasure {

    /**
     * Current number of lines with comment encountered
     */
    private int count = 0;
    /*
     * Did last line leave us in a comment?
     */
    private boolean inComment = false;

    public ClassMeasureCLOC() {
        super("classe_CLOC");
    }

    @Override
    public void consumeLine(String line) {
        List<String> tokens = Parser.tokenize(line, inComment);
        /*
         * Even if the last line left us in a comment, if this line is empty, it
         * doesn't count as a line which contains a comment
         */
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
