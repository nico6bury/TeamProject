
/** Point.java
 * Created by Nicholas Sixbry
 * The purpose of this class is to simulate a point by using an x and y
 * coordinate that together determine the point's position on a 2D plane.
 * The point also has some methods for doing things with lines created
 * by two points, as well as finding the distance between any two points.
 */
public class Point{
    //declaration of data properties
    private int xcor; //x-coordinate of this point
    private int ycor; //y-coordinate of this point


    //0,1,2 arg constructors
    public Point(){
        /** no-arg constructor
         * This is a no-arg constructor that sets the coordinates to 0
         * This means that without using the other constructor, you can't
         * really do anything with this point object
         */
        xcor = 0;
        ycor = 0;
    }//end no-arg constructor

    public Point(Point p){
        /** 1-arg constructor
         * This is a 1-arg constructor that essentially creates a copy of
         * another point object
         * @param p This is the point that will be copied
         */
        xcor = p.getX();
        ycor = p.getY();
    }//end 1-arg constructor

    public Point(int X, int Y){
        /** 2-arg constructor
         * This is the 2-arg constructor that should be used when making
         * new points with working coordinates.
         * @param X This is the x-coordinate of the point
         * @param Y This is the y-coordinate of the point
         */
        xcor = X;
        ycor = Y;
    }//end 2-arg constructor

    
    //get and set methods for x and y coordinates
    public int getX(){
        /** getX()
         * This method fetches the x-coordinate of this point
         * @return It returns the x-coordinate as an integer
         */
        return xcor;
    }//end getX()

    public int getY(){
        /** getY()
         * This method fetches the y-coordinate of this point
         * @return It returns the y-coordinate as an integer
         */
        return ycor;
    }//end getY()

    public void setX(int X){
        /** setX()
         * This method allows the user to set the x-coordinate to a 
         * specified integer
         * @param X This integer is assigned to the x-coordinate
         */
        xcor = X;
    }//end setX()

    public void setY(int Y){
        /** setY()
         * This method allows the user to set the y-coordinate to a 
         * specified integer
         * @param Y This integer is assigned to the y-coordinate
         */
        ycor = Y;
    }//end setY()


    //toString and equals methods
    public boolean equals(Point p){
        /** equals()
         * This special method determines whether or not two point objects
         * are equal to each other. It checks to see if both the x-coordinates
         * and the y-coordinate are the same, and only returns true if both
         * are equal
         * @param p This is the second point object which will be compared
         * to the point that called the method
         * @return The method returns true if both points are the same and
         * false if they are different in any way
         */
        return ((xcor == p.getX()) && (ycor == p.getY()));
    }//end equals()

    public String toString(){
        /** toString()
         * This special method returns out the contents of the point
         * object as a string. It does so in the format (x,y)
         * @return The method returns the contents of the current
         * point object as a String in the format (x,y)
         */
        return ("(" + xcor + "," + ycor + ")");
    }//end toString()


    /* The section that follows contains a variety of mischellanious
    methods that use multiple points, including some methods adapted from
    the Line class created in the CIS200 Lab #6. They work by using
    the current point object as one of the points as well as by having
    all the other points as parameters. This section has it's own 
    get methods too, so that's fun.*/

    //section-specific get methods
    public double getSlope(Point second){
        /** getSlope()
         * This method, when given a line by means of two points,
         * (this point object and a second one supplied through parameters,)
         * returns the slope of the line created by those two points.
         * @param second is the second point that is used in calculations
         * @return The method returns the slope of the line created
         * by both the point which calls this method as well as the
         * point supplied as a parameter
         */
        //formula (y2-y1)/(x2-x1)
        double x_cor = xcor;
        double y_cor = ycor;
        return ((second.getY() - y_cor) / 
        (second.getX() - x_cor));
    }//end getSlope()

    public static double getSlope(Point first, Point second){
        /** getSlope()
         * This is the static version of getSlope(), and everything
         * at least should be exactly the same as the non-static
         * version, except that this version takes both points as arguments
         * @param first is the first of the two lines that together
         * will create a line which we find the slope of
         * @param second is the second point used to form the line
         * @return The method returns the slope of the line created
         * by connecting both of the point parameters.
         */
        //formula (y2-y1)/(x2-x1)
        double x_cor = first.getX();
        double y_cor = first.getY();
        return ((second.getY() - y_cor) / 
        (second.getX() - x_cor));
    }//end static getSlope()

    public double getYInt(Point second){
        /** getYInt()
         * This method will, when given a line by means of two points,
         * (this point object and a second one supplied through parameters,)
         * returns the y-intercept of the line created by those two points.
         * This method is reliant on getSlope, so any changes to that will
         * probably affect this method as well.
         * @param second is the second point that is used in calculations
         * @return The method returns the y-intercept of the line created by
         * both the point which calls this method as well as the point
         * supplied as a parameter
         */
        //formula y1-slope*x1 where (x1,y1) is a point on the line
        double x_cor = xcor;
        double y_cor = ycor;
        double slope = getSlope(second);
        return ((y_cor) - (slope * x_cor));
    }//end getYInt

    public static double getYInt(Point first, Point second){
        /** getYInt()
         * This static version of getYInt() at least should be exactly
         * the same as the non-static version, except that instead
         * of needing to be called by a point, it can simply be called
         * anywhere. The points supplied by parameters are what get used
         * in calculating a line. This method relies on the static version
         * of getSlope()
         * @param first is the first point used to make a line
         * @param second is the second point used to make a line
         * @return The method returns the y-intercept of the line created
         * by connecting the two points as a double
         */
        double x_cor = first.getX();
        double y_cor = first.getY();
        double slope = getSlope(first, second);
        return ((y_cor) - (slope * x_cor));
    }//end static getYInt()

    public double getDistance(Point second){
        /** getDistance()
         * This method uses the distance method to calculate the distance
         * between two points. The distance formula:
         * sqrt((x2 - x1)^2 + (y2 - y1)^2)
         * @param second The method finds the distance between this point
         * and the second point object
         * @return The method returns the distance between this point
         * object and a second supplied via parameter as a double
         */
        double x_cor = xcor;
        double y_cor = ycor;
        double part1 = second.getX() - x_cor;
        double part2 = second.getY() - y_cor;
        part1 = Math.pow(part1, 2);
        part2 = Math.pow(part2, 2);
        double answer = part1 + part2;
        answer = Math.sqrt(answer);
        return answer;

    }//end getDistance()

    public String getLine(Point second, int type){
        /** getLine()
         * This method retrieves the point slope or slope intercept
         * equation of any line, given two points.
         * @param second this is the point that, used with this point
         * object, will create a line
         * @param type This determines whether the String returned will
         * a point-slope form line or a slope intercept line. A value of 0
         * indicates that the line will be point-slope, while a value of 1
         * indicates that the line will be slope intercept form
         * @return This method will return as a String the equation of the
         * line created by the two points, and the type of line is
         * determined by the integer type variable.
         */
        if (type == 0){ //results in point-slope form
            return ("y-"+ycor+"="+getSlope(second)+"(x-"+xcor+")");
        }//end if
        else{ //results in slope-intercept form
            return ("y="+getSlope(second)+"x+"+getYInt(second));
        }
    }//end getLine()

    public boolean intersects(Point second, Point third, Point fourth){
        /** intersects()
         * This method creates a line between this point object and the
         * second, as well as between the third point object and the 
         * fourth point object, so 1-2 and 3-4, and then it tests if 
         * those lines intersect at any point. The rules below determine
         * if the lines created will intersect:
         * 1) 2 lines intersect if their slopes are different
         * 2) they also intersect if the slopes AND y-intercepts are the same
         * 3) otherwise, they do not intersect
         * 
         * @param second is the point which will form a line with this
         * point object
         * @param third is the point object that will form a line with 
         * the fourth point object
         * @param fourth is the point object that will form a line with 
         * the third point object
         * @return The method returns a boolean value of true if the lines
         * created by the four points do intersect and false if they do not
         */
        if (getSlope(second) != getSlope(third, fourth)){
            return true;
        }
        else if (getYInt(second) != getYInt(third, fourth)){
            return true;
        }
        else{
            return false;
        }
    }

}//end class