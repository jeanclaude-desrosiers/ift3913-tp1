package ift3913.tp1.measure;

/**
 * Parent class of all types of measures. Handles the 'name of the measure'
 * attribute that is common to all measures
 *
 * @author jclaude
 */
public abstract class Measure {

    /**
     * Name of the measure (and not the object(s) it measures)
     */
    private final String name;

    public Measure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
