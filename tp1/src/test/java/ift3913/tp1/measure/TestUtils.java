package ift3913.tp1.measure;

import ift3913.tp1.data.MeasureResult;
import ift3913.tp1.data.MeasureResultType;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

/**
 *
 * @author jclaude
 */
public class TestUtils {

    private TestUtils() {

    }

    public static Path getResDir() {
        return Paths.get("src", "test").toAbsolutePath();
    }

    public static Path getFirstDir() {
        return Paths.get("resources");
    }

    public static Path getClasseTestComplexite() {
        return Paths.get("resources", "ClasseTestComplexite.java");
    }

    public static Path getClasseTestPremierNiveau() {
        return Paths.get("resources", "ClasseTestPremierNiveau.java");
    }

    public static Path getSecondDir() {
        return Paths.get("resources", "deuxiemeNiveau");
    }

    public static Path getClasseTestDeuxiemeNiveau() {
        return Paths.get("resources", "deuxiemeNiveau", "ClasseTestDeuxiemeNiveau.java");
    }

    public static MeasureResult getPackageMeasureOnly(Collection<MeasureResult> measureResults) {
        return measureResults
                .stream()
                .filter(m -> m.getType() == MeasureResultType.PACKAGE)
                .findFirst()
                .orElseThrow();
    }
}
