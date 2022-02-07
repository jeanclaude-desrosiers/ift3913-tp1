package mesures;

import ift3913.tp1.mesure.MesurePaquet;
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
    public void mesurerPaquet_mockClasses_testDoitRetourner24CommentairesEt41LignesDeCodeEtComplexite16() {

        Paquet paquet = MesurePaquet.mesurerSubPaquet(dossierTest1, pwPaquet, pwClasse);
        assertEquals(paquet.getNbLignesCloc(),24);
        assertEquals(paquet.getNbLignesLoc(),78);
        assertEquals(paquet.getComplexite(),16);
    }

    @Test
    public void mesurerPaquet_deuxiemeNiveau_testDoitRetourner8CommentairesEt19LignesDeCodeEtComplexite1() {

        Paquet paquet = MesurePaquet.mesurerSubPaquet(dossierTest2, pwPaquet, pwClasse);
        assertEquals(paquet.getNbLignesCloc(),8);
        assertEquals(paquet.getNbLignesLoc(),19);
        assertEquals(paquet.getComplexite(),1);
    }
}
