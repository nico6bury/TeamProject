package game;

import java.util.Random;

import javax.swing.JOptionPane;

import board.Board;
import board.GameFrame;
import data.Score;
import pieces.JPiece;
import pieces.LPiece;
import pieces.SPiece;
import pieces.SquarePiece;
import pieces.StickPiece;
import pieces.TPiece;
import pieces.ZPiece;

public class TetrisApp {
	private static boolean playingGame;
	private static GameFrame gf;
	private static Board board;
	private static Score score;

	public static void main(String[] args) {
		startGame();
	}

	private static void startGame() {
		// Create and display the board
		playingGame = true;
		gf = new GameFrame();
		gf.setVisible(true);
		board = gf.getBoard();
		score = gf.getScore();

		Random random = new Random();
		while (playingGame) {
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

	public static void stopGame() {
		try {
			playingGame = false;
			score.updateHighScore();
			String playAgain = JOptionPane.showInputDialog("Play another round? y/n").toLowerCase();
			if (playAgain.contains("y")) {
				startGame();
			} else {
				System.exit(0);
			}
		} catch(NullPointerException ne) {
			JOptionPane.showMessageDialog(null, "Must give a valid answer!");
			stopGame();
		}
	}

	public static Score getScore() {
		return score;
	}

	public static Board getBoard() {
		return board;
	}

	public static GameFrame getGameFrame() {
		return gf;
	}
}
