package ess.algorithm;

/**
 * <code>ISolver</code> is the basic interface for calculating solutions for Roman Composites
 * and should be implemented by every concrete <code>Solver</code>. It defines a single method,
 * {@link #solve()}. Override this method with the specific calculation logic.
 * Currently the concrete <code>Solver</code> gets instantiated directly in
 * {@link RoemischerVerbund}, so changing the object type there changes the
 * <code>Solver</code> that gets used.
 * 
 * @author Monika Schrenk
 */
public interface ISolver {

    /**
     * Calculates a solution for a given <code>Composite</code>.
     *
     * @return true, if a solution could be calculated, else false.
     */
    boolean solve();

}
