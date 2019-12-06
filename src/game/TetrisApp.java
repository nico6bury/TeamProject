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
		gf = new GameFrame();
		gf.setVisible(true);
		board = gf.getBoard();
		score = gf.getScore();
		nextPieces = new ArrayList<>();
		populateNextPieces();
		holdPiece = null;
		while (playingGame) {
			board.placePiece(nextPieces.get(0));
			nextPieces.remove(nextPieces.get(0));
			populateNextPieces();
		}
	}

	private static void populateNextPieces() {
		Random random = new Random();
		while (nextPieces.size() < 3) {
			int next = random.nextInt(7);
			switch (next) {
			case 0:
				nextPieces.add(new JPiece());
				break;
			case 1:
				nextPieces.add(new LPiece());
				break;
			case 2:
				nextPieces.add(new SPiece());
				break;
			case 3:
				nextPieces.add(new SquarePiece());
				break;
			case 4:
				nextPieces.add(new StickPiece());
				break;
			case 5:
				nextPieces.add(new TPiece());
				break;
			case 6:
				nextPieces.add(new ZPiece());
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
