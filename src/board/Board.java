package board;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import pieces.GenericPiece;

public class Board extends JPanel {
	// Tetris board size is 10 X 20
	private int cols = 10;
	private int rows = 20;
	private int boardWidth = 400;
	private int boardHeight = 800;
	private Point[][] points;

	public Board() {
		// Create Grid
		this.setSize(boardWidth, boardHeight);
		GridLayout layout = new GridLayout(rows, cols);
		this.setLayout(layout);

		points = new Point[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < cols; k++) {
				points[i][k] = new Point(i, k);
				this.add(points[i][k]);
			}
		}
	}

	public void placePiece(GenericPiece p) throws InterruptedException {
		Point[] pointLocations = new Point[4];
		int ct = 0;
		boolean cont = true;

		for (int i = 0; i < p.getShape().length; i++) {
			int shift = 0;
			// Shift down already placed blocks.
			int ct2 = 0;
			for (Point pointShift : pointLocations) {
				if (pointShift != null) {
					pointShift.setColor(Color.DARK_GRAY);
					int x = pointShift.getXCoordinate() + 1;
					int y = pointShift.getYCoordinate() + shift;
					points[x][y + shift].setColor(Color.ORANGE);
					pointLocations[ct2] = points[x][y + shift];
					ct2++;
				}
			}
			// Place next row of blocks
			for (int j = 0; j < p.getShape()[i].length; j++) {
				if (p.getShape()[i][j] == 1) {
					points[0][j + shift].setColor(p.getColor());
					pointLocations[ct] = points[0][j + shift];
					ct++;
				}
			}
			shift = 0;
			Thread.sleep(500);
		}

		while (cont) {
			int ct2 = 0;
			for (Point pointShift : pointLocations) {
				if (pointShift != null) {
					try {
						pointShift.setColor(Color.DARK_GRAY);
						int x = pointShift.getXCoordinate() + 1;
						int y = pointShift.getYCoordinate();
						points[x][y].setColor(Color.ORANGE);
						pointLocations[ct2] = points[x][y];
						ct2++;
					} catch (ArrayIndexOutOfBoundsException e) {
						cont = false;
						e.printStackTrace();
					}
				}
			}
			Thread.sleep(500);
		}
	}
}
