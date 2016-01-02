package Data_Component;

import static org.junit.Assert.*;

import org.junit.Test;

import ess.data.Tile;
import ess.data.TileComparator;

public class Tile_Comparator_Test {
    
    private static final int FIRST_SMALLER_THAN_SECOND = -1;
    private static final int FIRST_EQUALS_SECOND = 0;
    private static final int FIRST_LARGER_THAN_SECOND = 1;
    
    private static final int INVERSE = -1;

    @Test
    public void testDifferentRowSameColTiles() {
        Tile t1 = new Tile("_1", 1, 1);
        Tile t2 = new Tile("_2", 2, 1);
        
        testIntegrity(t1, t2);
        assertEquals(TileComparator.ROWS_ASC.compare(t1, t2), FIRST_SMALLER_THAN_SECOND);
        assertEquals(TileComparator.COLS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
        assertEquals(TileComparator.FIELDS_ASC.compare(t1, t2), FIRST_SMALLER_THAN_SECOND);
    }
    
    @Test
    public void testDifferentColSameRowTiles() {
        Tile t1 = new Tile("_1", 1, 1);
        Tile t2 = new Tile("_2", 1, 2);
        
        testIntegrity(t1, t2);
        assertEquals(TileComparator.ROWS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
        assertEquals(TileComparator.COLS_ASC.compare(t1, t2), FIRST_SMALLER_THAN_SECOND);
        assertEquals(TileComparator.FIELDS_ASC.compare(t1, t2), FIRST_SMALLER_THAN_SECOND);
    }
    
    @Test
    public void testSameRowAndColTiles() {
        Tile t1 = new Tile("_1", 1, 1);
        Tile t2 = new Tile("_2", 1, 1);
        
        testIntegrity(t1, t2);
        assertEquals(TileComparator.ROWS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
        assertEquals(TileComparator.COLS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
        assertEquals(TileComparator.FIELDS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
    }
    
    @Test
    public void testTileAndNull() {
        Tile t1 = new Tile("_1", 1, 1);
        Tile t2 = null;
        
        testIntegrity(t1, t2);
        assertEquals(TileComparator.ROWS_ASC.compare(t1, t2), FIRST_LARGER_THAN_SECOND);
        assertEquals(TileComparator.COLS_ASC.compare(t1, t2), FIRST_LARGER_THAN_SECOND);
        assertEquals(TileComparator.FIELDS_ASC.compare(t1, t2), FIRST_LARGER_THAN_SECOND);
    }
    
    @Test
    public void testNullAndNull() {
        Tile t1 = null;
        Tile t2 = null;
        
        testIntegrity(t1, t2);
        assertEquals(TileComparator.ROWS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
        assertEquals(TileComparator.COLS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
        assertEquals(TileComparator.FIELDS_ASC.compare(t1, t2), FIRST_EQUALS_SECOND);
    }
    
    private void testIntegrity(Tile t1, Tile t2) {
        int rowsAsc = TileComparator.ROWS_ASC.compare(t1, t2);
        int colsAsc = TileComparator.COLS_ASC.compare(t1, t2);
        int fieldsAsc = TileComparator.FIELDS_ASC.compare(t1, t2);
        int rowsDesc = TileComparator.ROWS_DESC.compare(t1, t2);
        int colsDesc = TileComparator.COLS_DESC.compare(t1, t2);
        int fieldsDesc = TileComparator.FIELDS_DESC.compare(t1, t2);
        
        int rowsAscSwapped = TileComparator.ROWS_ASC.compare(t2, t1);
        int colsAscSwapped = TileComparator.COLS_ASC.compare(t2, t1);
        int fieldsAscSwapped = TileComparator.FIELDS_ASC.compare(t2, t1);
        int rowsDescSwapped = TileComparator.ROWS_DESC.compare(t2, t1);
        int colsDescSwapped = TileComparator.COLS_DESC.compare(t2, t1);
        int fieldsDescSwapped = TileComparator.FIELDS_DESC.compare(t2, t1);
        
        assertEquals(rowsAsc, INVERSE * rowsDesc);
        assertEquals(colsAsc, INVERSE * colsDesc);
        assertEquals(fieldsAsc, INVERSE * fieldsDesc);
        
        assertEquals(rowsAsc, INVERSE * rowsAscSwapped);
        assertEquals(colsAsc, INVERSE * colsAscSwapped);
        assertEquals(fieldsAsc, INVERSE * fieldsAscSwapped);
        assertEquals(rowsDesc, INVERSE * rowsDescSwapped);
        assertEquals(colsDesc, INVERSE * colsDescSwapped);
        assertEquals(fieldsDesc, INVERSE * fieldsDescSwapped);
    }
    
}
