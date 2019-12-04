package board;

import java.awt.GridLayout;

import javax.swing.JPanel;

import pieces.GenericPiece;
import pieces.JPiece;
import pieces.LPiece;
import pieces.SPiece;
import pieces.SquarePiece;
import pieces.StickPiece;
import pieces.TPiece;
import pieces.ZPiece;

/**
 * Board contains methods relating to piece movement, gameplay, and also manages
 * all UI elements of the game itself.
 * 
 * @author Brian
 *
 */
public class Board extends JPanel {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 1844384893749121953L;
	// Tetris board size is 10 X 20
	private static final int COLS = 10;
	private static final int ROWS = 20;
	private static final int BOARD_WIDTH = 300;
	private static final int BOARD_HEIGHT = 600;
	private int horzShift = 0;
	private int vertShift = 0;
	private boolean needsTurn = false;
	private Point[][] points;
	private Point[] pieceLocations;

	/**
	 * Creates the board for the game.
	 */
	public Board() {
		setSize(BOARD_WIDTH, BOARD_HEIGHT);

		// Create Grid
		this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
		GridLayout layout = new GridLayout(ROWS, COLS);
		this.setLayout(layout);

		// Generate Points on the board
		points = new Point[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int k = 0; k < COLS; k++) {
				points[i][k] = new Point(i, k);
				this.add(points[i][k]);
			}
		}
	}

	/**
	 * Places a piece and continues to loop allowing the user to move it around
	 * until the piece finally hits either the bottom of the screen or connects to
	 * another already placed piece.
	 * 
	 * @param p The piece to place.
	 */
	public void placePiece(GenericPiece p) {
		pieceLocations = new Point[10];
		int placeCounter = 0;
		boolean playingPiece = true;
		long dropTimer = System.currentTimeMillis();
		int TIME_GIVEN = 500;
		horzShift = ((COLS - p.getShape()[0].length) / 2) + 1;
		vertShift = 0;
		int tempHorzShift = horzShift;
		int tempVertShift = vertShift;
		int onRow = 0;

		// Place the piece initially, all pieces start out horizontal, so 2 height of
		while (onRow < 2) {
			// Place all parts of the piece that are on the current row
			for (int j = 0; j < p.getShape()[onRow].length; j++) {
				if (p.getShape()[onRow][j] == 1) {
					points[0][j + horzShift].setColor(p.getColor());
					pieceLocations[placeCounter] = points[0][j + horzShift];
					placeCounter++;
				}
			}
			// Shift down a row if not on row 2
			onRow++;
			if (onRow < 2) {
				playingPiece = shiftDown(p);
			}
		}

		// Piece game loop
		while (playingPiece) {
			// Check if the piece needs to shift to the side.
			if (tempHorzShift != horzShift) {
				shiftSide(p, horzShift - tempHorzShift);
				tempHorzShift = horzShift;
			}
			// Check if the piece needs to rotate.
			if (needsTurn) {
				rotate(p, onRow);
				needsTurn = false;
				tempHorzShift = horzShift;
			}
			// Check if the piece needs to move down.
			if (dropTimer + TIME_GIVEN <= System.currentTimeMillis() || tempVertShift != vertShift) {
				// Shift already placed parts
				playingPiece = shiftDown(p);
				onRow++;
				dropTimer = System.currentTimeMillis();
				tempVertShift = vertShift;
			}
		}

		// Set the points to no longer being in play.
		for (Point point : pieceLocations) {
			if (point != null) {
				point.setInPlay(false);
			}
		}

		// Check for rows that need to be cleared out.
		int rowCheck = ROWS - 1;
		while (rowCheck > 0) {
			if (isRowFull(rowCheck)) {
				clearRow(rowCheck);
			} else {
				rowCheck--;
			}
		}
	}

	/**
	 * Shifts the piece down 1 section.
	 * 
	 * @param p The piece that is currently in play.
	 * @return continue - whether or not the piece will continue to be in play.
	 */
	private boolean shiftDown(GenericPiece p) {
		boolean cont = canShiftDown(p);
		if (cont) {
			for (int i = 0; i < pieceLocations.length; i++) {
				if (pieceLocations[i] != null) {
					pieceLocations[i].setNotUsing();
					int row = pieceLocations[i].getRow() + 1;
					int col = pieceLocations[i].getCol();
					pieceLocations[i] = points[row][col];
				}
			}
			for (Point point : pieceLocations) {
				if (point != null) {
					point.setInUse(p);
				}
			}
		}
		return cont;
	}

	/**
	 * Checks if the piece is able to be shifted down based on if it is too far down
	 * or going to collide with a piece below it.
	 * 
	 * @param p The piece in play
	 * @return true if the piece can be shifted down.
	 */
	private boolean canShiftDown(GenericPiece p) {
		for (int i = 0; i < pieceLocations.length; i++) {
			if (pieceLocations[i] != null) {
				try {
					int row = pieceLocations[i].getRow() + 1;
					int col = pieceLocations[i].getCol();
					if ((row >= ROWS) || (points[row][col].isInUse() && !points[row][col].isInPlay())) {
						return false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Shifts the piece that is currently in play to the left or the right.
	 * 
	 * @param p   The piece that is currently in play.
	 * @param amt The amount to shift the piece.
	 */
	private void shiftSide(GenericPiece p, int amt) {
		int index = 0; // Index of looping through the pointLocations
		boolean doIt = true;
		for (Point point : pieceLocations) {
			if (point != null) {
				try {
					if (points[pieceLocations[index].getRow()][pieceLocations[index].getCol() + amt].isInUse()
							&& !points[pieceLocations[index].getRow()][pieceLocations[index].getCol() + amt]
									.isInPlay()) {
						doIt = false;
					}
				} catch (ArrayIndexOutOfBoundsException ne) {
					// ArrayIndexOutOfBoundsException from going out of the board
					doIt = false;
				}
				index++;
			}
		}
		if (doIt) {
			index = 0;
			for (Point point : pieceLocations) {
				if (point != null) {
					int row = pieceLocations[index].getRow();
					int col = pieceLocations[index].getCol() + amt;
					pieceLocations[index] = points[row][col];
					point.setNotUsing();
					index++;
				}
			}
			for (Point point : pieceLocations) {
				if (point != null) {
					point.setInUse(p);
				}
			}
		}
	}

	/**
	 * Rotates the piece to the next rotation state.
	 * 
	 * @param p          The piece to rotate
	 * @param currentRow The current row that the piece is on.
	 * @return
	 */
	private boolean rotate(GenericPiece p, int currentRow) {
		int ind = 0;
		if (p instanceof JPiece) {
			ind = (p.getCurrentShapeIndex() == 3 ? 0 : p.getCurrentShapeIndex() + 1);
		} else if (p instanceof LPiece) {
			ind = (p.getCurrentShapeIndex() == 3 ? 0 : p.getCurrentShapeIndex() + 1);
		} else if (p instanceof SPiece) {
			ind = (p.getCurrentShapeIndex() == 1 ? 0 : 1);
		} else if (p instanceof SquarePiece) {
			return false;
		} else if (p instanceof StickPiece) {
			ind = (p.getCurrentShapeIndex() == 1 ? 0 : 1);
		} else if (p instanceof TPiece) {
			ind = (p.getCurrentShapeIndex() == 3 ? 0 : p.getCurrentShapeIndex() + 1);
		} else if (p instanceof ZPiece) {
			ind = (p.getCurrentShapeIndex() == 1 ? 0 : 1);
		} else {
			System.out.println("Tried to rotate an invalid piece");
			System.exit(0);
		}

		// Sanity checks
		int[][] newShape = p.getShapeOptions().get(ind);
		// Adjust left, right, and down shifts
		for (int i = 0; i < newShape.length; i++) {
			for (int j = 0; j < newShape[i].length; j++) {
				if (newShape[i][j] == 1) {
					while (j + horzShift >= COLS) {
						horzShift--;
					}
					while (j + horzShift < 0) {
						horzShift++;
					}
					while (currentRow - newShape.length < 0) {
						currentRow++;
					}
				}
			}
		}

		for (int i = 0; i < newShape.length; i++) {
			for (int j = 0; j < newShape[i].length; j++) {
				// Check that the shape will fit in the board once rotated
				if (newShape[i][j] == 1 && currentRow + newShape.length > ROWS + 1) {
					return false;
				}
				// Check that the piece will not collide with placed pieces
				if (newShape[i][j] == 1) {
					if (points[currentRow - i][j + horzShift].isInUse()
							&& !points[currentRow - i][j + horzShift].isInPlay()) {
						return false;
					}
				}
			}
		}

		for (Point rp : pieceLocations) {
			if (rp != null) {
				rp.setNotUsing();
			}
		}

		pieceLocations = new Point[10];
		p.setCurrentShape(ind);

		// Place the new piece.
		int ct = 0;
		for (int i = 0; i < newShape.length; i++) {
			for (int j = 0; j < newShape[i].length; j++) {
				if (newShape[i][j] == 1) {
					points[currentRow - i][j + horzShift].setColor(p.getColor());
					pieceLocations[ct] = points[currentRow - i][j + horzShift];
					ct++;
				}
			}
		}
		return true;
	}

	/**
	 * Checks whether or not the row is full and needs to be cleared.
	 * 
	 * @param row The row the check if is full.
	 * @return If the row is full.
	 */
	private boolean isRowFull(int row) {
		for (int j = 0; j < COLS; j++) {
			if (!points[row][j].isInUse()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Clears out a full row and shifts the rows above it down.
	 * 
	 * @param row The row to clear out.
	 */
	private void clearRow(int row) {
		for (int j = 0; j < COLS; j++) {
			points[row][j].setNotUsing();
		}

		int highestRow = -1;
		for (int i = row; i > 0; i--) {
			for (int j = 0; j < COLS; j++) {
				if (points[i][j].isInUse()) {
					highestRow = i;
					break;
				}
			}
		}

		// Shift rows down
		long dropTimer = System.currentTimeMillis();
		int TIME_GIVEN = 200;
		int onRow = row;

		while (onRow > highestRow) {
			if (System.currentTimeMillis() > dropTimer + TIME_GIVEN) {
				for (int j = 0; j < COLS; j++) {
					// Shift points from 1 above to down and then remove the row above
					if (points[onRow - 1][j].isInUse()) {
						points[onRow][j].setInUse(points[onRow - 1][j].getColor());
						points[onRow - 1][j].setNotUsing();
					}
				}
				onRow--;
				dropTimer = System.currentTimeMillis();
			}
		}
	}

	public int getHorzShift() {
		return horzShift;
	}

	public void setHorzShift(int horzShift) {
		this.horzShift = horzShift;
	}

	public int getVertShift() {
		return vertShift;
	}

	public void setVertShift(int vertShift) {
		this.vertShift = vertShift;
	}

	public boolean isNeedsTurn() {
		return needsTurn;
	}

	public void setNeedsTurn(boolean needsTurn) {
		this.needsTurn = needsTurn;
	}

	public int getCols() {
		return COLS;
	}

	public int getRows() {
		return ROWS;
	}

	public Point[] getPiecePoints() {
		return pieceLocations;
	}
}
