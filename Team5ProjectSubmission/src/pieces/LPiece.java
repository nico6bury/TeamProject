package pieces;

import java.awt.Color;

/**
 * LPiece is a representation of the tetris piece commonly referred to as a "L
 * piece"
 * 
 * @author Brian Edits to original: Stephanie
 */
public class LPiece extends GenericPiece {
	/**
	 * Creates a LPiece object
	 */
	public LPiece() {
		addShape(new int[][] { { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 0 } });
		addShape(new int[][] { { 1, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 } });
		addShape(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 1, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 } });
		this.setCurrentShape(0);
		this.setColor(Color.ORANGE);
	}
}
