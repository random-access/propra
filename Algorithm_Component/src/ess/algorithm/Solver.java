package ess.algorithm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import ess.algorithm.modules.IPositionFinder;
import ess.algorithm.modules.IRuleChecker;
import ess.algorithm.modules.ITileChooser;
import ess.algorithm.modules.SolveRuleChecker;
import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.exc.PropertyException;
import ess.utils.ProPraLogger;
import ess.utils.ProPraProperties;

/**
 * This class implements a single-threaded Solver, which finds a solution for a
 * given surface size and a given maximum tile length which respects the rules
 * activated in the configuration file if a solution exists.
 * 
 * @see ISolver
 * 
 * @author Monika Schrenk
 */
public class Solver implements ISolver {

    // private static final Logger log = Logger.getGlobal();

    private IPositionFinder posFinder;
    private IRuleChecker ruleChecker;
    private ITileChooser tileChooser;
    
    private LinkedList<Position> posList;
    private Composite composite;

    // private long counter;

    /**
     * Instantiates a new Solver and loads the modules. Modules are parts of the
     * algorithm that can be configured via configuration file to optimize the
     * algorithm. The algorithm can be influenced by:
     * 
     * <ul>
     * <li>Use of different TileChoosers (how the next tile from tile sorts gets
     * chosen)</li>
     * <li>Use of different PositionFinders (which return the next position in
     * the surface to place a tile)</li>
     * <li>Use of different RuleCheckers (currently there is only 1 RuleChecker
     * optimized for solving)</li>
     * </ul>
     *
     * @param composite
     *            holding the data the solver needs for building a solution
     * @throws PropertyException
     *             if any module was not defined properly in the configuration
     *             file or the configuration file cannot be read
     */
    public Solver(Composite composite) throws PropertyException {
        ProPraLogger.setup();
        this.composite = composite;
        posList = new LinkedList<>();
        loadModules();
    }

    private void loadModules() throws PropertyException {
        try {
            // load properties from file
            ProPraProperties properties = ProPraProperties.getInstance();

            // initialize selected implementation of IPositionFinder
            String posFinderName = ProPraProperties.HEURISTICS_PACKAGE
                    + properties.getValue(ProPraProperties.KEY_POSITION_FINDER);
            posFinder = (IPositionFinder) Class.forName(posFinderName).newInstance();

            // initialize RuleChecker
            ruleChecker = new SolveRuleChecker();

            // initialize selected implementation of ITileChooser
            String tileChooserName = ProPraProperties.HEURISTICS_PACKAGE + properties.getValue(ProPraProperties.KEY_TILE_CHOOSER);
            Constructor<?> constructor = Class.forName(tileChooserName).getConstructor(Composite.class);
            tileChooser = (ITileChooser) constructor.newInstance(composite);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException
                | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            throw new PropertyException(
                    "Invalid parameter in properties file in heuristics. Please check if your properties file is valid.");
        }
    }

    /**
     * @see ess.algorithm.ISolver#solve()
     */
    @Override
    public boolean solve() {
        Position pos = posFinder.findNextFreePosition(composite, null);
        Tile tile = null;
        boolean foundTileThatFits;

        // try to place tiles using backtracking as long as there are any
        // possibilities
        do {
            tile = tileChooser.getNextTile(tile);
            foundTileThatFits = false;

            // try out all possible tiles at the current position
            while (tile != null) {
                if (placeNextTile(tile, pos)) {
                    posList.add(pos);
                    foundTileThatFits = true;
                    pos = posFinder.findNextFreePosition(composite, pos);
                    if (ruleChecker.checkEndConditions(composite, tile, pos)) {
                        // log.info("Iterations: " + counter);
                        // log.info("Found a solution :) \n" + c);
                        prepareCompositeForDataOutput();
                        return true;
                    }
                    tile = null;
                } else {
                    tile = tileChooser.getNextTile(tile);
                }
            }

            // retrieve last position from posList, if no possible tile
            // could be found at the current position
            if (!foundTileThatFits) {
                pos = posList.pollLast();

                // pos is null if tile list is empty
                if (pos != null) {
                    tile = composite.getSurface().getEntryAt(pos);
                    composite.getSurface().removeEntry(tile, pos);
                }
                if (posList.isEmpty()) {
                    System.out.println("Choose another tile as first...");
                }
            }
        } while (pos != null);
        // log.info("Iterations: " + counter);
        // log.info("Found no solution :(.");
        return false;
    }

    // try to insert tile at pos
    // check all rules, return true if tile could be placed, else false
    private boolean placeNextTile(Tile tile, Position pos) {
        if (ruleChecker.checkImplicitRules(composite, tile, pos) && ruleChecker.checkExplicitRules(composite, tile, pos)) {
            composite.getSurface().insertEntry(tile, pos);
            // System.out.println(composite);
            // counter++;
            return true;
        }
        return false;
    }

    // Construct array list of tiles which are in the surface, ordered from top
    // left to bottom right
    // to be able to hand this directly over to the writer for the data output
    private void prepareCompositeForDataOutput() {
        // In case TileChooser doesn't choose positions in order, they must get
        // sorted now
        Collections.sort(posList);

        ArrayList<String> output = new ArrayList<>();
        for (Position pos : posList) {
            output.add(composite.getSurface().getEntryAt(pos).getId());
        }
        composite.setSurfaceTileList(output);
    }

}
