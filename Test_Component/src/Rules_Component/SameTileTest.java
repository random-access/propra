package Rules_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceEntry;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.explicit.SameTileRule;

public class SameTileTest {

	@Test
	public void testSameTileBottomInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(2,1));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(1,1));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertFalse(rule.check(c, e2));
	}
	
	@Test
	public void testSameTileRightInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(2,2));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(2,0));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertFalse(rule.check(c, e2));
	}
	
	@Test
	public void testSameTileLeftInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(2,0));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(2,2));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertFalse(rule.check(c, e2));
	}
	
	@Test
	public void testSameTileTopInside() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(1,1));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(2,1));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertFalse(rule.check(c, e2));
	}

	@Test
	public void testSameTileButNoCommonEdge() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(2,2));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(1,1));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertTrue(rule.check(c, e2));
	}
	
	@Test
	public void testTileWithNoNeighbours() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(2,2));
		c.getSurface().insertEntry(e1);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertTrue(rule.check(c, e1));
	}
	
	@Test
	public void testBorderTileWithNoNeighbours() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(0,0));
		c.getSurface().insertEntry(e1);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertTrue(rule.check(c, e1));
	}
	
	@Test
	public void testBorderTileWithSameTile() throws DataExchangeException {
		XMLDataExchanger xmlEx = new XMLDataExchanger();
		Composite c = xmlEx.readFromSource("instances/solveInstances/test5.xml");
		SurfaceEntry e1 = new SurfaceEntry(c.findTileById("_3"), new Position(0,0));
		SurfaceEntry e2 = new SurfaceEntry(c.findTileById("_3"), new Position(1,0));
		c.getSurface().insertEntry(e1);
		c.getSurface().insertEntry(e2);
		System.out.println(c);
		SameTileRule rule = new SameTileRule();
		assertFalse(rule.check(c, e2));
	}
}
