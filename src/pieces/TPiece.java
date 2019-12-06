package pieces;

import java.awt.Color;

/**
 * TPiece is a representation of the tetris piece referred to as the "TPiece"
 * 
 * @author Stephanie Krass
 *
 */
public class TPiece extends GenericPiece {
	/**
	 * Creates a TPiece object
	 */
	public TPiece() {
		addShape(new int[][] { { 0, 1, 0 }, { 1, 1, 1 }, { 0, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 1, 1, 0 }, { 0, 1, 0 } });
		addShape(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 0, 1, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 0, 1, 1 }, { 0, 1, 0 } });
		setCurrentShape(0);
		this.setColor(new Color(155, 66, 245));
	}
}