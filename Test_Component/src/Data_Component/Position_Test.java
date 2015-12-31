package Data_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Position;

public class Position_Test {
	
	// 	1 = this position is larger than other position
	// 	0 : this position is equal to other position
	// -1 : this position is smaller than other position

	@Test
	public void testComparePositionsWithLargerRowAndCol() {
		Position pos1 = new Position (1,3);
		Position pos2 = new Position (2,6);
		
		assertTrue("A position with a smaller row value is smaller, no matter of the column value.", 
		        pos1.compareTo(pos2) == -1);
		assertTrue("A position with a larger row value is larger, no matter of the column value.", 
		        pos2.compareTo(pos1) == 1);
	}
	
	@Test
	public void testComparePositionsWithLargerRowAndSmallerCol() {
		Position pos1 = new Position (-5,3);
		Position pos2 = new Position (2,-1);
		
		assertTrue("A position with a smaller row value is smaller, no matter of the column value.", 
		        pos1.compareTo(pos2) == -1);
		assertTrue("A position with a larger row value is larger, no matter of the column value.", 
		        pos2.compareTo(pos1) == 1);
	}
	
	@Test
	public void testComparePositionsWithSameRowAndDifferentCol() {
		Position pos1 = new Position (1,-4);
		Position pos2 = new Position (1,6);
		
		assertTrue("A position with a smaller column value is smaller than a position "
		        + "in the same row with a larger column value.", pos1.compareTo(pos2) == -1);
		assertTrue("A position with a larger column value is larger than a position "
		        + "in the same row with a smaller column value.", pos2.compareTo(pos1) == 1);
	}
	
	@Test
	public void testComparePositionsWithSameRowAndCol() {
		Position pos1 = new Position (1,3);
		Position pos2 = new Position (1,3);
		
		assertTrue("Positions with same coordinates are equal.", pos1.compareTo(pos2) == 0);
		assertTrue("Positions with same coordinates are equal.", pos2.compareTo(pos1) == 0);
	}
	
	@Test
	public void testComparePositionsWithItself() {
		Position pos = new Position (1,3);
		
		assertTrue("A position is equal to itself.", pos.compareTo(pos) == 0);
	}
	
	@Test
	public void testComparePositionsWithNull() {
		Position pos1 = new Position (1,3);
		
		assertTrue("Any position is larger than null.", pos1.compareTo(null) == 1);
	}


}
