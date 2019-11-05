package pieces;

import java.awt.Color;
import java.util.Random;

/**
 * StickPiece is a representation of the tetris piece referred to as the StickPiece
 * 
 * @author Stephanie Krass
 *
 */
public class StickPiece extends GenericPiece{
	/**
	 * Creates a StickPiece object
	 */
	public StickPiece() {
			this.setColor(Color.CYAN);
			int[][] shape = {{1} ,{1}, {1}, {1}};
			this.setShape(shape);
	}
}