package Rules_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.IRule;
import ess.rules.additional.MinDistanceToBorderRule;

public class MinDistanceToBorderTest {

    @Test
    public void MinimalTileTest1() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(4);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_2"), new Position(0,0));
        
        IRule rule = new MinDistanceToBorderRule();
        boolean validMove = rule.check(c, c.findTileById("_0"), new Position(0,1));
        s.insertEntry(c.findTileById("_0"), new Position(0,1));
        
        System.out.println(c);
        assertFalse(validMove);
    }
    
    @Test
    public void MinimalTileTest2() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(4);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_2"), new Position(0,0));
        
        IRule rule = new MinDistanceToBorderRule();
        boolean validMove = rule.check(c, c.findTileById("_0"), new Position(0,1));
        s.insertEntry(c.findTileById("_0"), new Position(0,1));
        
        System.out.println(c);
        // TODO recognize if neighbourTile is caged
        assertFalse(validMove);
    }

    @Test
    public void MinimalTileTest3() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(4);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_3"), new Position(5,4));
        
        IRule rule = new MinDistanceToBorderRule();
        boolean validMove = rule.check(c, c.findTileById("_1"), new Position(3,3));
        s.insertEntry(c.findTileById("_1"), new Position(3,3));
        
        System.out.println(c);
        assertFalse(validMove);
    }
    
    @Test
    public void MinimalTileTest4() throws DataExchangeException {
        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
        c.setMaxLineLength(4);
        Surface s = c.getSurface();
        s.insertEntry(c.findTileById("_3"), new Position(5,3));
        
        IRule rule = new MinDistanceToBorderRule();
        boolean validMove = rule.check(c, c.findTileById("_1"), new Position(3,3));
        s.insertEntry(c.findTileById("_1"), new Position(3,3));
        
        System.out.println(c);
        assertFalse(validMove);
    }

}
