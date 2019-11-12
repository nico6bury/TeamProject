package pieces;

import java.awt.Color;

/**
 * SPiece is a representation of the tetris piece commonly known as the "S
 * piece".
 * 
 * @author Brian
 */
public class SPiece extends GenericPiece {
	/**
	 * Creates an SPiece object
	 */
	public SPiece() {
		addShape(new int[][] { { 1, 1, 0 }, { 0, 1, 1 }, { 0, 0, 0 } });
		addShape(new int[][] { { 0, 0, 1 }, { 0, 1, 1 }, { 0, 1, 0 } });
		this.setCurrentShape(0);
		this.setColor(Color.GREEN);
	}
}
