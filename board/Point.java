//package board;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

//import pieces.GenericPiece;

public class Point extends JPanel {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -1690345195968992411L;
	private int x;
	private int y;
	private boolean inUse;

	public Point(int x, int y) {
		// Create looks
		inUse = false;
		this.x = x;
		this.y = y;
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
		return ("(" + x + "," + y + ") is the color: " + getColor());
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
		return ((x == p.getX()) && (y == p.getY()));
	}

	public int getXCoordinate() {
		return x;
	}

	public int getYCoordinate() {
		return y;
	}

	public void setColor(Color c) {
		this.setBackground(c);
	}

	public Color getColor() {
		return this.getBackground();
	}

	public void setInUse(GenericPiece p) {
		this.setColor(p.getColor());
		this.inUse = true;
		
	}

	public void setNotUsing() {
		this.setColor(Color.DARK_GRAY);
		this.inUse = false;
	}

	public boolean getInUse() {
		return this.inUse;
	}
}
