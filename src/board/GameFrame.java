package board;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import data.Score;

public class GameFrame extends JFrame implements KeyEventDispatcher {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 8176877174361042425L;
	private Board board;
	private Score score;
	private ScorePanel scorePanel;
	private boolean fastDrop = false;

	public GameFrame() {
		setUndecorated(true);
		setLayout(null);
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("CIS 200 Tetris App");
		board = new Board();
		score = new Score();
		this.add(board);
		scorePanel = new ScorePanel();
		this.add(scorePanel);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}

	public Board getBoard() {
		return board;
	}

	public boolean getFastDrop() {
		return fastDrop;
	}

	public Score getScore() {
		return score;
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == 40) {
				if (board.getVertShift() < board.getRows()) {
					fastDrop = true;
				}
			}
			// 37 is the left arrow that moves the piece left
			if (e.getKeyCode() == 37) {
				board.addHorzShift(-1);
			}

			// 39 is the left arrow that moves the piece right
			if (e.getKeyCode() == 39) {
				board.addHorzShift(1);
			}

			// 38 is the up arrow that rotates the piece.
			if (e.getKeyCode() == 38) {
				board.setNeedsTurn(true);
			}

			// 17 is the right ctrl that flips the held piece.
			if (e.getKeyCode() == 17) {
				board.setNeedsFlip(true);
			}
		}
		if (e.getID() == KeyEvent.KEY_RELEASED) {
			// 40 is the down arrow that moves the piece down.
			if (e.getKeyCode() == 40) {
				fastDrop = false;
			}
		}
		return false;
	}
}
