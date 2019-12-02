/** Score.java
 * Created by Nicholas Sixbury
 * The purpose of this class is to hold information for both the user's
 * current score as well as the high scores, which are stored in a text
 * file.
 */

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class Score{
    final private int SCORE_TO_KEEP = 3;
    private int userScore;
    private ArrayList <Integer> highScore;
    private ArrayList <String> names;
    private File scoresFile;

    /** no-arg constructor
     * This is the only constructor for the class. If you wish to use a
     * different filename, edit it here.
     */
    public Score() throws FileNotFoundException, IOException{
        scoresFile = new File("highScores.txt");
        if(!scoresFile.exists()){
            scoresFile.createNewFile();
        }
        userScore = 0;
        highScore = new ArrayList<>(0);
        names = new ArrayList<>(0);
        readFile();
    }//end no-arg constructor

    /** readFile()
     * This method reads whatever File object was created to the
     * parallel arrayLists names and highScore.
     */
    public void readFile() throws FileNotFoundException{
        highScore.clear();
        names.clear();
        Scanner fileReader = new Scanner(scoresFile);
        while(fileReader.hasNext()){
            String[] splitter = fileReader.nextLine().split(" ");
            names.add(splitter[0]);
            highScore.add(Integer.parseInt(splitter[1]));
        }//end while
        fileReader.close();
    }//end readFile

    /** writeFile()
     * This method writes all the data in the parallel ArrayLists
     * names and highScore to a File created on object creation.
     * It accomplishes this by printing the toString of this class.
     */
    public void writeFile() throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(scoresFile);
        pw.println(toString());
        pw.close();
    }//end writeFile

    /** toString
     * This method will return a String representation of the current
     * score, in the format of name + space + score, or ABC 500, with
     * each score listed on a different line. This is ordered with high
     * scores at the top, and lower scores at the bottom.
     * 
     * @return returns a String representation of the current score
     */
    public String toString(){
        if(names.size() != highScore.size()){
            return "Error: Bad Data";
        }//end if
        else{
            StringBuilder sb = new StringBuilder();
            //int size = highScore.size();
            for(int i = 0; i < names.size(); i++){
                sb.append(names.get(i));
                sb.append(" ");
                sb.append(highScore.get(i));
                sb.append("\n");
            }//end for
            if(sb.length() == 0){
                sb.append("No Previous High Scores");
            }//end if
            return sb.toString();
        }//end else
    }//end toString

    /** updateUserScore
     * This method lets you update the user's score by adding an int
     * to their current score. To subtract points, call this method with
     * a negative number.
     * 
     * @param addition This is the number by which to add to the score
     */
    public void updateUserScore(int addition){
        userScore += addition;
    }//end updateUserScore

    /** getUserScore
     * A get method for userScore
     * 
     * @return returns the current user's score as an integer
     */
    public int getUserScore(){
        return userScore;
    }//end getUserScore

    /** resetUserScore
     * sets the user's score to 0
     */
    public void resetUserScore(){
        userScore = 0;
    }//end resetUserScore

    /** updateHighScore
     * This method tests whether or not the highScore needs to be
     * updated based on the current user's score. If it does need to
     * be updated, then it will update it. If the scores are in the wrong
     * order, then this is the place to fix. If there are less than 3
     * high scores, then the user's score will automatically added.
     * Additionally, if high scores are updated, then the method will
     * automatically write them to file
     * 
     * @return returns true if the score was updated and false otherwise
     */
    public boolean updateHighScore()throws FileNotFoundException{
        if(highScore.size() < 3){
            boolean flag = true;
            for(int i = 0; i < highScore.size(); i++){
                if(userScore > highScore.get(i)){
                    highScore.add(i, userScore);
                    names.add(i, getUsername());
                    i = highScore.get(i);
                    flag = false;
                }//end if
            }//end for
            if(flag){
                highScore.add(userScore);
                names.add(getUsername());
            }//end if
            writeFile();
            return true;
        }
        else if(userScore > highScore.get(highScore.size()-1)){
        //tests if userScore is gerater than lowest high score
            for(int i = 0; i < highScore.size(); i++){
                if(userScore > highScore.get(i)){
                    highScore.add(i, userScore);
                    names.add(i, getUsername());
                    i = highScore.get(i);
                }//end if
            }//end for
            if(highScore.size() > 3){
                highScore.remove(highScore.get(SCORE_TO_KEEP));
                names.remove(names.get(SCORE_TO_KEEP));
            }//end 
            writeFile();
            return true;
        }
        else{
            return false;
        }
    }//end updateHighScore

    private String getUsername(){
        String name = JOptionPane.showInputDialog(null, 
        "What is your name?\n" + "You can use three characters.");
        while(name.length() > 3 || name.length() < 1){
            if(name.length() > 3){
                name = JOptionPane.showInputDialog(null, 
                "Please enter 3 characters or less.\n" + 
                "What is your name?");
            }//end if greater than 3 chars
            else{
                name = JOptionPane.showInputDialog(null,
                "You must enter at least something.\n" + 
                "What is your name?");
            }//end else name is empty
        }//end while
        return name.toUpperCase();
    }//end getUsername
}//end class