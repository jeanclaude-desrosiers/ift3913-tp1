package ift3913.tp1.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Classe qui génère les rapports csv
 */
public class CsvWriter {

    private static final String CLASSE_TITRE = "classes.csv";
    private static final String PAQUET_TITRE = "paquets.csv";
    private static final String CLASSE_HEADERS = "chemin,classe,classe_LOC,classe_CLOC,classe_DC,WMC,classe_BC";
    private static final String PAQUET_HEADERS = "chemin,paquet,paquet_LOC,paquet_CLOC,paquet_DC,WCP,paquet_BC";

    /**
     * Créer un rapport csv pour les classes
     * @return un printwriter dans lequel ajouter les modifications
     */
    public static PrintWriter creerFichierCSVpourClasses() {
        return getPrintWriter(CLASSE_TITRE, CLASSE_HEADERS);
    }

    /**
     * Créer un rapport csv pour les paquets
     * @return un printwriter dans lequel ajouter les modifications
     */
    public static PrintWriter creerFichierCSVpourPaquets() {
        return getPrintWriter(PAQUET_TITRE, PAQUET_HEADERS);
    }

    /**
     * Permet d'ajouter les informations d'un élément sur une ligne dans un rapport csv
     * @param pw indique dans quel rapport csv écrire (classes ou paquets)
     * @param chemin indique le chemin de l'élément
     * @param nom indique le nom de la classe ou paquet
     * @param nbLignesLoc indique le nombre de lignes de code et de commentaires dans l'élément
     * @param nbLignesCloc indique le nombre de lignes de commentaires dans l'élément
     * @param densite indique la densité du code de l'élément
     * @param complexite indique la complexite du code de l'élément
     * @param degree indique le degré des commentaires de l'élément
     */
    public static void ecrirePrintWriter(PrintWriter pw,
                                         String chemin, String nom, int nbLignesLoc, int nbLignesCloc, float densite,
                                         int complexite, float degree) {
        StringBuilder builder = new StringBuilder();
        builder.append(chemin);
        builder.append(",");
        builder.append(nom);
        builder.append(",");
        builder.append(nbLignesLoc);
        builder.append(",");
        builder.append(nbLignesCloc);
        builder.append(",");
        builder.append(densite);
        builder.append(",");
        builder.append(complexite);
        builder.append(",");
        builder.append(degree);
        builder.append("\n");
        pw.write(builder.toString());
    }

    private static PrintWriter getPrintWriter(String titre, String headers) {
        try {
            PrintWriter pw = new PrintWriter(titre);
            String builder = headers + "\n";
            pw.write(builder);
            return pw;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
