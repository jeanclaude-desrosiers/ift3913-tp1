package ift3913.tp1.mesure;

import ift3913.tp1.model.Element;
import ift3913.tp1.utils.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe qui contient les mesures des paquets
 */
public class MesurePaquet {

    private final String JAVA_EXTENSION = ".java";

    private MesureClasse mesureClasse = new MesureClasse();

    /**
     * Lance l'application des mesure récursivement à travers tous les paquets d'un dossier
     * @param chemin racine, début de l'analyse
     * @param paquets le fichier csv paquets à mettre à jour
     * @param classes le fichier csv classes à mettre à jour
     */
    public void mesurerPaquet (String chemin, PrintWriter paquets, PrintWriter classes) {
        mesurerSousPaquet(chemin, paquets, classes);
    }

    public Element mesurerSousPaquet(String chemin, PrintWriter paquetsWriter, PrintWriter classesWriter) {
        Element paquet = new Element();

        List<Path> cheminsFichiers = obtenirListeDesElementsEnfants(chemin);

        cheminsFichiers.remove(0);

        for (Path path: cheminsFichiers) {
            File f = new File(path.toString());
            if(f.isDirectory()) {
                Element sousPaquet = mesurerSousPaquet(path.toAbsolutePath().toString(), paquetsWriter, classesWriter);
                ajoutInformations(paquet, sousPaquet);
            }
            if(f.isFile() && f.getName().contains(JAVA_EXTENSION)) {
                Element sousClasse = mesureClasse.mesurerClasse(path.toAbsolutePath().toString(), f.getName());
                ajoutInformations(paquet, sousClasse);
                CsvWriter.ecrirePrintWriter(classesWriter, sousClasse);
            }
        }
        paquet.setNom(new File(chemin).getName());
        paquet.setChemin(chemin);
        paquet.calculeDensite();
        paquet.calculeDegreCommentaire();
        CsvWriter.ecrirePrintWriter(paquetsWriter, paquet);

        return paquet;
    }

    /**
     * Trouve les chemins de tous les elements enfants de profondeur 1
     * @param path chemin du fichier
     * @return liste de tous les chemins trouvés
     */
    private List<Path> obtenirListeDesElementsEnfants(String path) {
        List<Path> cheminsFichiers = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(path), 1)) {
            cheminsFichiers = paths.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cheminsFichiers;
    }

    private Element ajoutInformations (Element elementParent, Element elementEnfant) {
        elementParent.ajoutCloc(elementEnfant.getNbLignesCloc());
        elementParent.ajoutLoc(elementEnfant.getNbLignesLoc());
        elementParent.augmenteComplexite(elementEnfant.getComplexite());
        return elementParent;
    }
}
