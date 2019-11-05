package pieces;

import java.awt.Color;
import java.util.Random;

/**
 * TPiece is a representation of the tetris piece referred to as the "T piece"
 * 
 * @author Stephanie Krass
 *
 */
public class TPiece extends GenericPiece{
	/**
	 * Creates a TPiece object
	 */
	public TPiece() {
		Color myColor = new Color(155, 66, 245);
		Random r = new Random();
		int f = r.nextInt();
		this.setColor(myColor);

		// The TPiece can be flipped 
		if (f % 2 == 0) {
			int[][] shape = { { 1, 0 }, { 1, 1 }, { 1, 0 } };
			this.setShape(shape);
		} else {
			int[][] shape = { { 0, 1 }, { 1, 1 }, { 0, 1 } };
			this.setShape(shape);
		}
	}
}