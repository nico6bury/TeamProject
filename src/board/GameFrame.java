package board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import data.Score;

public class GameFrame extends JFrame implements KeyListener {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 8176877174361042425L;
	private static Board board;
	private static Score score;
	private static ScorePanel scorePanel;
	private static boolean fastDrop = false;

	public GameFrame() {
		setUndecorated(true);
		setLayout(null);
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("CIS 200 Tetris App");
		addKeyListener(this);
		board = new Board();
		score = new Score();
		this.add(board);
		scorePanel = new ScorePanel();
		this.add(scorePanel);
	}

	public Board getBoard() {
		return board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 40 is the down arrow that drops the piece
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 40 is the down arrow that moves the piece down.
		if (e.getKeyCode() == 40) {
			fastDrop = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static boolean getFastDrop() {
		return fastDrop;
	}

	public static Score getScore() {
		return score;
	}

	public static void setScore(Score score) {
		GameFrame.score = score;
	}
}
