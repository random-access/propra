package Rules_Component;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
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
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_2"), new Position(0,2)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_1"), new Position(0,3)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_4"), new Position(2,2)));
		
		IRule rule = new MaxLineLengthRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_4"), new Position(2,2)));
		
		System.out.println(c);
		assertTrue(validMove);
	}
	
	@Test
	public void MaxLineLengthTest2() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		c.setMaxLineLength(4);
		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_2"), new Position(0,2)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_1"), new Position(0,3)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_4"), new Position(2,2)));
		
		IRule rule = new MaxLineLengthRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_2"), new Position(2,2)));
		
		System.out.println(c);
		assertTrue(validMove);
	}
	
	@Test
	public void MaxLineLengthTest3() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		c.setMaxLineLength(3);
		Surface s = c.getSurface();
		s.insertEntry(new SurfaceEntry(c.findTileById("_0"), new Position(0,0)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_3"), new Position(0,2)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_4"), new Position(0,4)));
		s.insertEntry(new SurfaceEntry(c.findTileById("_4"), new Position(1,2)));
		
		IRule rule = new MaxLineLengthRule();
		boolean validMove = rule.check(c, new SurfaceEntry(c.findTileById("_3"), new Position(0,2)));
		
		System.out.println(c);
		assertTrue(validMove);
	}

}
