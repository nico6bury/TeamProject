package pieces;

import java.awt.Color;

/**
 * ZPiece is a representation of the tetris piece commonly referred to as a
 * "ZPiece"
 * 
 * @author Brian edits to original: Stephanie
 */
public class ZPiece extends GenericPiece {
	/**
	 * Creates a ZPiece object
	 */
	public ZPiece() {
		addShape(new int[][] { { 0, 1, 1 }, { 1, 1, 0 }, { 0, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 0, 1, 1 }, { 0, 0, 1 } });
		this.setCurrentShape(0);
		this.setColor(Color.RED);
	}
}
