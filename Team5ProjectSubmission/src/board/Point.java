package board;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import pieces.GenericPiece;

/**
 * Point is a class that represents a point in the game that can be used to
 * display pieces that are already placed, being used by the piece in play, or
 * can be used to display other shapes.
 * 
 * @author Brian, Nicholas
 *
 */
public class Point extends JPanel {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -1690345195968992411L;
	private int row;
	private int col;
	private boolean inUse;
	private boolean inPlay;

	/**
	 * Creates a new point object.
	 * 
	 * @param row The row that the point is on.
	 * @param col The column that the point is on.
	 */
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

	/**
	 * Get the column of the point on the board.
	 * 
	 * @return The column of the point.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Gets the color of the point.
	 * 
	 * @return The color of the point.
	 */
	public Color getColor() {
		return this.getBackground();
	}

	/**
	 * Get the row of the point on the board.
	 * 
	 * @return The row of the point.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets whether or not the piece is in play.
	 * 
	 * @param inPlay true if the piece is in play.
	 */
	public boolean isInPlay() {
		return this.inPlay;
	}

	/**
	 * Gets whether or not the point is currently in use.
	 * 
	 * @return true if the point is in use.
	 */
	public boolean isInUse() {
		return this.inUse;
	}

	/**
	 * Sets the color of the point, does not set in use or in play.
	 * 
	 * @param c The color to set the point to.
	 */
	private void setColor(Color c) {
		this.setBackground(c);
	}

	/**
	 * Sets whether or not the piece is in play.
	 * 
	 * @param inPlay true if the piece is in play.
	 */
	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	/**
	 * Sets the point as in use, but not by a specific piece. Therefore does not
	 * adjust in play at all.
	 * 
	 * @param c The color to that the point in use will be.
	 */
	public void setInUse(Color c) {
		this.setColor(c);
		this.inUse = true;
	}

	/**
	 * Sets the point as in use by a specific piece. Sets in use to the color of the
	 * piece and in play to true.
	 * 
	 * @param p The piece that the point is being used by.
	 */
	public void setInUse(GenericPiece p) {
		this.setInUse(p.getColor());
		this.inPlay = true;
	}

	/**
	 * Set the piece to not being used. Includes setting it to not being in play by
	 * the current piece.
	 */
	public void setNotUsing() {
		this.setColor(Color.DARK_GRAY);
		this.inUse = false;
		this.inPlay = false;
	}

	/**
	 * toString() This special method returns out the contents of the point object
	 * as a string. It does so in the format (x,y)
	 * 
	 * @return The method returns the contents of the current point object as a
	 *         String in the format (x,y)
	 */
	@Override
	public String toString() {
		return ("(" + row + "," + col + ") is the color: " + getColor());
	}
}
