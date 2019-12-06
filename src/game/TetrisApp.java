package game;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import board.Board;
import board.GameFrame;
import data.Score;
import pieces.GenericPiece;
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
	private static ArrayList<GenericPiece> nextPieces;
	private static GenericPiece holdPiece;

	public static void main(String[] args) {
		startGame();
	}

	private static void startGame() {
		// Create and display the board
		playingGame = true;
		nextPieces = new ArrayList<>();
		holdPiece = generatePiece();
		gf = new GameFrame();
		gf.setVisible(true);
		board = gf.getBoard();
		score = gf.getScore();

		while (nextPieces.size() < 3) {
			nextPieces.add(generatePiece());
		}

		while (playingGame) {
			board.placePiece(nextPieces.get(0));
			nextPieces.remove(nextPieces.get(0));
			nextPieces.add(generatePiece());
		}
	}

	public static GenericPiece generatePiece() {
		Random random = new Random();
		int next = random.nextInt(7);
		switch (next) {
		case 0:
			return new JPiece();
		case 1:
			return new LPiece();
		case 2:
			return new SPiece();
		case 3:
			return new SquarePiece();
		case 4:
			return new StickPiece();
		case 5:
			return new TPiece();
		case 6:
			return new ZPiece();
		default:
			System.out.println("ERROR, Invalid piece option: " + next);
			return null;
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
		} catch (NullPointerException ne) {
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

	public static ArrayList<GenericPiece> getNextPieces() {
		return nextPieces;
	}

	public static void setHoldPiece(GenericPiece p) {
		holdPiece = p;
	}

	public static GenericPiece getHoldPiece() {
		return holdPiece;
	}
}
