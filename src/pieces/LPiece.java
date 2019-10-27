package pieces;

import java.awt.Color;

public class LPiece extends GenericPiece {
	public LPiece() {
		this.setColor(Color.ORANGE);
		int[][] shape = { { 1, 1 }, { 1, 0 }, { 1, 0 } };
		this.setShape(shape);
	}
}
