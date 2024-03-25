package Model;

import javafx.scene.shape.Rectangle;

/**
 * The Racket class represents a game racket that extends Rectangle and implements Resizable.
 */
public class Racket extends Rectangle implements Resizable {
    private double speed; // The speed of the racket

    /**
     * Constructs a new Racket object with the specified coordinates, width, and height.
     *
     * @param x      The x-coordinate of the racket
     * @param y      The y-coordinate of the racket
     * @param width  The width of the racket
     * @param height The height of the racket
     */
    public Racket(double x, double y, double width, double height){
        super(x, y, width, height);
        this.speed = 15;
    }

    /**
     * Moves the racket up by the speed.
     */
    public void moveUp() {
        double newY = getY() - speed;
        if (newY >= 0) {
            setY(newY);
        } else {
            setY(0);
        }
    }

    /**
     * Moves the racket down by the speed.
     *
     * @param gameHeight The height of the game area
     */
    public void moveDown(double gameHeight) {
        double newY = getY() + speed;
        double height = getHeight();
        double maxHeight = gameHeight - 0.9 * height;

        if (newY <= maxHeight) {
            setY(newY);
        } else {
            setY(maxHeight);
        }
    }

    /**
     * Resizes the racket along the x-axis by the specified factor.
     *
     * @param factor The resizing factor
     */
    @Override
    public void resizeX(double factor) {
        double newX = getX() * factor;
        double newWidth = getWidth() * factor;
        setX(newX);
        setWidth(newWidth);
    }

    /**
     * Resizes the racket along the y-axis by the specified factor.
     *
     * @param factor The resizing factor
     */
    @Override
    public void resizeY(double factor) {
        double newY = getY() * factor;
        double newHeight = getHeight() * factor;
        setY(newY);
        setHeight(newHeight);
    }

    /**
     * Sets the speed of the racket.
     *
     * @param speed The new speed of the racket
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }
}
