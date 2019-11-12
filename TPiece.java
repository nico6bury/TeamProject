package pieces;

import java.awt.Color;

/**
 * TPiece is a representation of the tetris piece shaped like a T,
 * commonly known as a "t piece"
 * 
 * @author Laura
 */
public class TPiece extends GenericPiece{
    /**
     * No parameter constructor
     * Creates a TPiece object
     */
    public TPiece(){
        this.setColor(Color.BLUE);
        //initialize the shape
        int[][] shape = {{0,1,0}, {1,1,1}};
        this.setShape(shape);
    }
}