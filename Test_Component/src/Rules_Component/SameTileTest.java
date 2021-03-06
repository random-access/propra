package Rules_Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.explicit.SameTileRule;

public class SameTileTest {

	@Test
	public void testSameTileBottomInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");

		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 1));
			
		SameTileRule rule = new SameTileRule(c);
		assertFalse(rule.check(c.findTileById("_3"), new Position(1, 1)));

		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1, 1));
	}
	
	@Test
	public void testSameTileRightInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 2));
		
		SameTileRule rule = new SameTileRule(c);
		assertFalse(rule.check(c.findTileById("_3"), new Position(2, 0)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 0));
	}
	
	@Test
	public void testSameTileLeftInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 0));
		
		SameTileRule rule = new SameTileRule(c);
		assertFalse(rule.check(c.findTileById("_3"), new Position(2, 2)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 2));
	}
	
	@Test
	public void testSameTileTopInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1, 1));
		
		SameTileRule rule = new SameTileRule(c);
		assertFalse(rule.check(c.findTileById("_3"), new Position(2, 1)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 1));
	}

	@Test
	public void testSameTileButNoCommonEdge() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 2));
		
		SameTileRule rule = new SameTileRule(c);
		assertTrue(rule.check(c.findTileById("_3"), new Position(1, 1)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1, 1));
	}
	
	@Test
	public void testTileWithNoNeighbours() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		SameTileRule rule = new SameTileRule(c);
		assertTrue(rule.check(c.findTileById("_3"), new Position(2, 2)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(2, 2));
	}
	
	@Test
	public void testBorderTileWithNoNeighbours() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		SameTileRule rule = new SameTileRule(c);
		assertTrue(rule.check(c.findTileById("_3"), new Position(0, 0)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(0, 0));
	}
	
	@Test
	public void testBorderTileWithSameTile() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(0, 0));
		
		SameTileRule rule = new SameTileRule(c);
		assertFalse(rule.check(c.findTileById("_3"), new Position(1, 0)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1, 0));
	}
}
