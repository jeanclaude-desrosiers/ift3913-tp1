package ift3913.tp1.measure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jclaude
 */
public class ClassMeasureCLOCTest {

    /**
     * Test of getNumericResult method, of class ClassMeasureCLOC.
     */
    @Test
    public void testGetNumericResult_ClasseTestPremierNiveau_shouldBe15() {
        System.out.println("testGetNumericResult_ClasseTestPremierNiveau_shouldBe15");
        ClassMeasureCLOC instance = new ClassMeasureCLOC();
        Number expected = 15;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestPremierNiveau()).getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumericResult method, of class ClassMeasureCLOC.
     */
    @Test
    public void testGetNumericResult_ClasseTestDeuxiemeNiveau_shouldBe8() {
        System.out.println("testGetNumericResult_ClasseTestDeuxiemeNiveau_shouldBe8");
        ClassMeasureCLOC instance = new ClassMeasureCLOC();
        Number expected = 8;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestDeuxiemeNiveau()).getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumericResult method, of class ClassMeasureCLOC.
     */
    @Test
    public void testGetNumericResult_ClasseTestComplexite_shouldBe1() {
        System.out.println("testGetNumericResult_ClasseTestComplexite_shouldBe1");
        ClassMeasureCLOC instance = new ClassMeasureCLOC();
        Number expected = 1;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestComplexite()).getNumericResult();

        assertEquals(expected, actual);
    }

}
