package pieces;

import java.awt.Color;

/**
 * StickPiece is a representation of the tetris piece referred to as the
 * StickPiece
 * 
 * @author Stephanie Krass
 *
 */
public class StickPiece extends GenericPiece {
	/**
	 * Creates a StickPiece object
	 */
	public StickPiece() {
		setColor(Color.CYAN);
		addShape(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
		addShape(new int[][] { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 } });
		addShape(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } });
		addShape(new int[][] { { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 } });
		setCurrentShape(0);
	}
}