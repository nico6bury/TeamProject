package pieces;

import java.awt.Color;

/**
 * GenericPiece is the class that all piece objects are built off of and holds
 * universally used methods and fields needed by all piece objects.
 * 
 * @author Brian
 *
 */
public class GenericPiece {
	/**
	 * A 2 dimensional integer array of the shape of the piece where a 1 means that
	 * there is a block and a 0 means there is not.
	 */
	private int[][] shape;
	/**
	 * The color of the piece.
	 */
	private Color color;

	/**
	 * getShape returns the 2D array representing the shape of the piece.
	 * 
	 * @return int[][] representing the shape.
	 */
	public int[][] getShape() {
		return shape;
	}

	/**
	 * setShape sets the shape of the object.
	 * 
	 * @param shape int[][] representing the shape.
	 */
	public void setShape(int[][] shape) {
		this.shape = shape;
	}

	/**
	 * getColor returns the color of the piece.
	 * @return The color of the piece.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * setColor sets the color of the piece.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
