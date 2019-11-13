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
	}

	public Board getBoard() {
		return this.board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			if (board.getHorzShift() > 0) {
				board.setHorzShift(board.getHorzShift() - 1);
			}
		}
		if (e.getKeyCode() == 39) {
			if (board.getHorzShift() < board.getCols()) {
				board.setHorzShift(board.getHorzShift() + 1);
			}
		}
		if (e.getKeyCode() == 40) {
			if (board.getVertShift() < board.getRows()) {
				board.setVertShift(board.getVertShift() + 1);
			}
		}
		if (e.getKeyCode() == 38) {
			board.setNeedsTurn(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
