package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.TetrisApp;
import pieces.GenericPiece;
import data.Score;

/**
 * ScorePanel.java Created by Laura Baldwin Purpose: This class is to display
 * the highscores, the block on hold, the blocks coming up, and a button to quit
 * the program
 */
public class ScorePanel extends JPanel {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 1955286229728891072L;
	private static Point[][] heldPiece;
	private static Point[][] next1;
	private static Point[][] next2;
	private static Point[][] next3;

	public ScorePanel(Score score) {
		this.setSize(200, 600);
		this.setBounds(300, 0, 200, 600);
		this.setBackground(Color.black);
		this.setLayout(null);
		heldPiece = null;

		// create a label under which to display the scores
		JLabel title = new JLabel("High Scores:", JLabel.CENTER);
		Font allFont = new Font(title.getFont().getName(), Font.BOLD, 24);
		title.setFont(allFont);
		title.setBounds(0, 0, this.getWidth(), 40);
		title.setForeground(Color.YELLOW);
		this.add(title);
		
		// create a label in which to display high scores
		JLabel scores = new JLabel(getHighScores(score), JLabel.CENTER);
		Font scoreFont = new Font(title.getFont().getName(), Font.BOLD, 20);
		scores.setFont(scoreFont);
		scores.setBounds(0, 40, this.getWidth(), 80);
		scores.setForeground(Color.YELLOW);
		this.add(scores);

		//create a label under which to display the user's score

		// create a label to display user score
		Integer intScore = score.getUserScore();

		JLabel userScore = new JLabel("Your Score: " + intScore.toString(), JLabel.CENTER);
		
		userScore.setFont(scoreFont);
		userScore.setBounds(0,80,this.getWidth(), 140);
		userScore.setForeground(Color.YELLOW);
		this.add(userScore);

		// create a label under which to display the block on hold

		JLabel hold = new JLabel("Hold:", JLabel.CENTER);
		hold.setFont(allFont);
		hold.setBounds(0, 170, this.getWidth(), 70);
		hold.setForeground(Color.YELLOW);
		this.add(hold);

		// create an 2x4 array of Points to hold the held piece
		heldPiece = new Point[2][4];
		for (int rows = 0; rows < 2; rows++) {
			for (int cols = 0; cols < 4; cols++) {
				heldPiece[rows][cols] = new Point(rows, cols);
			}
		}

		// display the held piece
		updateHoldDisplay();

		// create a label under which to display the next pieces
		JLabel next = new JLabel("Next:", JLabel.CENTER);
		next.setFont(allFont);
		next.setBounds(0, 300, this.getWidth(), 50);
		next.setForeground(Color.YELLOW);
		this.add(next);

		// create 3 2x4 Point Arrays to display the next pieces
		next1 = new Point[2][4];
		next2 = new Point[2][4];
		next3 = new Point[2][4];

		for (int rows = 0; rows < 2; rows++) {
			for (int cols = 0; cols < 4; cols++) {
				next1[rows][cols] = new Point(rows, cols);
				next2[rows][cols] = new Point(rows, cols);
				next3[rows][cols] = new Point(rows, cols);
			}
		}
		// display the next pieces
		updateNextPieceDisplay();

		// create button to quit game
		JButton quit = new JButton("Quit");
		quit.setBackground(Color.BLACK);
		quit.setForeground(Color.YELLOW);
		// Font quitFont = new Font(next.getFont().getName(), Font.BOLD, 24);
		quit.setFont(allFont);
		quit.setBounds(0, 570, this.getWidth(), 30);
		quit.setBorderPainted(false);

		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TetrisApp.stopGame();
			}
		});
		this.add(quit);

	}
	// update nextPieces:

	public void updateHoldDisplay() {
		for (int rows = 0; rows < 2; rows++) {
			for (int cols = 0; cols < 4; cols++) {
				try {
					if (TetrisApp.getHoldPiece().getShape()[rows][cols] == 1) {
						heldPiece[rows][cols].setBackground(TetrisApp.getHoldPiece().getColor());
						heldPiece[rows][cols].setBounds(40 + (cols * 30), 230 + (rows * 30), 30, 30);
						this.add(heldPiece[rows][cols]);
					} else {
						throw new NullPointerException("Piece is not there");
					}
				} catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
					heldPiece[rows][cols].setBackground(Color.GRAY);
					heldPiece[rows][cols].setBounds(40 + (cols * 30), 230 + (rows * 30), 30, 30);
					this.add(heldPiece[rows][cols]);
				}
			}
		}
	}

	public void updateNextPieceDisplay() {
		for (int rows = 0; rows < 2; rows++) {
			for (int cols = 0; cols < 4; cols++) {
				try {
					if (TetrisApp.getNextPieces().get(0).getShape()[rows][cols] == 1) {
						next1[rows][cols].setBackground(TetrisApp.getNextPieces().get(0).getColor());
						next1[rows][cols].setBounds(40 + (cols * 30), 350 + (rows * 30), 30, 30);
						this.add(next1[rows][cols]);
					} else {
						throw new NullPointerException("Piece is not there");
					}
				} catch (IndexOutOfBoundsException | NullPointerException e) {
					next1[rows][cols].setBackground(Color.GRAY);
					next1[rows][cols].setBounds(40 + (cols * 30), 350 + (rows * 30), 30, 30);
					this.add(next1[rows][cols]);
				}
				try {
					if (TetrisApp.getNextPieces().get(1).getShape()[rows][cols] == 1) {
						next2[rows][cols].setBackground(TetrisApp.getNextPieces().get(1).getColor());
						next2[rows][cols].setBounds(40 + (cols * 30), 420 + (rows * 30), 30, 30);
						this.add(next2[rows][cols]);
					} else {
						throw new NullPointerException("Piece is not there");
					}
				} catch (IndexOutOfBoundsException | NullPointerException e) {
					next2[rows][cols].setBackground(Color.GRAY);
					next2[rows][cols].setBounds(40 + (cols * 30), 420 + (rows * 30), 30, 30);
					this.add(next2[rows][cols]);
				}
				try {
					if (TetrisApp.getNextPieces().get(2).getShape()[rows][cols] == 1) {
						next3[rows][cols].setBackground(TetrisApp.getNextPieces().get(2).getColor());
						next3[rows][cols].setBounds(40 + (cols * 30), 490 + (rows * 30), 30, 30);
						this.add(next3[rows][cols]);
					} else {
						throw new NullPointerException("Piece is not there");
					}
				} catch (IndexOutOfBoundsException | NullPointerException e) {
					next3[rows][cols].setBackground(Color.GRAY);
					next3[rows][cols].setBounds(40 + (cols * 30), 490 + (rows * 30), 30, 30);
					this.add(next3[rows][cols]);
				}
			}
		}
	}

	private String getHighScores(Score score){
		//create String [] to parse the highscores into
		String [] highScores = score.toString().split("\n");
		StringBuilder sb = new StringBuilder();
		sb.append("<HTML><body>");
		for(int count = 0; count < highScores.length; count++){
			if(count == highScores.length-1){
				sb.append(highScores[count]);
			}
			else{
				sb.append(highScores[count]);
				sb.append("<br>");
			}
		}
		sb.append("</body></HTML>");
		String stringScores = sb.toString();
		return stringScores;
	}
}