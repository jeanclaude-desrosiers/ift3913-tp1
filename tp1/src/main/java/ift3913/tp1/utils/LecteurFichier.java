package ift3913.tp1.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe qui regroupe les fonctions en lien avec le parcours de fichiers et leur lecture.
 */
public class LecteurFichier {

    /**
     * Ouverture d'un fichier pour la lecture
     * @param path chemin du fichier
     * @return le lecteur fichier ouvert
     */
    public static BufferedReader ouvertureFichier(String path) {
        try{
            FileReader fichier = new FileReader(path);
            return new BufferedReader(fichier);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
