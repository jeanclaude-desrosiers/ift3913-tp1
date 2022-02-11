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

    /**
     * This directory has.
     *
     * CLOC = 1 + 15 = 16
     *
     * LOC = 36 + 7 = 43
     *
     * WCP = 13 + 1 + 1 = 15
     *
     * @return
     */
    public static Path getFirstDir() {
        return Paths.get("resources");
    }

    /**
     * This file has.
     *
     * CLOC = 1
     *
     * LOC = 36
     *
     * WMC = 4 (# of methods) + 5 + 2 + 1 + 1 = 13
     *
     * @return
     */
    public static Path getClasseTestComplexite() {
        return Paths.get("resources", "ClasseTestComplexite.java");
    }

    /**
     * This file has.
     *
     * CLOC = 15
     *
     * LOC = 7
     *
     * WMC = 1 (# of methods)
     *
     * @return
     */
    public static Path getClasseTestPremierNiveau() {
        return Paths.get("resources", "ClasseTestPremierNiveau.java");
    }

    /**
     * This directory has.
     *
     * CLOC = 8
     *
     * LOC = 11
     *
     * WCP = 1
     *
     * @return
     */
    public static Path getSecondDir() {
        return Paths.get("resources", "deuxiemeNiveau");
    }

    /**
     * This file has.
     *
     * CLOC = 8
     *
     * LOC = 11
     *
     * WMC = 1 (# of methods)
     *
     * @return
     */
    public static Path getClasseTestDeuxiemeNiveau() {
        return Paths.get("resources", "deuxiemeNiveau", "ClasseTestDeuxiemeNiveau.java");
    }
    
    public static MeasureResult getPackageMeasureOnly(Collection<MeasureResult> measureResults, Path path) {
        return measureResults
                .stream()
                .filter(m -> m.getPath().equals(path))
                .findFirst()
                .orElseThrow();
    }
}
