package mesures;

import ift3913.tp1.mesures.MesurePaquet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MesurePaquetTest {

    private final String dossierTest =  System.getProperty("user.dir") + "/src/test/test-resources/dossierTest";

    @Test
    public void mesureCLOC_testDoitRetourner7Commentaires() {

        int nbCommentaires = MesurePaquet.paquet_CLOC(dossierTest);
        assertEquals(nbCommentaires,7);
    }

    @Test
    public void mesureLOC_testDoitRetourner18Lignes() {

        int nbCommentaires = MesurePaquet.paquet_LOC(dossierTest);
        assertEquals(nbCommentaires,18);
    }
}
