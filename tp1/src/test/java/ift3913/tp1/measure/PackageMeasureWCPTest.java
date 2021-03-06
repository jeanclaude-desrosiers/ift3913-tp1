package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jclaude
 */
public class PackageMeasureWCPTest {

    /**
     * Test of measure method, of class PackageMeasureWCP.
     */
    @Test
    public void testMeasure_firstDir_shouldBe15() {
        System.out.println("testMeasure_firstDir_shouldBe15");
        PackageMeasureWCP instance = new PackageMeasureWCP();
        Number expected = 15;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getFirstDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(
                measureResults, TestUtils.getFirstDir());
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of measure method, of class PackageMeasureWCP.
     */
    @Test
    public void testMeasure_secondDir_shouldBe1() {
        System.out.println("testMeasure_secondDir_shouldBe1");
        PackageMeasureWCP instance = new PackageMeasureWCP();
        Number expected = 1;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getSecondDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(
                measureResults, TestUtils.getSecondDir());
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

}
