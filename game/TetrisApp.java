package game;

import java.util.Random;

import board.Board;
import pieces.DogPiece;
import pieces.LPiece;

public class TetrisApp {
	public static void main(String[] args) {
		// Create and display the board
		Board board = new Board();
		board.setVisible(true);
		
		Random random = new Random();
		while(true) {
			int next = random.nextInt(5);
			switch(next) {
			case 0:
				board.placePiece(new LPiece());
				break;
			case 1:
				board.placePiece(new DogPiece());
				break;
			case 2:
				board.placePiece(new TPiece());
				break;
			case 3:
				board.placePiece(new SquarePiece());
				break;
			case 4:
				board.placePiece(new StickPiece());
				break;
			default:
				System.out.println("ERROR, Invalid piece option: " + next);
			}
		}
	}
}
