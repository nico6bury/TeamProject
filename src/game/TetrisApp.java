package game;

import java.util.Random;

import board.Board;
import board.GameFrame;
import pieces.JPiece;
import pieces.LPiece;
import pieces.SPiece;
import pieces.SquarePiece;
import pieces.StickPiece;
import pieces.TPiece;
import pieces.ZPiece;

public class TetrisApp {
	public static void main(String[] args) {
		// Create and display the board
		GameFrame gf = new GameFrame();
		gf.setVisible(true);
		Board board = gf.getBoard();

		Random random = new Random();
		while (true) {
			int next = random.nextInt(7);
			switch (next) {
			case 0:
				board.placePiece(new JPiece());
				break;
			case 1:
				board.placePiece(new LPiece());
				break;
			case 2:
				board.placePiece(new SPiece());
				break;
			case 3:
				board.placePiece(new SquarePiece());
				break;
			case 4:
				board.placePiece(new StickPiece());
				break;
			case 5:
				board.placePiece(new TPiece());
				break;
			case 6:
				board.placePiece(new ZPiece());
				break;
			default:
				System.out.println("ERROR, Invalid piece option: " + next);
			}
		}
	}
}
