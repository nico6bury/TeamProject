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
	private int cols = 10;
	private int rows = 20;
	private int boardWidth = 300;
	private int boardHeight = 600;
	private int horzShift = 0;
	private int vertShift = 0;
	private boolean needsTurn = false;
	private Point[][] points;

	/**
	 * Creates the board for the game.
	 */
	public Board() {
		setSize(boardWidth, boardHeight);

		// Create Grid
		this.setSize(boardWidth, boardHeight);
		GridLayout layout = new GridLayout(rows, cols);
		this.setLayout(layout);

		// Generate Points
		points = new Point[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < cols; k++) {
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
		Point[] pointLocations = new Point[10];
		int ct = 0;
		boolean playingPiece = true;
		long dropTimer = System.currentTimeMillis();
		int TIME_GIVEN = 500;
		horzShift = ((cols - p.getShape()[0].length) / 2) + 1;
		int tempHorzShift = horzShift;
		vertShift = 0;
		int tempVertShift = vertShift;
		int onRow = 0;

		// Place the piece initially, all pieces start out horizontal, so 2 height
		while (onRow < 2) {
			for (int j = 0; j < p.getShape()[onRow].length; j++) {
				if (p.getShape()[onRow][j] == 1) {
					points[0][j + horzShift].setColor(p.getColor());
					pointLocations[ct] = points[0][j + horzShift];
					ct++;
				}
			}
			onRow++;
			if (onRow < 2) {
				playingPiece = shiftDown(pointLocations, p);
			}
		}

		while (playingPiece) {
			if (tempHorzShift != horzShift) {
				shiftSide(pointLocations, p, horzShift - tempHorzShift);
				tempHorzShift = horzShift;
			}
			if (needsTurn) {
				pointLocations = rotate(pointLocations, p, onRow);
				needsTurn = false;
				tempHorzShift = horzShift;
			}
			if (dropTimer + TIME_GIVEN <= System.currentTimeMillis() || tempVertShift != vertShift) {
				// Shift already placed parts
				playingPiece = shiftDown(pointLocations, p);
				onRow++;
				dropTimer = System.currentTimeMillis();
				tempVertShift = vertShift;
			}
		}
		// Check each row for whether or not it can be cleared, and clear it.
	}

	/**
	 * Shifts the piece down 1 section.
	 * 
	 * @param pointLocations A Point[] of each point in the piece.
	 * @param p              The piece that is currently in play.
	 * @return continue - whether or not the piece will continue to be in play.
	 */
	private boolean shiftDown(Point[] pointLocations, GenericPiece p) {
		int index = 0;
		boolean cont = true;
		for (Point point : pointLocations) {
			if (point != null) {
				try {
					int row = pointLocations[index].getXCoordinate() + 1;
					int col = pointLocations[index].getYCoordinate();
					pointLocations[index] = points[row][col];
					point.setNotUsing();
					index++;
					if (row >= this.rows - 1 || points[row + 1][col].getInUse()) {
						cont = false;
					}
				} catch (ArrayIndexOutOfBoundsException e) {

				}
			}
		}
		for (Point point : pointLocations) {
			if (point != null) {
				point.setInUse(p);
			}
		}
		return cont;
	}

	/**
	 * Shifts the piece that is currently in play to the left or the right.
	 * 
	 * @param pointLocations A Point[] of each point in the piece.
	 * @param p              The piece that is currently in play.
	 * @param amt            The amount to shift the piece.
	 */
	private void shiftSide(Point[] pointLocations, GenericPiece p, int amt) {
		int index = 0;
		boolean doIt = true;
		for (Point point : pointLocations) {
			if (point != null) {
				if (pointLocations[index].getYCoordinate() + amt >= this.cols) {
					doIt = false;
				}
				if (pointLocations[index].getYCoordinate() + amt < 0) {
					doIt = false;
				}
				index++;
			}
		}
		if (doIt) {
			index = 0;
			for (Point point : pointLocations) {
				if (point != null) {
					int row = pointLocations[index].getXCoordinate();
					int col = pointLocations[index].getYCoordinate() + amt;
					pointLocations[index] = points[row][col];
					point.setNotUsing();
					index++;
				}
			}
			for (Point point : pointLocations) {
				if (point != null) {
					point.setInUse(p);
				}
			}
		}
	}

	/**
	 * Rotates the piece to the next rotation option.
	 * 
	 * @param pointLocations The locations of the current points of the piece.
	 * @param p              The piece to rotate.
	 * @param currentRow     The current row that the piece is on.
	 * @return A Point[] of the points that the piece is using.
	 */
	private Point[] rotate(Point[] pointLocations, GenericPiece p, int currentRow) {
		int ind = 0;
		Point[] newPoints = new Point[10];
		if (p instanceof JPiece) {
			ind = (p.getCurrentShapeIndex() == 3 ? 0 : p.getCurrentShapeIndex() + 1);
		} else if (p instanceof LPiece) {
			ind = (p.getCurrentShapeIndex() == 3 ? 0 : p.getCurrentShapeIndex() + 1);
		} else if (p instanceof SPiece) {
			ind = (p.getCurrentShapeIndex() == 1 ? 0 : 1);
		} else if (p instanceof SquarePiece) {
			return pointLocations;
		} else if (p instanceof StickPiece) {
			ind = (p.getCurrentShapeIndex() == 1 ? 0 : 1);
		} else if (p instanceof TPiece) {
			ind = (p.getCurrentShapeIndex() == 3 ? 0 : p.getCurrentShapeIndex() + 1);
		} else if (p instanceof ZPiece) {
			ind = (p.getCurrentShapeIndex() == 1 ? 0 : 1);
		} else {
			System.out.println("Tried to rotated invalid piece");
			System.exit(0);
		}
		int[][] newShape = p.getShapeOptions().get(ind);
		int ct = 0;
		for (int i = 0; i < newShape.length; i++) {
			for (int j = 0; j < newShape[i].length; j++) {
				if (newShape[i][j] == 1 && currentRow + newShape.length > this.rows + 1) {
					return pointLocations;
				}
			}
		}

		p.setCurrentShape(ind);
		for (Point rp : pointLocations) {
			if (rp != null) {
				rp.setNotUsing();
			}
		}
		for (int i = 0; i < newShape.length; i++) {
			for (int j = 0; j < newShape[i].length; j++) {
				if (newShape[i][j] == 1) {
					while (j + horzShift >= this.cols) {
						shiftSide(newPoints, p, -1);
						horzShift -= 1;
					}
					while (currentRow - newShape.length < 0) {
						shiftDown(newPoints, p);
						currentRow++;
					}
					points[currentRow - newShape.length][j + horzShift].setColor(p.getColor());
					newPoints[ct] = points[currentRow - newShape.length][j + horzShift];
					ct++;
				}
			}
			shiftDown(newPoints, p);
		}
		needsTurn = false;
		return newPoints;
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
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
