package Rules_Component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Tile;
import ess.rules.implicit.TileExceedsSurfaceRule;

public class TileExceedsSurfaceTest {

	@Test
	public void tileHasValidPosition() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 1, 2);
		Position pos = new Position(0, 0);

		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		c.getSurface().insertEntry(tile, pos);

		// Assert (that the expected results have occurred.))
		assertTrue("Tile has a valid position", result);
	}

	@Test
	public void tileHasValidPosition2() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile1 = new Tile("_1", 2, 2);
		Position pos1 = new Position(0, 0);
		c.getSurface().insertEntry(tile1, pos1);

		Tile tile2 = new Tile("_2", 1, 2);
		Position pos2 = new Position(2, 0);

		// Act (on the object or method under test.)

		boolean result = new TileExceedsSurfaceRule(c).check(tile2, pos2);
		c.getSurface().insertEntry(tile2, pos2);

		// Assert (that the expected results have occurred.))
		assertTrue("Tile has a valid position", result);
	}

	@Test
	public void tileExceedsRightEdge() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 1, 2);
		Position pos = new Position(1, 1);
		
		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds right edge", result);
	}

	@Test
	public void tileExceedsLeftEdge() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(1, -1);

		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds left edge", result);
	}

	@Test
	public void tileExceedsTopEdge() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(-1, 0);

		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds top edge", result);
	}

	@Test
	public void tileExceedsBottomEdge() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(2, 0);
		
		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds bottom edge", result);
	}

	@Test
	public void tileExceedsTopLeftCorner() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(-1, -1);

		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds top left corner", result);
	}

	@Test
	public void tileExceedsTopRightCorner() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(-1, 1);
		
		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds top right corner", result);
	}

	@Test
	public void tileExceedsBottomLeftCorner() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(2, -1);
		
		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds bottom left corner", result);
	}

	@Test
	public void tileExceedsBottomRightCorner() {
		// Arrange (set all necessary preconditions and inputs.)
		Composite c = new Composite(3, 2, null, null);
		Tile tile = new Tile("_1", 2, 2);
		Position pos = new Position(2, 1);

		// Act (on the object or method under test.)
		boolean result = new TileExceedsSurfaceRule(c).check(tile, pos);
		
		// Assert (that the expected results have occurred.))
		assertFalse("Tile exceeds bottom right corner", result);
	}

}
