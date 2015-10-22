package Algorithm_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Position;
import ess.data.SurfaceUtils;
import ess.data.Tile;

public class Surface_Utils_Test {

	@Test
	public void testInsertTileOrder() {
		// Arrange (set all necessary preconditions and inputs.)
		int cols = 10;
		int rows = 6;
		String[][] surface = SurfaceUtils.initSurface(rows, cols);

		// Act (on the object or method under test.)
		for (int i = 0; i < cols * rows; i++) {
			Tile t = new Tile("_" + i, 1, 1);
			Position p = new Position(i / cols, i % cols);
			SurfaceUtils.insertTile(surface, t, p);
		}
		SurfaceUtils.printSurface(surface);

		// Assert (that the expected results have occurred.))
		int currentId = 0;
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[0].length; j++) {
				assertEquals("Wrong value in surface. ", surface[i][j], "_" + currentId++);
			}
		}
	}

	@Test
	public void testInsertTileOrientation() {
		// Arrange (set all necessary preconditions and inputs.)
		int width = 5;
		int height = 3;
		String[][] surface = SurfaceUtils.initSurface(width, height);
		Tile t1 = new Tile("_2", 1, 3);
		Position p1 = new Position(0, 1);

		// Act (on the object or method under test.)
		SurfaceUtils.insertTile(surface, t1, p1);
		SurfaceUtils.printSurface(surface);

		// Assert (that the expected results have occurred.))
		assertEquals(surface[0][1], t1.getIdent());
		assertEquals(surface[1][1], t1.getIdent());
		assertEquals(surface[2][1], t1.getIdent());
	}

}