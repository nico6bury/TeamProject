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
	private ArrayList<int[][]> possibleShapes = new ArrayList<>();
	private int currentShapeIndex = 0;

	/**
	 * getColor returns the color of the piece.
	 * 
	 * @return The color of the piece.
	 */
	public Color getColor() {
		return color;
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
	 * getShape returns the 2D array representing the shape of the piece.
	 * 
	 * @return int[][] representing the shape.
	 */
	public int[][] getShape() {
		return possibleShapes.get(currentShapeIndex);
	}

	public void addShape(int[][] shape) {
		this.possibleShapes.add(shape);
	}

	public ArrayList<int[][]> getShapeOptions() {
		return this.possibleShapes;
	}

	public int getCurrentShapeIndex() {
		return this.currentShapeIndex;
	}

	public void setCurrentShape(int currentShape) {
		this.currentShapeIndex = currentShape;
	}
}
