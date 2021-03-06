package ift3913.tp1.measure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jclaude
 */
public class ClassMeasureLOCTest {

    /**
     * Test of getNumericResult method, of class ClassMeasureLOC.
     */
    @Test
    public void testGetNumericResult_ClasseTestPremierNiveau_shouldBe7() {
        System.out.println("testGetNumericResult_ClasseTestPremierNiveau_shouldBe7");
        ClassMeasureLOC instance = new ClassMeasureLOC();
        Number expected = 7;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestPremierNiveau()).getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumericResult method, of class ClassMeasureLOC.
     */
    @Test
    public void testGetNumericResult_ClasseTestDeuxiemeNiveau_shouldBe11() {
        System.out.println("testGetNumericResult_ClasseTestDeuxiemeNiveau_shouldBe11");
        ClassMeasureLOC instance = new ClassMeasureLOC();
        Number expected = 11;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestDeuxiemeNiveau()).getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumericResult method, of class ClassMeasureLOC.
     */
    @Test
    public void testGetNumericResult_ClasseTestComplexite_shouldBe36() {
        System.out.println("testGetNumericResult_ClasseTestComplexite_shouldBe36");
        ClassMeasureLOC instance = new ClassMeasureLOC();
        Number expected = 36;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestComplexite()).getNumericResult();

        assertEquals(expected, actual);
    }

}
