package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.Score;
import game.TetrisApp;

/**
 * ScorePanel.java
 * Created by Laura Baldwin
 * Purpose:
 * This class is to display the highscores, the block on hold, the blocks coming up, and a button to quit the program
 */
public class ScorePanel extends JPanel {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 1955286229728891072L;

	public ScorePanel(){
		this.setSize(200,600);
		this.setBounds(300, 0, 200, 600);
		this.setBackground(Color.black);
		this.setLayout(null);

		//create a label under which to display the scores
		JLabel title = new JLabel("Scores:",JLabel.CENTER);
		Font allFont = new Font(title.getFont().getName(), Font.BOLD, 24);
		title.setFont(allFont);
		title.setBounds(0, 0, this.getWidth(), 50);
		title.setForeground(Color.YELLOW);
		this.add(title);

		//create a label to display user score
		JLabel userScore = new JLabel("", JLabel.CENTER);
		userScore.setFont(allFont);

		//create a label in which to display high scores
		JLabel scores = new JLabel("", JLabel.CENTER);
		scores.setFont(allFont);
		scores.setBounds(0, 50, this.getWidth(), 150);
		this.add(scores);
		
		//create a label under which to display the block on hold
		
		JLabel hold = new JLabel("Hold:",JLabel.CENTER);
		//Font holdFont = new Font(hold.getFont().getName(), Font.BOLD, 24);
		hold.setFont(allFont);
		hold.setBounds(0, 150, this.getWidth(), 50);
		hold.setForeground(Color.YELLOW);
		this.add(hold);
		
		//create a label under which to display the next pieces
		
		JLabel next = new JLabel("Next:",JLabel.CENTER);
		//Font nextFont = new Font(next.getFont().getName(), Font.BOLD, 24);
		next.setFont(allFont);
		next.setBounds(0, 300, this.getWidth(), 50);
		next.setForeground(Color.YELLOW);
		this.add(next);

		//create 3 2x4 Point Arrays to display the next pieces
		Point [][] next1 = new Point[2][4];
		Point [][] next2 = new Point[2][4];
		Point [][] next3 = new Point[2][4];

		for(int rows = 0; rows < 2; rows++){
			for(int cols = 0; cols < 4; cols++){
				next1[rows][cols] = new Point(rows, cols);
				next2[rows][cols] = new Point(rows, cols);
				next3[rows][cols] = new Point(rows, cols);
			}
		}
		
		for(int rows = 0; rows < 2; rows++ ){
			for(int cols = 0; cols < 4 ; cols++){
				next1[rows][cols].setBackground(Color.ORANGE);
				next1[rows][cols].setBounds(40+(cols*30), 350+(rows*30), 30, 30);

				this.add(next1[rows][cols]);

			}
		}

		//create button to quit game
		JButton quit = new JButton("Quit");
		quit.setBackground(Color.BLACK);
		quit.setForeground(Color.YELLOW);
		//Font quitFont = new Font(next.getFont().getName(), Font.BOLD, 24);
		quit.setFont(allFont);
		quit.setBounds(0,570,this.getWidth(),30);
		quit.setBorderPainted(false);
		
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TetrisApp.stopGame();
			}
		});
		this.add(quit);
		
		
	}
	//update nextPieces:

	
	
}
