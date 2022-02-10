package ift3913.tp1.utils;

import ift3913.tp1.model.Element;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Classe qui génère les rapports csv
 */
public class CsvWriter {

    private static final String SEPARATEUR = ",";
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
     * @param pw le print writer de choix qui indique dans quel fichier csv écrire
     * @param elementAAjouter l'élément a ajouté au rapport csv
     */
    public static void ecrirePrintWriter(PrintWriter pw, Element elementAAjouter) {

        StringBuilder builder = new StringBuilder();
        builder.append(elementAAjouter.getChemin());
        builder.append(SEPARATEUR);
        builder.append(elementAAjouter.getNom());
        builder.append(SEPARATEUR);
        builder.append(elementAAjouter.getNbLignesLoc());
        builder.append(SEPARATEUR);
        builder.append(elementAAjouter.getNbLignesCloc());
        builder.append(SEPARATEUR);
        builder.append(elementAAjouter.getDensite());
        builder.append(SEPARATEUR);
        builder.append(elementAAjouter.getComplexite());
        builder.append(SEPARATEUR);
        builder.append(elementAAjouter.getDegre());
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
