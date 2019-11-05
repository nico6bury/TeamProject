package pieces;

import java.awt.Color;
import java.util.Random;

/**
 * LPiece is a representation of the tetris piece commonly referred to as a "L
 * piece"
 * 
 * @author Brian
 * Edits to original: Stephanie
 */
public class LPiece extends GenericPiece {
	/**
	 * Creates a LPiece object
	 */
	public LPiece() {
		Random r = new Random();
		int f = r.nextInt();
		

		// The L can be flipped
		if (f % 2 == 0) {
			int[][] shape = { { 1, 1 }, { 1, 0 }, { 1, 0 } };
			this.setShape(shape);
			this.setColor(Color.BLUE);
		} else {
			int[][] shape = { { 1, 1 }, { 0, 1 }, { 0, 1 } };
			this.setShape(shape);
			this.setColor(Color.ORANGE);
		}
	}
}
