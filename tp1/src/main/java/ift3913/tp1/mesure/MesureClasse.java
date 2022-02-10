package ift3913.tp1.mesure;

import ift3913.tp1.model.Element;
import ift3913.tp1.utils.LecteurFichier;
import ift3913.tp1.utils.RegexAnalyse;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe qui contient les mesures en lien avec les classes java
 */
public class MesureClasse {

    public Element mesurerClasse(String path, String nom) {
        Element classe = new Element();

        String ligne = "";
        StringBuilder fichier = new StringBuilder();
        boolean blocCommentaire = false;

        try {
            BufferedReader br = LecteurFichier.ouvertureFichier(path);
            if (br == null) return classe;
            while ((ligne = br.readLine()) != null) {

                fichier.append(ligne);

                if (!blocCommentaire) {

                    if (ligne.trim().isEmpty())
                        continue;

                    classe.ajoutLoc(classe_LOC(ligne));
                    classe.ajoutCloc(classe_CLOC(ligne));

                    if (matchRegex(RegexAnalyse.REGEX_CAS_UNIQUE, ligne)
                            && !ligne.contains(RegexAnalyse.FIN_DE_COMMENTAIRE)) {
                        classe.setNbLignesCloc(classe.getNbLignesCloc() + 1);
                        classe.setNbLignesLoc(classe.getNbLignesLoc() + 1);
                        blocCommentaire = true;
                    }

                } else {
                    classe.setNbLignesCloc(classe.getNbLignesCloc() + 1);
                    classe.setNbLignesLoc(classe.getNbLignesLoc() + 1);
                    if (ligne.contains(RegexAnalyse.FIN_DE_COMMENTAIRE))
                        blocCommentaire = false;
                }
            }

            classe.setComplexite(classe_WMC(fichier));
            classe.calculeDensite();
            classe.calculeDegreCommentaire();
            classe.setChemin(path);
            classe.setNom(nom);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return classe;
    }

    /**
     * @param ligne en lecture
     * @return nombre de lignes de code dans le fichier
     */
    public int classe_LOC(String ligne) {
        int resultat = 0;

        if (matchRegex(RegexAnalyse.REGEX_TOUT_COMMENTAIRE, ligne))
            resultat++;

        if (!matchRegex(RegexAnalyse.REGEX_UNIQUEMENT_COMMENTAIRE, ligne))
            resultat++;

        return resultat;
    }

    /**
     * @param ligne en lecture
     * @return nombre de commentaires uniquement dans le code
     */
    public int classe_CLOC(String ligne) {
        int resultat = 0;

        if (matchRegex(RegexAnalyse.REGEX_TOUT_COMMENTAIRE, ligne))
            resultat++;

        return resultat;
    }

    /**
     * Calcule le WMC d'une classe
     *
     * @param fichier en lecture
     * @return la complexité de la classe
     */
    public int classe_WMC(StringBuilder fichier) {
        int complexite = 0;

        Pattern regex = Pattern.compile(RegexAnalyse.REGEX_METHODE);
        Matcher matcher = regex.matcher(fichier.toString());
        while (matcher.find()) {
            complexite++;
        }

        regex = Pattern.compile(RegexAnalyse.REGEX_CONDITIONS);
        matcher = regex.matcher(fichier.toString());
        while (matcher.find()) {
            complexite++;
        }

        return complexite;
    }

    private boolean matchRegex(String regexAMatcher, String ligne) {
        Pattern regex = Pattern.compile(regexAMatcher);
        Matcher matcher = regex.matcher(ligne);
        return matcher.find();
    }

}