package pieces;

import java.awt.Color;
import java.util.ArrayList;

/**
 * GenericPiece is the class that all piece objects are built off of and holds
 * universally used methods and fields needed by all piece objects.
 * 
 * @author Brian
 *
 */
public abstract class GenericPiece {
	/**
	 * The color of the piece.
	 */
	private Color color;
	/**
	 * An ArrayList of int[][] that represent the possible shapes that a piece can
	 * have.
	 */
	private ArrayList<int[][]> possibleShapes = new ArrayList<>();
	/**
	 * The current index for the rotation of the piece.
	 */
	private int currentShapeIndex = 0;

	/**
	 * Adds a possible shape rotation to the arraylist of possible shape options for
	 * the piece.
	 * 
	 * @param shape An int[][] that represents the shape of a piece.
	 */
	public void addShape(int[][] shape) {
		this.possibleShapes.add(shape);
	}

	/**
	 * Compares the color of two pieces to determine if they are equal as a piece is
	 * only going to be equal to another if they are of the same type.
	 * 
	 * @param p The other piece to check equivilance with.
	 * @return True if the two pieces are equal.
	 */
	public boolean equals(GenericPiece p) {
		return p.getColor().equals(this.getColor());
	}

	/**
	 * getColor returns the color of the piece.
	 * 
	 * @return The color of the piece.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Gets the current index of the shape for the piece.
	 * 
	 * @return An int representing the current shape index.
	 */
	public int getCurrentShapeIndex() {
		return this.currentShapeIndex;
	}

	/**
	 * getShape returns the 2D array representing the shape of the piece.
	 * 
	 * @return int[][] representing the shape.
	 */
	public int[][] getShape() {
		return possibleShapes.get(currentShapeIndex);
	}

	/**
	 * Gets the arraylist of possible shape options for the piece.
	 * 
	 * @return An arraylist of int[][]s that represent the shape of a piece.
	 */
	public ArrayList<int[][]> getShapeOptions() {
		return this.possibleShapes;
	}

	/**
	 * setColor sets the color of the piece.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Set the current shape to a specified current shape index value.
	 * 
	 * @param currentShape An int that corresponds to a shape index option.
	 */
	public void setCurrentShape(int currentShape) {
		this.currentShapeIndex = currentShape;
	}
}
