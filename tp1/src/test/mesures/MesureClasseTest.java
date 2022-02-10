package mesures;

import ift3913.tp1.mesure.MesureClasse;
import ift3913.tp1.model.Element;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MesureClasseTest {

    private final MesureClasse mesureClasse = new MesureClasse();

    private final String fichierTest1 =  System.getProperty("user.dir") +
            "/src/test/mockClasses/ClasseTestPremierNiveau.java";

    private final String fichierTest2 =  System.getProperty("user.dir") +
            "/src/test/mockClasses/deuxiemeNiveau/ClasseTestDeuxiemeNiveau.java";

    private final String fichierTestComplexite =  System.getProperty("user.dir") +
            "/src/test/mockClasses/ClasseTestComplexite.java";

    @Test
    public void mesurerClasse_surFichier1_testDoitRetourner15ClocEt22Loc() {

        Element element = mesureClasse.mesurerClasse(fichierTest1, "ClasseTestPremierNiveau.java");

        assertEquals(element.getChemin(), fichierTest1);
        assertEquals(element.getNom(), "ClasseTestPremierNiveau.java");
        assertEquals(element.getNbLignesCloc(),15);
        assertEquals(element.getNbLignesLoc(), 22);
        assertEquals(element.getComplexite(), 1);
    }

    @Test
    public void mesurerClasse_surFichier2_testDoitRetourner8ClocEt19Loc() {

        Element element = mesureClasse.mesurerClasse(fichierTest2, "ClasseTestDeuxiemeNiveau.java");

        assertEquals(element.getChemin(), fichierTest2);
        assertEquals(element.getNom(), "ClasseTestDeuxiemeNiveau.java");
        assertEquals(element.getNbLignesCloc(),8);
        assertEquals(element.getNbLignesLoc(), 19);
        assertEquals(element.getComplexite(), 1);
    }

    @Test
    public void mesureWMC_surFichierComplexite_testDoitRetournerComplexite14() {

        Element element = mesureClasse.mesurerClasse(fichierTestComplexite, "ClasseTestComplexite.java");

        assertEquals(element.getChemin(), fichierTestComplexite);
        assertEquals(element.getNom(), "ClasseTestComplexite.java");
        assertEquals(element.getNbLignesCloc(),1);
        assertEquals(element.getNbLignesLoc(), 37);
        assertEquals(element.getComplexite(), 14);
    }
}
