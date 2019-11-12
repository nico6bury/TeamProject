package board;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import pieces.GenericPiece;

/**
 * Board contains methods relating to piece movement, gameplay, and also manages
 * all UI elements of the game itself.
 * 
 * @author Brian
 *
 */
public class Board extends JFrame implements KeyListener {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 1844384893749121953L;
	// Tetris board size is 10 X 20
	private int cols = 10;
	private int rows = 20;
	private int boardWidth = 400;
	private int boardHeight = 800;
	private int horzShift = 0;
	private int vertShift = 0;
	private boolean needsTurn = false;
	private Point[][] points;

	/**
	 * Creates the board for the game.
	 */
	public Board() {
		setSize(300, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("CIS 200 Tetris App");
		addKeyListener(this);

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
				needsTurn = false;
			}
			if (dropTimer + TIME_GIVEN <= System.currentTimeMillis() || tempVertShift != vertShift) {
				// Shift already placed parts
				playingPiece = shiftDown(pointLocations, p);
				onRow++;
				dropTimer = System.currentTimeMillis();
				tempVertShift = vertShift;
			}
		}
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
				if(pointLocations[index].getYCoordinate() + amt  < 0) {
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

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			if (this.horzShift > 0) {
				horzShift -= 1;
			}
		}
		if (e.getKeyCode() == 39) {
			if (this.horzShift < cols) {
				horzShift += 1;
			}
		}
		if (e.getKeyCode() == 40) {
			if (this.vertShift < rows) {
				vertShift += 1;
			}
		}
		if (e.getKeyCode() == 38) {
			if (this.horzShift > 0) {
				needsTurn = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
