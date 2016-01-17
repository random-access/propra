package ess.algorithm;

/**
 * <code>ISolver</code> is the basic interface for calculating solutions for Roman Composites
 * and should be implemented by every concrete Solver. It defines a single method,
 * {@link #solve()}. Override this method with the specific calculation logic.
 * <br><br>
 * Currently an instance of solver <code>Solver</code> is initialized directly in
 * {@link RoemischerVerbund}, so changing the object type there changes the
 * Solver that gets used.
 * 
 * @author Monika Schrenk
 */
public interface ISolver {

    /**
     * Calculates a solution for a given <code>Composite</code>.
     *
     * @return <code>true</code>, if a solution could be calculated, else <code>false</code>.
     */
    boolean solve();
}
