package game;

import javax.swing.JFrame;

import board.Board;
import pieces.DogPiece;

public class TetrisApp {
	public static void main(String[] args) {
		// Create and display the board
		JFrame frame = new JFrame();
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("CIS 200 Tetris App");

		Board board = new Board();
		frame.add(board);
		frame.setVisible(true);

		try {
			board.placePiece(new DogPiece());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
