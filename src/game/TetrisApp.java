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
		while (nextPieces.size() < 3) {
			nextPieces.add(generatePiece());
		}
		gf = new GameFrame();
		gf.setVisible(true);
		board = gf.getBoard();
		score = gf.getScore();

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

	/**
	 * Get the score object of the game.
	 * 
	 * @return The score object.
	 */
	public static Score getScore() {
		return score;
	}

	/**
	 * Gets the GameFrame object of the game.
	 * 
	 * @return The GameFrame object.
	 */
	public static GameFrame getGameFrame() {
		return gf;
	}

	/**
	 * Gets an ArrayList of the pieces that are up next in the queue.
	 * 
	 * @return An ArrayList of GenericPiece objects.
	 */
	public static ArrayList<GenericPiece> getNextPieces() {
		return nextPieces;
	}

	/**
	 * Sets the piece being held in the hold.
	 * 
	 * @param p The piece to set in the hold.
	 */
	public static void setHoldPiece(GenericPiece p) {
		holdPiece = p;
	}

	/**
	 * Gets the piece that is currently being held in the hold.
	 * 
	 * @return A GenericPiece object of the piece being held.
	 */
	public static GenericPiece getHoldPiece() {
		return holdPiece;
	}
}
