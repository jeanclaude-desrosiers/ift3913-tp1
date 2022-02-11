package ift3913.tp1.measure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jclaude
 */
public class ClassMeasureWMCTest {

    /**
     * Test of getNumericResult method, of class ClassMeasureWMC.
     */
    @Test
    public void testGetNumericResult_ClasseTestPremierNiveau_shouldBe1() {
        System.out.println("testGetNumericResult_ClasseTestPremierNiveau_shouldBe1");
        ClassMeasureWMC instance = new ClassMeasureWMC();
        Number expected = 1;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestPremierNiveau()).getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumericResult method, of class ClassMeasureWMC.
     */
    @Test
    public void testGetNumericResult_ClasseTestDeuxiemeNiveau_shouldBe1() {
        System.out.println("testGetNumericResult_ClasseTestDeuxiemeNiveau_shouldBe1");
        ClassMeasureWMC instance = new ClassMeasureWMC();
        Number expected = 1;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestDeuxiemeNiveau()).getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of getNumericResult method, of class ClassMeasureWMC.
     */
    @Test
    public void testGetNumericResult_ClasseTestComplexite_shouldBe14() {
        System.out.println("testGetNumericResult_ClasseTestComplexite_shouldBe14");
        ClassMeasureWMC instance = new ClassMeasureWMC();
        Number expected = 14;

        Number actual = instance.measureClass(TestUtils.getResDir(),
                TestUtils.getClasseTestComplexite()).getNumericResult();

        assertEquals(expected, actual);
    }

}
