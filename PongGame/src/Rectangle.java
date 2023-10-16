import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * A class designed to draw players geometrically as a rectangle object
 */
public class Rectangle {

    private double x; //the centered-x coordinate of this rectangle object
    private double y; //the centered-y coordinate of this rectangle object
    private double width; //the width in pixels of this rectangle object
    private double height; //the height in pixels of this rectangle object
    private Color color; //the color of this rectangle object

    /**
     * Creates a new rectangle object
     *
     * @param x      the centered-x position of this rectangle object
     * @param y      the centered-y position of this rectangle object
     * @param width  the width of this rectangle object in pixels
     * @param height the height of this rectangle object in pixels
     * @param color  the color of this rectangle object
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    /**
     * Draws this rectangle to the game window
     *
     * @param g2D the graphics engine to use to draw this rectangle
     */
    public void draw(Graphics2D g2D) {
        g2D.setColor(color);
        g2D.fill(new Rectangle2D.Double(x, y, width, height));
    }

    /**
     * Getter for this rectangle's centered-x coordinate
     *
     * @return the x-position of this rectangle
     */
    public double getX() {
        return x;
    }

    /**
     * Setter for this rectangle's centered-x coordinate
     *
     * @param x the new x-position of this rectangle to be set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter for this rectangle's centered-y coordinate
     *
     * @return the y-position of this rectangle
     */
    public double getY() {
        return y;
    }

    /**
     * Setter for this rectangle's centered-y coordinate
     *
     * @param y the new y-position of this rectangle to be set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Getter for this rectangle's width in pixels
     *
     * @return this rectangle's width in pixels
     */
    public double getWidth() {
        return width;
    }

    /**
     * Setter for this rectangle's width in pixels
     *
     * @param width the new width of this rectangle to be set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Getter for this rectangle's height in pixels
     *
     * @return this rectangle's height in pixels
     */
    public double getHeight() {
        return height;
    }

    /**
     * Setter for this rectangle's height in pixels
     *
     * @param height the new height of this rectangle to be set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Getter for this rectangle's color
     *
     * @return this rectangle's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for this rectangle's color
     *
     * @param color the new color of this rectangle to be set
     */
    public void setColor(Color color) {
        this.color = color;
    }


}
