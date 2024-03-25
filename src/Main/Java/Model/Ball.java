package Model;

import javafx.scene.shape.Circle;
import java.util.Random;

/**
 * The Ball class represents a ball object in the game.
 */
public class Ball extends Circle implements Resizable {
    private double speed; // The speed of the ball
    private Random random; // Random object for generating random directions
    private int xVelocity; // The velocity of the ball along the x-axis
    private int yVelocity; // The velocity of the ball along the y-axis
    private double initialCenterX; // The initial x-coordinate of the ball's center
    private double initialCenterY; // The initial y-coordinate of the ball's center
    private double speedIncrement = 0.25; // The increment value for speed

    /**
     * Constructs a new Ball object with the specified center coordinates, radius, and speed.
     *
     * @param centerX The x-coordinate of the center of the ball
     * @param centerY The y-coordinate of the center of the ball
     * @param radius  The radius of the ball
     * @param speed   The speed of the ball
     */
    public Ball(double centerX, double centerY, double radius, double speed) {
        super(centerX, centerY, radius);
        this.speed = speed;
        this.random = new Random();
        this.xVelocity = getRandomDirection();
        this.yVelocity = getRandomDirection();
        this.initialCenterX = centerX;
        this.initialCenterY = centerY;
    }

    private int getRandomDirection() {
        return random.nextBoolean() ? -1 : 1;
    }

    /**
     * Resizes the ball along the x-axis by the specified factor.
     *
     * @param factor The resizing factor
     */
    @Override
    public void resizeX(double factor) {
        double newCenterX = getCenterX() * factor;
        setCenterX(newCenterX);
        double newRadiusX = getRadius() * Math.abs(factor);
        setRadius(newRadiusX);
    }

    /**
     * Resizes the ball along the y-axis by the specified factor.
     *
     * @param factor The resizing factor
     */
    @Override
    public void resizeY(double factor) {
        double newCenterY = getCenterY() * factor;
        setCenterY(newCenterY);
        double newRadiusY = getRadius() * Math.abs(factor);
        setRadius(newRadiusY);
    }

    /**
     * Moves the ball according to its current velocity and speed.
     *
     * @param gameHeight The height of the game area
     */
    public void move(double gameHeight) {
        double x = super.getCenterX() + xVelocity * speed;
        super.setCenterX(x);
        double y = super.getCenterY() + yVelocity * speed;
        super.setCenterY(y);

        if (y - getRadius() * 0.2 <= 0 || y + getRadius() >= gameHeight) {
            yVelocity = -yVelocity;
        }
    }

    /**
     * Resets the ball to its initial position and randomly changes its direction.
     */
    public void reset() {
        super.setCenterX(initialCenterX);
        super.setCenterY(initialCenterY);
        xVelocity = getRandomDirection();
        yVelocity = getRandomDirection();
    }

    /**
     * Reverses the direction of the ball along the x-axis.
     */
    public void reverseXDirection(){
        xVelocity = -xVelocity;
    }

    /**
     * Gets the speed of the ball.
     *
     * @return The speed of the ball
     */
    public double getBallSpeed(){
        return this.speed;
    }

    /**
     * Sets the speed of the ball.
     *
     * @param speed The new speed of the ball
     */
    public void setBallSpeed(double speed){
        this.speed = speed;
    }

    /**
     * Increments the speed of the ball by the speed increment value.
     */
    public void incrementSpeed(){
        this.speed += speedIncrement;
    }

    /**
     * Gets the velocity of the ball along the x-axis.
     *
     * @return The velocity of the ball along the x-axis
     */
    public double getXVelocity(){
        return xVelocity;
    }
}
