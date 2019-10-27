package pieces;

import java.awt.Color;
import java.util.Random;

/**
 * LPiece is a representation of the tetris piece commonly referred to as a "L
 * piece"
 * 
 * @author Brian
 *
 */
public class LPiece extends GenericPiece {
	/**
	 * Creates a LPiece object
	 */
	public LPiece() {
		Random r = new Random();
		int f = r.nextInt();
		this.setColor(Color.ORANGE);

		// The L can be flipped
		if (f % 2 == 0) {
			int[][] shape = { { 1, 1 }, { 1, 0 }, { 1, 0 } };
			this.setShape(shape);
		} else {
			int[][] shape = { { 1, 1 }, { 0, 1 }, { 0, 1 } };
			this.setShape(shape);
		}
	}
}
