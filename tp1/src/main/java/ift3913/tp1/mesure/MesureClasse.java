package ift3913.tp1.mesure;

import ift3913.tp1.utils.LecteurFichier;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe qui contient les mesures en lien avec les classes java
 */
public class MesureClasse {

    /*
        Cas 1: // commentaire -ou- Espace puis // commentaire -ou- code puis // commentaire
        Cas 2: /** commentaire * / -ou- espace /** commentaire * / -ou- code puis /** commentaire * /
        Cas 3: /* comentaire * / -ou- espace /* commentaire * / -ou- code puis /* commentaire * /
     */
    private static final String REGEX_TOUT_COMMENTAIRE =
            "[ \\t]*\\/\\/.*|\\/\\*.*\\*\\/|^[ \\t]*\\/\\*\\*|^[ \\t]*\\*[^\\*\\/]|\\*\\/[ \\t]*$|^[ \\t]*\\*[ \\t]*$";

    /*
        Cas 1: // commentaire -ou- Espace puis // commentaire
        Cas 2: /** commentaire * / -ou- espace /** commentaire * /
        Cas 3: /* comentaire * / -ou- espace /* commentaire * /
     */
    private static final String REGEX_UNIQUEMENT_COMMENTAIRE =
            "^[ \\t]*\\/\\/|^[ \\t]*\\/\\*.*\\*\\/[ \\t]*$|^[ \\t]*\\*|" +
                    "^[ \\t]*\\/\\*\\*[^\\*\\/]*$|^[ \\t]*\\/\\*[^\\*\\/]*$";

    /*
        Attrape les débuts de blocs de commentaires commençant uniquement par /*
        \*\/|^[ \t]*\/\*\*
     */
    private static final String REGEX_CAS_UNIQUE =
            "\\/\\*[^\\*].*[^\\*][^\\/]$|\\/\\*[ \\t]*$";

    // Regex qui identifie les déclarations de méthodes
    private static final String REGEX_METHODE =
            "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";

    // Regex qui identifie les for, while, do...while, switch, catch
    private static final String REGEX_CONDITIONS =
            "[ \\t]*if[ \\t\\r\\n]*\\(|[ \\t]*while[ \\t\\r\\n]*\\(|" +
                    "[ \\t]*do[ \\t\\r\\n]*\\{[ \\t\\r\\n].*[ \\t\\r\\n]*\\}[ \\t\\r\\n]*while|" +
                    "[ \\t]*case[ \\t\\r\\n]*[\\w]+:|[ \\t]*default[ \\t\\r\\n]*:|[ \\t]*for[ \\t\\r\\n]*\\(|" +
                    "[ \\t]*catch[ \\t\\r\\n]*\\(";

    private static final String FIN_DE_COMMENTAIRE = "*/";


    /**
     *
     * @param path du fichier java à analyser
     * @return nombre de lignes de code dans le fichier
     */
    public static int classe_LOC (String path) {
        int nbLignesCode = 0;
        String ligne = "";
        boolean blocCommentaire = false;

        try {
            BufferedReader br = LecteurFichier.ouvertureFichier(path);
            if(br == null) return 0;
            while ((ligne = br.readLine()) != null) {

                if (!blocCommentaire) {

                    if (ligne.trim().isEmpty())
                        continue;

                    if (matchRegex(REGEX_TOUT_COMMENTAIRE, ligne))
                        nbLignesCode++;

                    if (!matchRegex(REGEX_UNIQUEMENT_COMMENTAIRE, ligne))
                        nbLignesCode++;

                    if (matchRegex(REGEX_CAS_UNIQUE, ligne) && !ligne.contains(FIN_DE_COMMENTAIRE)) {
                        blocCommentaire = true;
                        nbLignesCode++;
                    }
                } else {
                    nbLignesCode++;
                    if (ligne.contains(FIN_DE_COMMENTAIRE))
                        blocCommentaire = false;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nbLignesCode;
    }

    /**
     *
     * @param path du fichier java à analyser
     * @return nombre de commentaires uniquement dans le code
     */
    public static int classe_CLOC (String path) {
        int nbCommentaires = 0;
        String ligne = "";
        boolean blocCommentaire = false;

        try {
            BufferedReader br = LecteurFichier.ouvertureFichier(path);
            if(br == null) return 0;
            while((ligne = br.readLine()) != null) {

                if(!blocCommentaire) {

                    if (ligne.trim().isEmpty())
                        continue;

                    if (matchRegex(REGEX_TOUT_COMMENTAIRE, ligne))
                        nbCommentaires++;

                    if (matchRegex(REGEX_CAS_UNIQUE, ligne) && !ligne.contains(FIN_DE_COMMENTAIRE)) {
                        nbCommentaires++;
                        blocCommentaire = true;
                    }
                }
                else {
                    nbCommentaires++;
                    if (ligne.contains(FIN_DE_COMMENTAIRE))
                        blocCommentaire = false;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return nbCommentaires;
    }

    /**
     * Calcul la densité d'une classe
     * @param lignesCloc nombre de lignes commentaires
     * @param lignesLoc nombre de lignes code + commentaires
     * @return la densité de la classe
     */
    public static float classe_DC (float lignesCloc, float lignesLoc) {
        if(lignesLoc != 0)
            return lignesCloc / lignesLoc;
        else
            return 0;
    }

    /**
     * Calcule le WMC d'une classe
     * @param path du fichier java à analyser
     * @return la complexité de la classe
     */
    public static int classe_WMC(String path) {
        int complexite = 0;
        String ligne = "";
        StringBuilder fichier = new StringBuilder();

        try {
            BufferedReader br = LecteurFichier.ouvertureFichier(path);
            if (br == null) return 0;
            while ((ligne = br.readLine()) != null) {
                fichier.append(ligne);
            }

            Pattern regex = Pattern.compile(REGEX_METHODE);
            Matcher matcher = regex.matcher(fichier.toString());
            while (matcher.find()) {
                complexite++;
            }

            regex = Pattern.compile(REGEX_CONDITIONS);
            matcher = regex.matcher(fichier.toString());
            while (matcher.find()) {

                complexite++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return complexite;
    }

    private static boolean matchRegex(String regexAMatcher, String ligne) {
        Pattern regex = Pattern.compile(regexAMatcher);
        Matcher matcher = regex.matcher(ligne);
        return matcher.find();
    }

}