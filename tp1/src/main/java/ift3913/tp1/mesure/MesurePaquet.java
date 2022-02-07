package ift3913.tp1.mesure;

import ift3913.tp1.utils.CsvWriter;
import ift3913.tp1.utils.LecteurFichier;
import ift3913.tp1.model.Paquet;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

/**
 * Classe qui contient les mesures des paquets
 */
public class MesurePaquet {

    private static final String JAVA_EXTENSION = ".java";

    /**
     * Lance l'application des mesure récursivement à travers tous les paquets d'un dossier
     * @param chemin racine, début de l'analyse
     * @param paquets le fichier csv paquets à mettre à jour
     * @param classes le fichier csv classes à mettre à jour
     */
    public static void mesurerPaquet (String chemin, PrintWriter paquets, PrintWriter classes) {
        mesurerSubPaquet(chemin, paquets, classes);
    }

    public static Paquet mesurerSubPaquet (String chemin, PrintWriter paquets, PrintWriter classes) {
        int nbLignesCloc = 0;
        int nbLignesLoc = 0;
        int complexite = 0;

        List<Path> cheminsFichiers = LecteurFichier.obtenirListeFichiers(chemin);

        cheminsFichiers.remove(0);

        for (Path path: cheminsFichiers) {
            File f = new File(path.toString());
            if(f.isDirectory()) {
                Paquet paquet = mesurerSubPaquet(path.toAbsolutePath().toString(), paquets, classes);
                nbLignesCloc += paquet.getNbLignesCloc();
                nbLignesLoc += paquet.getNbLignesLoc();
                complexite += paquet.getComplexite();
            }
            if(f.isFile() && f.getName().contains(JAVA_EXTENSION)) {
                nbLignesCloc += MesureClasse.classe_CLOC(path.toString());
                nbLignesLoc += MesureClasse.classe_LOC(path.toString());
                complexite += MesureClasse.classe_WMC(path.toString());
                float densite = MesureClasse.classe_DC(nbLignesCloc, nbLignesLoc);
                CsvWriter.ecrirePrintWriter(classes, path.toString(),
                        f.getName(), nbLignesLoc, nbLignesCloc, densite, complexite,
                        MesureClasse.classe_DC(densite, complexite));
            }
        }
        String nomDirectory = new File(chemin).getName();
        float densite = MesureClasse.classe_DC(nbLignesCloc, nbLignesLoc);
        CsvWriter.ecrirePrintWriter(paquets, chemin,
                nomDirectory, nbLignesLoc, nbLignesCloc, densite, complexite, paquet_DC(densite, complexite));

        return new Paquet(nbLignesLoc, nbLignesCloc, complexite);
    }

    /**
     * Calcul la densité d'un paquet
     * @param lignesCloc nombre de lignes commentaires
     * @param lignesLoc nombre de lignes code + commentaires
     * @return la densité du paquet
     */
    public static float paquet_DC (float lignesCloc, float lignesLoc) {
        if(lignesLoc != 0)
            return lignesCloc / lignesLoc;
        else
            return 0;
    }
}
