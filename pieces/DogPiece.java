package pieces;

import java.awt.Color;
import java.util.Random;

/**
 * DogPiece is a representation of the tetris piece commonly referred to as a
 * "dog piece"
 * 
 * @author Brian
 * edits to original: Stephanie 
 */
public class DogPiece extends GenericPiece {
	/**
	 * Creates a DogPiece object
	 */
	public DogPiece() {
		Random r = new Random();
		int f = r.nextInt();

		// The DogPiece can be flipped
		if (f % 2 == 0) {
			int[][] shape = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
			this.setShape(shape);
			this.setColor(Color.GREEN);
		} else {
			int[][] shape = { { 1, 0 }, { 1, 1 }, { 0, 1 } };
			this.setShape(shape);
			this.setColor(Color.RED);
		}
	}
}
