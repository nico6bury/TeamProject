package board;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyEventDispatcher {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 8176877174361042425L;
	private Board board;
	private ScorePanel scorePanel;
	private boolean fastDrop = false;

	/**
	 * Creates a new game frame and it's components.
	 */
	public GameFrame() {
		setUndecorated(true);
		setLayout(null);
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("CIS 200 Tetris App");
		board = new Board();
		this.add(board);
		scorePanel = new ScorePanel();
		this.add(scorePanel);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		//40 is the down arrow that moves the piece faster down
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

			// 90 is the Z that rotates the piece clockwise.
			if (e.getKeyCode() == 90) {
				board.setTurnDirection(1);
			}
			// 88 is the X key that rotates the piece counter-clockwise.
			if(e.getKeyCode() == 88) {
				board.setTurnDirection(-1);
			}

			// 67 is the C key that flips the held piece.
			if (e.getKeyCode() == 67) {
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

	/**
	 * Gets the board of the game.
	 * 
	 * @return The Board object of the game.
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Get whether or not the piece is currently in fast drop mode.
	 * 
	 * @return true if the piece is in fast drop mode.
	 */
	public boolean getFastDrop() {
		return fastDrop;
	}

	/**
	 * Get the score panel of the game.
	 * 
	 * @return the score panel object of the game.
	 */
	public ScorePanel getScorePanel() {
		return scorePanel;
	}
}
