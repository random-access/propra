package ess.algorithm;

/**
 * ISolver is the basic interface for calculating solutions for Roman Composites
 * and should be implemented by all Solvers. It defines a single method,
 * {@link #solve()}. Override this method with the specific calculation logic.
 * Currently the concrete solver gets instantiated directly in
 * {@link RoemischerVerbund}, so changing the object type there changes the
 * solver that gets used.
 * 
 * @author Monika Schrenk
 */
public interface ISolver {

    /**
     * Tries to calculate a solution for the given restrictions.
     *
     * @return true, if a solution could be calculated, else false.
     */
    public boolean solve();

}
