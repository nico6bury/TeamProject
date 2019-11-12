package pieces;

import java.awt.Color;

/**
 * StickPiece is a representation of the 4x1 tetris piece,
 * which is commonly refered to as a "stick piece"
 * 
 * @author Laura
 * 
 */
public class StickPiece extends GenericPiece{
    /**
     * No parameter constructor
     * Creates a StickPiece object
     */
    public StickPiece(){
        this.setColor(Color.MAGENTA);

        //initialize the shape
        int[][] shape = {{1,1,1,1}};
        this.setShape(shape);
    }
}