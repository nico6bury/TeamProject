package pieces;

import java.awt.Color;

/**
 * JPiece is a representation of the tetris piece commonly referred to as a "J
 * piece"
 * 
 * @author Brian Edits to original: Stephanie
 */
public class JPiece extends GenericPiece {
	/**
	 * Creates a JPiece object
	 */
	public JPiece() {
		addShape(new int[][] { { 1, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 1, 1, 0 } });
		addShape(new int[][] { { 0, 0, 0 }, { 1, 1, 1 }, { 1, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 1 } });
		this.setCurrentShape(0);
		this.setColor(Color.BLUE);
	}
}
