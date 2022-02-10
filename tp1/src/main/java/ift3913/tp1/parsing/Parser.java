package ift3913.tp1.parsing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jclaude
 */
public final class Parser {

    public static final Parser INSTANCE = new Parser();

    private Parser() {

    }

    /**
     *
     * @param line
     * @param alreadyInComment
     * @return
     */
    public static List<String> tokenize(String line, boolean alreadyInComment) {
        List<String> tokens = new ArrayList<>();
        boolean inString = false;
        boolean inComment = alreadyInComment;
        boolean isTokenDone = false;

        StringBuilder token = new StringBuilder();
        // "previous" also includes the current character
        StringBuilder previous = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            Character current = line.charAt(i);
            previous.append(current);

            if (inString || (!inComment && current == '"' && !getLast(previous, 2).equals("\\\""))) {
                token.append(current);

                /* We've either hit the start of a new string, or
                 * the end of a string we were in, so we flip inString
                 */
                inString = !inString;
                // If we reach the end of the string, we completed a token
                if (!inString) {
                    isTokenDone = true;
                }
            } else if (!inString) {
                if (!inComment && getLast(previous, 2).equals("//")) {
                    /* If we are not in a comment nor a string, then encountering
                     * a // means the rest of the line is just a comment.
                     */
                    tokens.add(token.toString() + line.substring(i));
                    break;
                } else if (!inComment && getLast(previous, 2).equals("/*")) {
                    inComment = true;
                    token.append(current);
                    // Handles this weird comment '/*/' by putting a space '/* /'
                    // that way the '*/' won't be seen as the comment's end
                    previous.append(' ');
                } else if (inComment) {
                    token.append(current);

                    if (getLast(previous, 2).equals("*/")) {
                        inComment = false;
                        isTokenDone = true;
                    }
                } else if (current == '/' || current == '\\') {
                    // Probably the start of a comment, append to token and we'll see
                    token.append(current);
                } else if (Character.isWhitespace(current)) {
                    /* Ignore whitespace, but if we have something in token
                     * then whitespace means we reached the end of the token
                     */
                    if (token.length() > 0) {
                        isTokenDone = true;
                    }
                } else if (Character.isLetter(current)) {
                    token.append(current);
                } else {
                    isTokenDone = true;

                    /* If there is already something in the token, add it and
                     * then clear it
                     */
                    if (token.length() > 0) {
                        tokens.add(token.toString());
                        token.setLength(0);
                    }

                    token.append(current);
                }
            }

            if (isTokenDone) {
                isTokenDone = false;
                tokens.add(token.toString());
                // Clearing the StringBuilder in-place
                token.setLength(0);
            }
        }

        return tokens;
    }

    private static String getLast(StringBuilder str, int n) {
        return str.substring(Math.max(0, str.length() - n));
    }
}
