package ess.algorithm;

import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;

/**
 * The Interface <code>IRoemischerVerbund</code> defines the methods for API use.
 * 
 * @author Monika Schrenk
 */
public interface IRoemischerVerbund {

    /**
     * Tests if the given solution is correct. 
     *
     * @param xmlFile path to the document that needs to be validated.
     * @param maxGapLength maximum gap length that is allowed
     * @return a list of broken rules
     */
    List<Validation> validateSolution(String xmlFile, int maxGapLength);

    /**
     * Calculates a solution for the given data.
     *
     * @param xmlFile document holding the input data.
     * @param maxGapLength maximum gap length that is allowed
     * @return <code>true</code>, if a solution could be found, else <code>false</code>. 
     */
    boolean solve(String xmlFile, int maxGapLength);
}