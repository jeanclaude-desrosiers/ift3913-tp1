package ift3913.tp1.utils;

/**
 * Classe qui contient tous les regex pour l'analyse des classes
 */
public class RegexAnalyse {

    /*
    Cas 1: // commentaire -ou- Espace puis // commentaire -ou- code puis // commentaire
    Cas 2: /** commentaire * / -ou- espace /** commentaire * / -ou- code puis /** commentaire * /
    Cas 3: /* comentaire * / -ou- espace /* commentaire * / -ou- code puis /* commentaire * /
 */
    public static final String REGEX_TOUT_COMMENTAIRE =
            "[ \\t]*\\/\\/.*|\\/\\*.*\\*\\/|^[ \\t]*\\/\\*\\*|^[ \\t]*\\*[^\\*\\/]|\\*\\/[ \\t]*$|^[ \\t]*\\*[ \\t]*$";

    /*
        Cas 1: // commentaire -ou- Espace puis // commentaire
        Cas 2: /** commentaire * / -ou- espace /** commentaire * /
        Cas 3: /* comentaire * / -ou- espace /* commentaire * /
     */
    public static final String REGEX_UNIQUEMENT_COMMENTAIRE =
            "^[ \\t]*\\/\\/|^[ \\t]*\\/\\*.*\\*\\/[ \\t]*$|^[ \\t]*\\*|" +
                    "^[ \\t]*\\/\\*\\*[^\\*\\/]*$|^[ \\t]*\\/\\*[^\\*\\/]*$";

    /*
        Attrape les débuts de blocs de commentaires commençant uniquement par /*
        \*\/|^[ \t]*\/\*\*
     */
    public static final String REGEX_CAS_UNIQUE =
            "\\/\\*[^\\*].*[^\\*][^\\/]$|\\/\\*[ \\t]*$";

    // Regex qui identifie les déclarations de méthodes
    public static final String REGEX_METHODE =
            "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";

    // Regex qui identifie les for, while, do...while, switch, catch
    public static final String REGEX_CONDITIONS =
            "[ \\t]*if[ \\t\\r\\n]*\\(|[ \\t]*while[ \\t\\r\\n]*\\(|" +
                    "[ \\t]*do[ \\t\\r\\n]*\\{[ \\t\\r\\n].*[ \\t\\r\\n]*\\}[ \\t\\r\\n]*while|" +
                    "[ \\t]*case[ \\t\\r\\n]*[\\w]+:|[ \\t]*default[ \\t\\r\\n]*:|[ \\t]*for[ \\t\\r\\n]*\\(|" +
                    "[ \\t]*catch[ \\t\\r\\n]*\\(";

    public static final String FIN_DE_COMMENTAIRE = "*/";
}
