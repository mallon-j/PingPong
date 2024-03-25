package Model;
import javafx.scene.shape.Circle;
import java.util.Random;

public class Ball extends Circle implements Resizable {
    private double speed;
    private Random random;
    private int xVelocity;
    private int yVelocity;
    private double initialCenterX;
    private double initialCenterY;
    private double speedIncrement = 0.25;

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

    @Override
    public void resizeX(double factor) {
        double newCenterX = getCenterX() * factor;
        setCenterX(newCenterX);
        double newRadiusX = getRadius() * Math.abs(factor);
        setRadius(newRadiusX);
    }

    @Override
    public void resizeY(double factor) {
        double newCenterY = getCenterY() * factor;
        setCenterY(newCenterY);
        double newRadiusY = getRadius() * Math.abs(factor);
        setRadius(newRadiusY);
    }

    public void move(double gameHeight) {
        double x = super.getCenterX() + xVelocity * speed;
        super.setCenterX(x);
        double y = super.getCenterY() + yVelocity * speed;
        super.setCenterY(y);

        if (y - getRadius()*0.2 <= 0 || y + getRadius() >= gameHeight) {
            yVelocity = -yVelocity;
        }
    }

    public void reset() {
        super.setCenterX(initialCenterX);
        super.setCenterY(initialCenterY);
        xVelocity = getRandomDirection();
        yVelocity = getRandomDirection();
    }


    public void reverseXDirection(){
        xVelocity = -xVelocity;
    }

    public double getBallSpeed(){
        return this.speed;
    }

    public void setBallSpeed(double speed){
        this.speed = speed;
    }

    public void incrementSpeed(){
        this.speed += speedIncrement;
    }
}