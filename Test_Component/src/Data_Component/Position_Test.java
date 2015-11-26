package Data_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Position;

public class Position_Test {
	
	// 	1 = this tile is larger than other tile
	// 	0 : this tile is equal to other tile
	// -1 : this tile is smaller than other tile

	@Test
	public void testComparePositionsWithLargerRowAndCol() {
		Position pos1 = new Position (1,3);
		Position pos2 = new Position (2,6);
		
		assertTrue(pos1.compareTo(pos2) == -1);
		assertTrue(pos2.compareTo(pos1) == 1);
	}
	
	@Test
	public void testComparePositionsWithLargerRowAndSmallerCol() {
		Position pos1 = new Position (-5,3);
		Position pos2 = new Position (2,-1);
		
		assertTrue(pos1.compareTo(pos2) == -1);
		assertTrue(pos2.compareTo(pos1) == 1);
	}
	
	@Test
	public void testComparePositionsWithSameRowAndDifferentCol() {
		Position pos1 = new Position (1,-4);
		Position pos2 = new Position (1,6);
		
		assertTrue(pos1.compareTo(pos2) == -1);
		assertTrue(pos2.compareTo(pos1) == 1);
	}
	
	@Test
	public void testComparePositionsWithSameRowAndCol() {
		Position pos1 = new Position (1,3);
		Position pos2 = new Position (1,3);
		
		assertTrue(pos1.compareTo(pos2) == 0);
		assertTrue(pos2.compareTo(pos1) == 0);
	}
	
	@Test
	public void testComparePositionsWithItself() {
		Position pos = new Position (1,3);
		
		assertTrue(pos.compareTo(pos) == 0);
		assertTrue(pos.compareTo(pos) == 0);
	}
	
	@Test
	public void testComparePositionsWithNull() {
		Position pos1 = new Position (1,3);
		
		assertTrue(pos1.compareTo(null) == 1);
	}


}
