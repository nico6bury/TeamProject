package pieces;

import java.awt.Color;

public class GenericPiece {
	private int[][] shape;
	private Color color;

	public int[][] getShape() {
		return shape;
	}

	public void setShape(int[][] shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
