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
        System.out.println("testMeasure_firstDir_shouldBe78");
        PackageMeasureLOC instance = new PackageMeasureLOC();
        Number expected = 78;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getFirstDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(measureResults);
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

    /**
     * Test of measure method, of class PackageMeasureLOC.
     */
    @Test
    public void testMeasure_secondDir_shouldBe11() {
        System.out.println("testMeasure_secondDir_shouldBe19");
        PackageMeasureLOC instance = new PackageMeasureLOC();
        Number expected = 19;

        Collection<MeasureResult> measureResults = instance.measure(TestUtils.getResDir(),
                TestUtils.getSecondDir());
        MeasureResult measureResult = TestUtils.getPackageMeasureOnly(measureResults);
        Number actual = measureResult.getNumericResult();

        assertEquals(expected, actual);
    }

}
