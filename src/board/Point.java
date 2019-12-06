package board;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pieces.GenericPiece;

public class Point extends JPanel {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -1690345195968992411L;
	private int row;
	private int col;
	private boolean inUse;
	private boolean inPlay;

	public Point(int row, int col) {
		// Create looks
		this.inUse = false;
		this.inPlay = false;
		this.row = row;
		this.col = col;
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	/**
	 * toString() This special method returns out the contents of the point object
	 * as a string. It does so in the format (x,y)
	 * 
	 * @return The method returns the contents of the current point object as a
	 *         String in the format (x,y)
	 */
	public String toString() {
		return ("(" + row + "," + col + ") is the color: " + getColor());
	}

	/**
	 * equals() This special method determines whether or not two point objects are
	 * equal to each other. It checks to see if both the x-coordinates and the
	 * y-coordinate are the same, and only returns true if both are equal
	 * 
	 * @param p This is the second point object which will be compared to the point
	 *          that called the method
	 * @return The method returns true if both points are the same and false if they
	 *         are different in any way
	 */
	public boolean equals(Point p) {
		return ((row == p.getRow()) && (col == p.getCol()));
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	private void setColor(Color c) {
		this.setBackground(c);
	}

	public Color getColor() {
		return this.getBackground();
	}

	public void setInUse(GenericPiece p) {
		this.setInUse(p.getColor());
		this.setInPlay(true);
	}

	public void setInUse(Color c) {
		this.setColor(c);
		this.inUse = true;
	}

	public void setNotUsing() {
		this.setColor(Color.DARK_GRAY);
		this.inUse = false;
		this.inPlay = false;
	}

	public boolean isInUse() {
		return this.inUse;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	public boolean isInPlay() {
		return this.inPlay;
	}
}
