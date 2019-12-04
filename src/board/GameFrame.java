package board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 8176877174361042425L;
	private Board board;

	public GameFrame() {
		setUndecorated(true);
		setLayout(null);
		setSize(300, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("CIS 200 Tetris App");
		addKeyListener(this);
		this.board = new Board();
		this.add(board);
		ScorePanel scorePanel = new ScorePanel();
		this.add(scorePanel);
	}

	public Board getBoard() {
		return this.board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 37 is the left arrow that moves the piece left
		if (e.getKeyCode() == 37) {
			// Get the points for the piece and find the furthest left spot to check movable
			Point[] piecePoints = board.getPiecePoints();
			int furthestLeft = board.getCols() + 1;
			for (Point p : piecePoints) {
				if (p != null) {
					furthestLeft = p.getRow() < furthestLeft ? p.getRow() : furthestLeft;
				}
			}
			if (furthestLeft > 0) {
				board.setHorzShift(board.getHorzShift() - 1);
			}
		}

		// 39 is the left arrow that moves the piece right
		if (e.getKeyCode() == 39) {
			// Get the points for the piece and find the furthest right spot to check
			// movable
			Point[] piecePoints = board.getPiecePoints();
			int furthestRight = -1;
			for (Point p : piecePoints) {
				if (p != null) {
					furthestRight = p.getRow() < furthestRight ? p.getRow() : furthestRight;
				}
			}
			if (furthestRight < board.getCols()) {
				board.setHorzShift(board.getHorzShift() + 1);
			}
		}

		// 40 is the down arrow that moves the piece down.
		if (e.getKeyCode() == 40) {
			if (board.getVertShift() < board.getRows()) {
				board.setVertShift(board.getVertShift() + 1);
			}
		}

		// 38 is the up arrow that rotates the piece.
		if (e.getKeyCode() == 38) {
			board.setNeedsTurn(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
