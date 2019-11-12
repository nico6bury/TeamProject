package pieces;

import java.awt.Color;

/**
 * SquarePiece is a representation of the tetris piece referred to as the "Square" piece
 * 
 * @author Stephanie Krass
 *
 */
public class SquarePiece extends GenericPiece{
	/**
	 * Creates a SquarePiece object
	 */
	Color myColor = new Color(242, 245, 66);//rgb yellow
	public SquarePiece() {
		addShape(new int[][] { { 1, 1 }, { 1, 1} });
		this.setCurrentShape(0);
		this.setColor(myColor);
	}
}