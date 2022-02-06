package ift3913.tp1.mesures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MesurePaquet {

    public static int paquet_LOC (String chemin) {
        int nbLignes = 0;

        List<Path> cheminsFichiers = obtenirListeFichiers(chemin);

        for (Path path: cheminsFichiers) {
            File f = new File(path.toAbsolutePath().toString());
            if (f.isFile()) {
                nbLignes += MesureClasse.classe_LOC(path.toAbsolutePath().toString());
            }
        }

        return nbLignes;
    }

    public static int paquet_CLOC (String chemin) {
        int nbLignes = 0;

        List<Path> cheminsFichiers = obtenirListeFichiers(chemin);

        for (Path path: cheminsFichiers) {
            File f = new File(path.toAbsolutePath().toString());
            if(f.isFile()) {
                nbLignes += MesureClasse.classe_CLOC(path.toAbsolutePath().toString());
            }
        }

        return nbLignes;
    }

    public static float paquet_DC (float loc, float cloc) {
        return cloc / loc;
    }

    private static List<Path> obtenirListeFichiers (String path) {
        List<Path> cheminsFichiers = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            cheminsFichiers = paths.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cheminsFichiers;
    }
}
