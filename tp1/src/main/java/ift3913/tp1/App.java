package ift3913.tp1;

import ift3913.tp1.mesure.MesurePaquet;
import ift3913.tp1.utils.CsvWriter;

import java.io.PrintWriter;

/**
 *
 * @author jclaude
 * @author Genevieve Paul-Hus 20037331
 */
public class App {
    public static void main(String[] args) {

        PrintWriter fichierCsvPaquets = CsvWriter.creerFichierCSVpourPaquets();
        PrintWriter fichierCsvClasses = CsvWriter.creerFichierCSVpourClasses();

        MesurePaquet.mesurerPaquet(args[0], fichierCsvPaquets, fichierCsvClasses);

        fichierCsvClasses.close();
        fichierCsvPaquets.close();
    }
}
