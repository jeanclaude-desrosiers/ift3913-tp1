package mesures;

import ift3913.tp1.mesures.MesureClasse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MesureClasseTest {

    private String fichierTest =  System.getProperty("user.dir") + "/src/test/test-resources/fichierTest.txt";

    @Test
    public void mesureCLOC_testDoitRetourner17Commentaires() {

        int nbCommentaires = MesureClasse.classe_CLOC(fichierTest);
        assertEquals(nbCommentaires,17);
    }

    @Test
    public void mesureLOC_testDoitRetourner27Lignes() {

        int nbCommentaires = MesureClasse.classe_LOC(fichierTest);
        assertEquals(nbCommentaires,27);
    }
}
