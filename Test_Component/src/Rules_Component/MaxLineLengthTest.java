package Rules_Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.IRule;
import ess.rules.explicit.MaxLineLengthRule;

public class MaxLineLengthTest {

    @Test
    public void MaxLineLengthTest1() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(4);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_0"), new Position(0, 0));
        s.insertEntry(c.findTileById("_2"), new Position(0, 2));
        s.insertEntry(c.findTileById("_1"), new Position(0, 3));

        IRule rule = new MaxLineLengthRule();
        Tile tile = c.findTileById("_4");
        Position pos = new Position(2, 2);
        boolean validMove = rule.check(c, tile, pos);
        s.insertEntry(tile, pos);

        // System.out.println("Test 1");
        // System.out.println(c);
        assertTrue(validMove);
    }

    @Test
    public void MaxLineLengthTest2() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(3);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_0"), new Position(0, 0));
        s.insertEntry(c.findTileById("_2"), new Position(0, 2));
        s.insertEntry(c.findTileById("_1"), new Position(0, 3));

        IRule rule = new MaxLineLengthRule();
        Tile tile = c.findTileById("_2");
        Position pos = new Position(3, 0);
        boolean validMove = rule.check(c, tile, pos);
        s.insertEntry(tile, pos);

        // System.out.println("Test 2");
        // System.out.println(c);
        assertTrue(validMove);
    }

    @Test
    public void MaxLineLengthTest3() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(3);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_0"), new Position(0, 0));
        s.insertEntry(c.findTileById("_3"), new Position(0, 2));
        s.insertEntry(c.findTileById("_4"), new Position(0, 4));
        s.insertEntry(c.findTileById("_4"), new Position(1, 2));

        IRule rule = new MaxLineLengthRule();
        Tile tile = c.findTileById("_3");
        Position pos = new Position(0, 2);
        boolean validMove = rule.check(c, tile, pos);
        s.insertEntry(tile, pos);

        // System.out.println("Test 3");
        // System.out.println(c);
        assertTrue(validMove);
    }

    @Test
    public void MaxLineLengthTest4() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(2);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_0"), new Position(0, 0));
        s.insertEntry(c.findTileById("_3"), new Position(0, 2));
        s.insertEntry(c.findTileById("_4"), new Position(0, 4));
        s.insertEntry(c.findTileById("_4"), new Position(1, 2));
        s.insertEntry(c.findTileById("_3"), new Position(2, 4));

        IRule rule = new MaxLineLengthRule();
        Tile tile = c.findTileById("_3");
        Position pos = new Position(2, 4);
        boolean validMove = rule.check(c, tile, pos);
        s.insertEntry(tile, pos);

        // System.out.println("Test 4");
        // System.out.println(c);
        assertFalse(validMove);
    }
    
    @Test
    public void MaxLineLengthTest5() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(2);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_0"), new Position(0, 0));

        IRule rule = new MaxLineLengthRule();
        Tile tile = c.findTileById("_2");
        Position pos = new Position(0, 2);
        boolean validMove = rule.check(c, tile, pos);
        s.insertEntry(tile, pos);

        // System.out.println("Test 5");
        // System.out.println(c);
        assertFalse(validMove);
    }

}
