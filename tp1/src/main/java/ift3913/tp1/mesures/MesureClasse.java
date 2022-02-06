package ift3913.tp1.mesures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MesureClasse {

    /*
        Cas 1: // commentaire -ou- Espace puis // commentaire -ou- code puis // commentaire
        Cas 2: /** commentaire * / -ou- espace /** commentaire * / -ou- code puis /** commentaire * /
        Cas 3: /* comentaire * / -ou- espace /* commentaire * / -ou- code puis /* commentaire * /
     */
    private static final String REGEX_TOUT_COMMENTAIRE =
            "[ \\t]*\\/\\/.*|\\/\\*.*\\*\\/|^[ \\t]*\\/\\*\\*|^[ \\t]*\\*[^\\*\\/]|\\*\\/";

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
            FileReader fichier = new FileReader(path);
            BufferedReader br = new BufferedReader(fichier);
            while((ligne = br.readLine()) != null) {

                if(!blocCommentaire) {

                    if (ligne.trim().isEmpty())
                        continue;

                    if(matchRegex(REGEX_TOUT_COMMENTAIRE, ligne))
                        nbLignesCode++;

                    if(!matchRegex(REGEX_UNIQUEMENT_COMMENTAIRE, ligne))
                        nbLignesCode++;

                    if (matchRegex(REGEX_CAS_UNIQUE, ligne) && !ligne.contains(FIN_DE_COMMENTAIRE)) {
                        blocCommentaire = true;
                        nbLignesCode++;
                    }
                }
                else {
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
     * @param path du fichier à analyser
     * @return nombre de commentaires uniquement dans le code
     */
    public static int classe_CLOC (String path) {
        int nbCommentaires = 0;
        String ligne = "";
        boolean blocCommentaire = false;

        try {
            FileReader fichier = new FileReader(path);
            BufferedReader br = new BufferedReader(fichier);
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

    public static float classe_DC(float loc, float cloc) {
        return cloc / loc;
    }

    private static boolean matchRegex(String regexAMatcher, String ligne) {
        Pattern regex = Pattern.compile(regexAMatcher);
        Matcher matcher = regex.matcher(ligne);
        return matcher.find();
    }

}