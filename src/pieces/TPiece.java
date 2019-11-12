package pieces;

import java.awt.Color;

/**
 * TPiece is a representation of the tetris piece referred to as the "T piece"
 * 
 * @author Stephanie Krass
 *
 */
public class TPiece extends GenericPiece {
	/**
	 * Creates a TPiece object
	 */
	public TPiece() {
		Color myColor = new Color(155, 66, 245);
		addShape(new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 1, 1, 0 }, { 0, 1, 0 } });
		addShape(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 1, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 0 } });
		this.setColor(myColor);
	}
}