package mesures;

import ift3913.tp1.mesures.MesurePaquet;
import ift3913.tp1.model.Paquet;
import ift3913.tp1.utils.CsvWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class MesurePaquetTest {

    private final String dossierTest1 =  System.getProperty("user.dir") +
            "/src/test/mockClasses";

    private final String dossierTest2 =  System.getProperty("user.dir") +
            "/src/test/mockClasses/deuxiemeNiveau";

    private PrintWriter pwClasse;
    private PrintWriter pwPaquet;

    @Before
    public void setUp ()
    {
        pwClasse = CsvWriter.creerFichierCSVpourClasses();
        pwPaquet = CsvWriter.creerFichierCSVpourPaquets();
    }

    @After
    public void after() {
        pwClasse.close();
        pwPaquet.close();
    }

    @Test
    public void mesurerPaquet_mockClasses_testDoitRetourner23CommentairesEt41LignesDeCode() {

        Paquet paquet = MesurePaquet.mesurerSubPaquet(dossierTest1, pwClasse, pwClasse);
        assertEquals(paquet.getNbLignesCloc(),23);
        assertEquals(paquet.getNbLignesLoc(),41);
    }

    @Test
    public void mesurerPaquet_deuxiemeNiveau_testDoitRetourner8CommentairesEt19LignesDeCode() {

        Paquet paquet = MesurePaquet.mesurerSubPaquet(dossierTest2, pwClasse, pwClasse);
        assertEquals(paquet.getNbLignesCloc(),8);
        assertEquals(paquet.getNbLignesLoc(),19);
    }
}
