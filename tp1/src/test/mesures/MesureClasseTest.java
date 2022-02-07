package mesures;

import ift3913.tp1.mesures.MesureClasse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MesureClasseTest {

    private String fichierTest1 =  System.getProperty("user.dir") +
            "/src/test/mockClasses/ClasseTestPremierNiveau.java";

    private String fichierTest2 =  System.getProperty("user.dir") +
            "/src/test/mockClasses/deuxiemeNiveau/ClasseTestDeuxiemeNiveau.java";

    @Test
    public void mesureCLOC_surFichier1_testDoitRetourner15Commentaires() {

        int nbCommentaires = MesureClasse.classe_CLOC(fichierTest1);
        assertEquals(nbCommentaires,15);
    }

    @Test
    public void mesureLOC_surFichier1_testDoitRetourner22Lignes() {

        int nbCommentaires = MesureClasse.classe_LOC(fichierTest1);
        assertEquals(nbCommentaires,22);
    }

    @Test
    public void mesureCLOC_surFichier2_testDoitRetourner8Commentaires() {

        int nbCommentaires = MesureClasse.classe_CLOC(fichierTest2);
        assertEquals(nbCommentaires,8);
    }

    @Test
    public void mesureLOC_surFichier2_testDoitRetourner19Lignes() {

        int nbCommentaires = MesureClasse.classe_LOC(fichierTest2);
        assertEquals(nbCommentaires,19);
    }
}
