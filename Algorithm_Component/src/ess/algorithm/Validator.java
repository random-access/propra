package ess.algorithm;

import java.util.List;

import ess.algorithm.RoemischerVerbund.Validation;
import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.TopToBottomPosFinder;
import ess.algorithm.modules.ValidationRuleChecker;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;

/**
 * This class is responsible for validating a composite with a given installation plan against a set
 * of rules activated via configuration file.
 * 
 * @author Monika Schrenk
 */
public class Validator { 

    private Composite composite;
    private IPositionFinder posFinder;
    private ValidationRuleChecker ruleChecker;
    
    /**
     * Instantiates a new <code>Validator</code> and sets up modules optimized for validation, therefore using
     * 
     * <ul>
     *      <li>A <code>TopToBottomPosFinder</code>, because the installation plan provides the tiles from top 
     *      left to bottom right</li>
     *      <li>No <code>ITileChooser</code>, because the tiles must not be selected from a list for validation</li>
     *      <li>A <code>ValidationRuleChecker</code> which checks every <code>IRule</code> even if a previous one
     *      was already broken, because this information is needed for returning an error list</li>
     * </ul>
     * 
     * @param composite holding the data the validator needs for building a solution
     * @throws PropertyException if any module was not defined properly in the configuration file or the configuration file 
     * cannot be read
     */
    public Validator(Composite composite) throws PropertyException {
        this.composite = composite;
        posFinder = new TopToBottomPosFinder();
        ruleChecker = new ValidationRuleChecker(composite);
    }
    /**
     * Validates the given composite against the rules activated in the configuration file. 
     * @return a list of rules that were broken at least once, or an empty list if no error occurred 
     * during construction.
     */
    public List<Validation> validateSolution() {
        fillSurface();
        return ruleChecker.getErrorList();
    }

    // fills the surface with tiles in tileList, checking all rules before placing a tile.
    private void fillSurface() {
        Position pos = null;
        Tile tile = null;
        
        // try to place all tiles in the composite's tile list in the surface
        for (String ident : composite.getSurfaceTileList()) {
            pos = posFinder.findNextFreePosition(composite, pos);
            tile = composite.findTileById(ident);
            if (ruleChecker.checkImplicitRules(composite, tile, pos)) {
                ruleChecker.checkExplicitRules(composite, tile, pos);
                // if an explicit rule gets broken, continue to validate to maybe find other broken rules
                composite.getSurface().insertEntry(tile, pos);
            } else {
                // if an implicit rule gets broken (tiles overlapping the surface or other tiles
                // or too many / not enough tiles in construction plan)
                // place tile everywhere where it is possible, don't care about overwriting other tiles
                composite.getSurface().insertEntryWherePossible(tile, pos);
            }
        }
        
        // test if the surface is filled completely
        pos = posFinder.findNextFreePosition(composite, pos);
        ruleChecker.checkEndConditions(composite, tile, pos);
    }
}
