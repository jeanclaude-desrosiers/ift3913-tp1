package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jclaude
 */
public class PackageMeasureLOCTest {

    /**
     * Test of measure method, of class PackageMeasureLOC.
     */
    @Test
    public void testMeasure_firstDir_shouldBe43() {
        System.out.println("testMeasure_firstDir_shouldBe43");
        PackageMeasureLOC instance = new PackageMeasureLOC();
        Number expected = 43;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getFirstDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(
                measureResults, TestUtils.getFirstDir());
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of measure method, of class PackageMeasureLOC.
     */
    @Test
    public void testMeasure_secondDir_shouldBe11() {
        System.out.println("testMeasure_secondDir_shouldBe11");
        PackageMeasureLOC instance = new PackageMeasureLOC();
        Number expected = 11;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getSecondDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(
                measureResults, TestUtils.getSecondDir());
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

}
