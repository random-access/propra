package Algorithm_Component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ess.data.Position;
import ess.data.Surface;
import ess.data.Tile;

public class Surface_Utils_Test {

	@Test
	public void testInsertTileOrder() {
		// Arrange (set all necessary preconditions and inputs.)
		int cols = 10;
		int rows = 6;
		Surface surface = new Surface(rows, cols);

		// Act (on the object or method under test.)
		for (int i = 0; i < cols * rows; i++) {
			Tile t = new Tile("_" + i, 1, 1);
			Position p = new Position(i / cols, i % cols);
			surface.insertEntry(t,p);
		}
		
		// Assert (that the expected results have occurred.))
		int currentId = 0;
		for (int i = 0; i < surface.getRows(); i++) {
			for (int j = 0; j < surface.getCols(); j++) {
				assertEquals("Wrong value in surface. ", surface.getFields()[i][j].getId(), "_" + currentId++);
			}
		}
	}

	@Test
	public void testInsertTileOrientation() {
		// Arrange (set all necessary preconditions and inputs.)
		int width = 5;
		int height = 3;
		Surface s = new Surface(width, height);
		Tile t1 = new Tile("_2", 3, 1);
		Position p1 = new Position(0, 1);

		// Act (on the object or method under test.)
		s.insertEntry(t1, p1);

		// Assert (that the expected results have occurred.))
		assertEquals(s.getFields()[0][1], t1);
		assertEquals(s.getFields()[1][1], t1);
		assertEquals(s.getFields()[2][1], t1);
	}

}
