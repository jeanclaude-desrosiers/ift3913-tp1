package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.List;

/**
 * Measures the number of lines with code on it, in a Java class.
 * <br>
 * The class {@link Parser} does most of the heavy-lifting
 *
 * @author jclaude
 */
public class ClassMeasureLOC extends ClassMeasure {

    private int count = 0;
    private boolean inComment = false;

    public ClassMeasureLOC() {
        super("classe_LOC");
    }

    @Override
    public void consumeLine(String line) {
        List<String> tokens = Parser.tokenize(line, inComment);
        boolean lineContainsCode = false;

        for (String token : tokens) {
            if (!inComment && token.startsWith("/*") && !token.endsWith("*/")) {
                inComment = true;
            } else if (!inComment && !token.startsWith("//") && !token.startsWith("/*")) {
                lineContainsCode = true;
            } else if (inComment && token.endsWith("*/")) {
                inComment = false;
            }
        }

        if (lineContainsCode) {
            System.out.println("LOC : " + tokens);
            count++;
        } else {
            System.out.println("NOT : " + tokens);
        }
    }

    @Override
    public Number getNumericResult() {
        return count;
    }

}
