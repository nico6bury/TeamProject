package pieces;

import java.awt.color;

/**
 * SquarePiece is a representation of the tetris piece shaped like a 2x2 box,
 * which is commonly refered to as a "square piece"
 * 
 * @author Laura
 * 
 */
public class SquarePiece extends GenericPiece{
    /**
     * No parameter constructor
     * Creates a SquarePiece object
     */
    public SquarePiece(){
        this.setColor(Color.RED);
        //initialize the shape
        int[][] shape = {{1,1}, {1,1}};
        this.setShape(shape);
    }
}