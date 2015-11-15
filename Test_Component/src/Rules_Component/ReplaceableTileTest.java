package Rules_Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.explicit.ReplaceableTileRule;

public class ReplaceableTileTest {

	
	@Test
	public void testHasSideReplacementEdgePos() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");

		c.getSurface().insertEntry(c.findTileById("_4"), new Position(1,0));
		
		ReplaceableTileRule rule = new ReplaceableTileRule();
		assertFalse(rule.check(c, c.findTileById("_2"), new Position(1,2)));
		
		c.getSurface().insertEntry(c.findTileById("_2"), new Position(1,2));
		System.out.println(c);
	}
	
	
	@Test
	public void testHasTopReplacementInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		
		c.getSurface().insertEntry(c.findTileById("_4"), new Position(2,2));
		
		ReplaceableTileRule rule = new ReplaceableTileRule();
		assertFalse(rule.check(c, c.findTileById("_3"), new Position(1,2)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1,2));
		System.out.println(c);
	}
	
	@Test
	public void testHasNoReplacement() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");

		c.getSurface().insertEntry(c.findTileById("_4"), new Position(2,2));
		
		ReplaceableTileRule rule = new ReplaceableTileRule();
		assertTrue(rule.check(c, c.findTileById("_3"), new Position(1,1)));
		
		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1,1));
		System.out.println(c);
	}
	
	@Test
	public void testHasSmallerAndLargerReplacement() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");

		c.getSurface().insertEntry(c.findTileById("_3"), new Position(1,2));
		c.getSurface().insertEntry(c.findTileById("_2"), new Position(2,2));
		
		ReplaceableTileRule rule = new ReplaceableTileRule();
		assertFalse(rule.check(c, c.findTileById("_2"), new Position(2,3)));
		
		c.getSurface().insertEntry(c.findTileById("_2"), new Position(2,3));
		System.out.println(c);
	}
}
