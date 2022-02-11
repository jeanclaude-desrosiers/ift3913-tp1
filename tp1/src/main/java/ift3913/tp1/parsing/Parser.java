package ift3913.tp1.parsing;

import java.util.ArrayList;
import java.util.List;

/**
 * Static class helper for parsing a Java class source file.
 *
 * @author jclaude
 */
public final class Parser {

    private Parser() {

    }

    /**
     * Takes a single line and splits it into tokens.
     *
     * Whitespace is removed from the start/end of the line and in-between
     * tokens. The only place it remains is within comments, because everything
     * in a comment is just one token.
     *
     * E.g. [int x = 10; // This is a variable assignment] becomes<br>
     * ["int", "x", "=", "10", ";", "// This is a variable assignment"]
     *
     * Comments in a multi-line comment are also handled.
     *
     * E.g. [int x = 10;/* Hello // World] becomes<br>
     * ["int", "x", "=", "10", ";", "/* Hello // World" becomes"]
     *
     * String literals also become one single token.
     *
     * E.g. [String ssn = "// secret"; // encryption done right!] becomes<br>
     * ["String", "ssn", "=", "\"// secret\"", ";", "// encryption done right!"]
     *
     * @param line             line to split
     * @param alreadyInComment do we start the line in a comment (as in, did the
     *                         last line not close a comment)
     *
     * @return a list of String tokens, or empty list if the line is all
     *         whitespace
     */
    public static List<String> tokenize(String line, boolean alreadyInComment) {
        List<String> tokens = new ArrayList<>();
        boolean inString = false;
        boolean inComment = alreadyInComment;
        boolean isTokenDone = false;

        StringBuilder token = new StringBuilder();
        /*
         * "previous" also includes the current character
         */
        StringBuilder previous = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            Character current = line.charAt(i);
            previous.append(current);

            if (inString || (!inComment && current == '"' && !getLast(previous, 2).equals("\\\""))) {
                token.append(current);

                /*
                 * We've either hit the start of a new string, or the end of a
                 * string we were in, so we flip inString
                 */
                inString = !inString;
                /*
                 * If we reach the end of the string, we completed a token
                 */
                if (!inString) {
                    isTokenDone = true;
                }
            } else if (!inString) {
                if (!inComment && getLast(previous, 2).equals("//")) {
                    /*
                     * If we are not in a comment nor a string, then
                     * encountering a // means the rest of the line is just a
                     * comment.
                     */
                    tokens.add(token.toString() + line.substring(i));
                    token.setLength(0);
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
                    /*
                     * Probably the start of a comment, append to token and
                     * we'll see
                     */
                    token.append(current);
                } else if (Character.isWhitespace(current)) {
                    /*
                     * Ignore whitespace, but if we have something in token then
                     * whitespace means we reached the end of the token
                     */
                    if (token.length() > 0) {
                        isTokenDone = true;
                    }
                } else if (Character.isLetterOrDigit(current)) {
                    token.append(current);
                } else {
                    isTokenDone = true;

                    /*
                     * If there is already something in the token, add it and
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
                /*
                 * Clearing the StringBuilder in-place
                 */
                token.setLength(0);
            }
        }

        /*
         * Add the last token if there is one
         */
        if (token.length() > 0) {
            tokens.add(token.toString());
        }

        return tokens;
    }

    /**
     * Gets the last "n" or less characters
     *
     * @param str
     * @param n
     *
     * @return the "n" last characters in str, or the entire str if n is greater
     *         than str
     */
    private static String getLast(StringBuilder str, int n) {
        return str.substring(Math.max(0, str.length() - n));
    }
}
