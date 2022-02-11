package ift3913.tp1.measure;

import ift3913.tp1.parsing.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Measures the average McCabe cyclomatic complexity, for methods in a Java
 * class.
 * <br>
 * The class {@link Parser} does most of the heavy-lifting
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/Cyclomatic_complexity#Description">Wikipedia
 * article on McCabe cyclomatic complexity</a>
 *
 * @author jclaude
 */
public class ClassMeasureWMC extends ClassMeasure {

    /**
     * Set of words which produce a branching in Java
     */
    private static final Collection<String> BRANCH_WORDS = Arrays.asList(
            "if", "while", "case", "for"
    );

    /**
     * Keeps track of the "{", "}" indentation as we go through the file. This
     * allows us to know when we enter/leave a method
     */
    private int indentation = 0;
    /**
     * Counts the number of branching words encountered in a method so far.
     * Counter starts at 1 because if we encounter no branching word, the
     * complexity is just 1
     */
    private int count = 1;
    /*
     * Did last line leave us in a comment?
     */
    private boolean inComment = false;
    /**
     * Keeps track of the McCabe cyclomatic complexity for every method, so we
     * can average them after
     */
    private final Collection<Integer> complexityPerMethod = new ArrayList<>();

    public ClassMeasureWMC() {
        super("WMC");
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

                    /*
                     * We just exited a method, we need to add what we counted
                     * to the list and reset the counter
                     */
                    if (indentation == 1) {
                        complexityPerMethod.add(count);
                        count = 1;
                    }
                }

                /*
                 * We are in a method, and we see a branching word
                 */
                if (indentation > 1 && BRANCH_WORDS.contains(token)) {
                    count++;
                }
            }
        }
    }

    @Override
    public Number getNumericResult() {
        return Math.max(1, complexityPerMethod.stream()
                .collect(Collectors.averagingInt(x -> x)));
    }

}
