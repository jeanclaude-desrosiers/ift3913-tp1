package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author jclaude
 */
public class ClassMeasureWMC extends ClassMeasure {

    private static final Collection<String> BRANCH_WORDS = Arrays.asList(
            "if", "while", "case", "for"
    );

    private int indentation = 0;
    private int count = 1;
    private boolean inComment = false;
    private final Collection<Integer> complexityPerMethod = new ArrayList<>();

    public ClassMeasureWMC() {
        super("classe_WMC");
    }

    @Override
    public void consumeLine(String line) {
        List<String> tokens = Parser.tokenize(line, inComment);

        for (String token : tokens) {
            if (!inComment && token.startsWith("/*") && !token.endsWith("*/")) {
                inComment = true;
            } else if (inComment && token.endsWith("*/")) {
                inComment = false;
            } else if (!inComment && !token.startsWith("//")) {
                if (token.equals("{")) {
                    indentation++;
                } else if (token.equals("}")) {
                    indentation--;

                    // We just exited a method
                    if (indentation == 1) {
                        complexityPerMethod.add(count);
                        count = 1;
                    }
                }

                // We are in a method, and we see a branching word
                if (indentation > 1 && BRANCH_WORDS.contains(token)) {
                    count++;
                }
            }
        }
    }

    @Override
    public Number getNumericResult() {
        if (complexityPerMethod.isEmpty()) {
            return 1;
        } else {
            return complexityPerMethod.stream().reduce(0, (x, y) -> x + y)
                    / complexityPerMethod.size();
        }
    }

}
