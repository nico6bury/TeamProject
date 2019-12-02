package board;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;


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
		Font titleFont = new Font(title.getFont().getName(), Font.BOLD, 24);
		title.setFont(titleFont);
		title.setBounds(0, 0, this.getWidth(), 50);
		title.setForeground(Color.YELLOW);
		this.add(title);
		
		//create a label under which to display the block on hold
		
		JLabel hold = new JLabel("Hold:",JLabel.CENTER);
		Font holdFont = new Font(hold.getFont().getName(), Font.BOLD, 24);
		hold.setFont(holdFont);
		hold.setBounds(0, 150, this.getWidth(), 50);
		hold.setForeground(Color.YELLOW);
		this.add(hold);
		
		//create a label under which to display the next pieces
		
		JLabel next = new JLabel("Next:",JLabel.CENTER);
		Font nextFont = new Font(next.getFont().getName(), Font.BOLD, 24);
		next.setFont(nextFont);
		next.setBounds(0, 300, this.getWidth(), 50);
		next.setForeground(Color.YELLOW);
		this.add(next);
		
	}
	
}
