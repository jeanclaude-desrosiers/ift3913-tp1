package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jclaude
 */
public class PackageMeasureCLOCTest {

    /**
     * Test of measure method, of class PackageMeasureCLOC.
     */
    @Test
    public void testMeasure_firstDir_shouldBe24() {
        System.out.println("testMeasure_firstDir_shouldBe24");
        PackageMeasureCLOC instance = new PackageMeasureCLOC();
        Number expected = 24;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getFirstDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(measureResults);
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of measure method, of class PackageMeasureCLOC.
     */
    @Test
    public void testMeasure_secondDir_shouldBe8() {
        System.out.println("testMeasure_secondDir_shouldBe8");
        PackageMeasureCLOC instance = new PackageMeasureCLOC();
        Number expected = 8;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getSecondDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(measureResults);
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

}
