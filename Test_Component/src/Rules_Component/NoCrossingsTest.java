package Rules_Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;
import ess.rules.IRule;
import ess.rules.NoCrossingsRule;

public class NoCrossingsTest {

	@Test
	public void testInnerTileWithoutCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_2"), new Position(0,2)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_1"), new Position(0,3)));
		
		IRule rule = new NoCrossingsRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_4"), new Position(2,2)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_4"), new Position(2,2)));
		System.out.println(c);
		assertTrue(validMove);
	}
	
	@Test
	public void testInnerTileWithoutCrossing2() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_1"), new Position(0,3)));
		
		IRule rule = new NoCrossingsRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_0"), new Position(2,3)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(2,3)));
		System.out.println(c);
		
		assertTrue(validMove);
	}
	
	
	@Test
	public void testInnerTileWithCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_2"), new Position(0,2)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_1"), new Position(0,3)));
		
		IRule rule = new NoCrossingsRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_0"), new Position(2,3)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(2,3)));
		System.out.println(c);
		
		assertFalse(validMove);
	}
	
	@Test
	public void testBorderTileWithoutCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test3.xml");

		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(21,17)));
		
		IRule rule = new NoCrossingsRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_1"), new Position(22,19)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_1"), new Position(22,19)));
		System.out.println(c);
		
		assertTrue(validMove);
	}
	
	@Test
	public void testBorderTileWithCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		
		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_2"), new Position(1,2)));
		
		IRule rule = new NoCrossingsRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_0"), new Position(3,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(3,0)));
		System.out.println(c);
		
		assertFalse(validMove);
	}

}
