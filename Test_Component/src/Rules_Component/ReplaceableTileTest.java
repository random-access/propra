package Rules_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.ReplacableTileRule;

public class ReplaceableTileTest {

	
	@Test
	public void testHasSideReplacementEdgePos() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_4"), new Position(1,0));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_2"), new Position(1,2));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		ReplacableTileRule rule = new ReplacableTileRule();
		assertFalse(rule.check(c, e2));
	}
	
	
	@Test
	public void testHasTopReplacementInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_4"), new Position(2,2));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(1,2));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		ReplacableTileRule rule = new ReplacableTileRule();
		assertFalse(rule.check(c, e2));
	}
	
	@Test
	public void testHasNoReplacement() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_4"), new Position(2,2));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(1,1));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		ReplacableTileRule rule = new ReplacableTileRule();
		assertTrue(rule.check(c, e2));
	}
	
	@Test
	public void testHasSmallerAndLargerReplacement() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(1,2));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_2"), new Position(2,2));
		SurfaceEntry e3 = new SurfaceEntry(c.findTileById("_2"), new Position(2,3));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		c.getSurface().insertEntry(e3);
		System.out.println(c);
		ReplacableTileRule rule = new ReplacableTileRule();
		assertFalse(rule.check(c, e3));
	}
}
