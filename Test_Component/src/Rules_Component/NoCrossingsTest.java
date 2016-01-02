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
import ess.rules.explicit.CrossingsRule;

public class NoCrossingsTest {

	@Test
	public void testInnerTileWithoutCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		Surface s = c.getSurface();
		s.insertEntry(c.findTileById("_0"), new Position(0, 0));
		s.insertEntry(c.findTileById("_2"), new Position(0, 2));
		s.insertEntry(c.findTileById("_1"), new Position(0, 3));
		
		IRule rule = new CrossingsRule(c);
		Tile tile = c.findTileById("_4");
		Position pos = new Position(2, 2);
		boolean validMove = rule.check(tile, pos);
		s.insertEntry(tile, pos);
		
		assertTrue(validMove);
	}
	
	@Test
	public void testInnerTileWithoutCrossing2() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		Surface s = c.getSurface();
		s.insertEntry(c.findTileById("_0"), new Position(0, 0));
		s.insertEntry(c.findTileById("_1"), new Position(0, 3));
		
		IRule rule = new CrossingsRule(c);
		Tile tile = c.findTileById("_0");
        Position pos = new Position(2, 3);
		boolean validMove = rule.check(tile, pos);
		s.insertEntry(tile, pos);
		
		assertTrue(validMove);
	}
	
	   @Test
	    public void testInnerTileWithoutCrossing3() throws DataExchangeException {
	        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
	        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
	        Surface s = c.getSurface();
	        s.insertEntry(c.findTileById("_0"), new Position(2, 4));
	        
	        IRule rule = new CrossingsRule(c);
	        Tile tile = c.findTileById("_1");
	        Position pos = new Position(2, 1);
	        boolean validMove = rule.check(tile, pos);
	        s.insertEntry(tile, pos);
	        
	        assertTrue(validMove);
	    }
	
	
	@Test
	public void testInnerTileWithCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		Surface s = c.getSurface();
		s.insertEntry(c.findTileById("_0"), new Position(0, 0));
		s.insertEntry(c.findTileById("_2"), new Position(0, 2));
		s.insertEntry(c.findTileById("_1"), new Position(0, 3));
		
		IRule rule = new CrossingsRule(c);
		Tile tile = c.findTileById("_0");
        Position pos = new Position(2, 3);
		boolean validMove = rule.check(tile, pos);
		s.insertEntry(tile, pos);
		
		assertFalse(validMove);
	}
	
	@Test
	public void testBorderTileWithoutCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test3.xml");

		Surface s = c.getSurface();
		s.insertEntry(c.findTileById("_0"), new Position(21, 17));
		
		IRule rule = new CrossingsRule(c);
		Tile tile = c.findTileById("_1");
        Position pos = new Position(22, 19);
		boolean validMove = rule.check(tile, pos);
		s.insertEntry(tile, pos);
		
		assertTrue(validMove);
	}
	
	@Test
	public void testBorderTileWithCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		
		Surface s = c.getSurface();
		s.insertEntry(c.findTileById("_0"), new Position(3, 0));
		s.insertEntry(c.findTileById("_2"), new Position(1, 2));
		
		IRule rule = new CrossingsRule(c);
		Tile tile = c.findTileById("_0");
        Position pos = new Position(0, 0);
		boolean validMove = rule.check(tile, pos);
		s.insertEntry(tile, pos);
		
		assertFalse(validMove);
	}
	
	   @Test
	    public void testBorderTileWithCrossing2() throws DataExchangeException {
	        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
	        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
	        
	        Surface s = c.getSurface();
	        s.insertEntry(c.findTileById("_0"), new Position(0, 0));
	        s.insertEntry(c.findTileById("_2"), new Position(1, 2));
	        
	        IRule rule = new CrossingsRule(c);
	        Tile tile = c.findTileById("_0");
	        Position pos = new Position(3, 0);
	        boolean validMove = rule.check(tile, pos);
	        s.insertEntry(tile, pos);
	        
	        assertFalse(validMove);
	    }
	   
	    @Test
	    public void testBorderTileWithCrossing3() throws DataExchangeException {
	        XMLDataExchanger xmlExchanger = new XMLDataExchanger();
	        Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
	        
	        Surface s = c.getSurface();
	        s.insertEntry(c.findTileById("_0"), new Position(3, 0));
	        
	        IRule rule = new CrossingsRule(c);
	        Tile tile = c.findTileById("_2");
	        Position pos = new Position(1, 2);
	        boolean validMove = rule.check(tile, pos);
	        s.insertEntry(tile, pos);
	        
	        assertFalse(validMove);
	    }

}
