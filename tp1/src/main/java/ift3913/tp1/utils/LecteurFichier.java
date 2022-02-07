package ift3913.tp1.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * Trouve les chemins de tous les fichiers en profondeur 1
     * @param path chemin du fichier
     * @return liste de tous les chemins trouvés
     */
    public static List<Path> obtenirListeFichiers (String path) {
        List<Path> cheminsFichiers = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(path), 1)) {
            cheminsFichiers = paths.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cheminsFichiers;
    }
}
