package ess.rules;

import ess.data.Composite;
import ess.data.Position;
import ess.data.Surface;
import ess.data.SurfaceEntry;
import ess.rules.helpers.Corner;

public class NoCrossingsRule extends AbstractTileRule implements IRule {

	@Override
	public boolean check(Composite c, SurfaceEntry e) {
		Surface surface = c.getSurface();
		return checkCorner(Corner.TOP_LEFT, surface, e) 
				&& checkCorner(Corner.TOP_RIGHT, surface, e) 
				&& checkCorner(Corner.BOTTOM_LEFT, surface, e)
				&& checkCorner(Corner.BOTTOM_RIGHT, surface, e);
	}
	
	private boolean checkCorner(Corner c, Surface surface, SurfaceEntry e) {
		Position cornerPos = getCornerPosition(c, e);
		if (isEdgePosition(surface, cornerPos)) {
			return true;
		}
		SurfaceEntry corner = surface.getFields()[cornerPos.getRow()+c.getNeighbourRowOffset()][cornerPos.getColumn()+ c.getNeighbourColOffset()];
		SurfaceEntry sameRow = surface.getFields()[cornerPos.getRow()][cornerPos.getColumn()+c.getNeighbourColOffset()];
		SurfaceEntry sameColumn = surface.getFields()[cornerPos.getRow()+c.getNeighbourRowOffset()][cornerPos.getColumn()];
		
		if (corner == null) {
			return sameRow == null || sameColumn == null;
		}
		return corner.equals(sameRow) || corner.equals(sameColumn);
	}


}
