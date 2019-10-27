package pieces;

import java.awt.Color;
import java.util.Random;

public class LPiece extends GenericPiece {
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
