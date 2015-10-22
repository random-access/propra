package Rules_Component;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.SurfaceUtils;
import ess.data.Tile;
import ess.data.TileUtils;
import ess.io.XMLDataExchanger;
import ess.io.exc.DataExchangeException;

public class NoCrossingsTest {

	@Test
	public void testInnerTileWithoutCrossing() throws DataExchangeException {
		XMLDataExchanger xmlExchanger = new XMLDataExchanger();
		Composite c = xmlExchanger.readFromSource("instances/solveInstances/test4.xml");
		ArrayList<Tile> tiles = c.getTileSorts();
		String[][] surface = SurfaceUtils.initSurface(c.getRows(), c.getCols());
		SurfaceUtils.insertTile(surface, TileUtils.findTileByIdent(tiles, "_0"), new Position(0,0));
		SurfaceUtils.insertTile(surface, TileUtils.findTileByIdent(tiles, "_2"), new Position(0,2));
		SurfaceUtils.insertTile(surface, TileUtils.findTileByIdent(tiles, "_1"), new Position(0,3));
		System.out.println(c);
		
		// IRule rule = new NoCrossingsRule();
		// boolean validMove = rule.checkCurrentMove(surface, TileUtils.findTileByIdent(c.getTileSorts(), "_4"), new Point(2,2));
		
		// assertTrue(validMove);
	}
	
	@Test
	public void testInnerTileWithCrossing() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testBorderTileWithoutCrossing() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testBorderTileWithCrossing() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWholeSurfaceWithoutCrossing() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWholeSurfaceWithCrossing() {
		fail("Not yet implemented");
	}

}
