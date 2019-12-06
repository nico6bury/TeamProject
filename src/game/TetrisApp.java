package game;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import board.Board;
import board.GameFrame;
import board.ScorePanel;
import data.Score;
import pieces.GenericPiece;
import pieces.JPiece;
import pieces.LPiece;
import pieces.SPiece;
import pieces.SquarePiece;
import pieces.StickPiece;
import pieces.TPiece;
import pieces.ZPiece;

/**
 * TetrisApp is the driver class for the tetris game. It contains the game loop,
 * the pieces that are next up for play, and the piece that is in hold. It also
 * creates the GameFrame and Score objects that are used for the game.
 * 
 * @author Brian
 *
 */
public class TetrisApp {
	private static boolean playingGame;
	private static GameFrame gf;
	private static Score score;
	private static ArrayList<GenericPiece> nextPieces;
	private static GenericPiece holdPiece;

	/**
	 * Chooses a random piece from the possible pieces.
	 * 
	 * @return A GenericPiece object of a piece.
	 */
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

	/**
	 * Gets the board object of the game.
	 * 
	 * @return The Board object.
	 */
	public static Board getBoard() {
		return gf.getBoard();
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
	 * Gets the piece that is currently being held in the hold.
	 * 
	 * @return A GenericPiece object of the piece being held.
	 */
	public static GenericPiece getHoldPiece() {
		return holdPiece;
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
	 * Get the score object of the game.
	 * 
	 * @return The score object.
	 */
	public static Score getScore() {
		return score;
	}

	/**
	 * Gets the ScorePanel object of the game.
	 * 
	 * @return The ScorePanel object.
	 */
	public static ScorePanel getScorePanel() {
		return gf.getScorePanel();
	}

	public static void main(String[] args) {
		startGame();
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
	 * Starts the game and has the main game control loop.
	 */
	private static void startGame() {
		// Set variables
		playingGame = true;
		score = new Score();
		nextPieces = new ArrayList<>();
		holdPiece = generatePiece();
		while (nextPieces.size() < 3) {
			nextPieces.add(generatePiece());
		}

		// Create and display the board
		gf = new GameFrame();
		gf.setVisible(true);
		getScorePanel().updateHoldDisplay();
		getScorePanel().updateNextPieceDisplay();

		while (playingGame) {
			GenericPiece tmp = nextPieces.get(0);
			nextPieces.remove(0);
			nextPieces.add(generatePiece());
			gf.getScorePanel().updateNextPieceDisplay();
			getBoard().placePiece(tmp);
		}
	}

	/**
	 * Stops the game that is in progress, prompts the user for updating the high
	 * score, and prompts the user to play the game again.
	 */
	public static void stopGame() {
		try {
			playingGame = false;
			getScore().updateHighScore();
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
}
