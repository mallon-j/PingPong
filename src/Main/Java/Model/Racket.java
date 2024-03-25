package Model;

import javafx.scene.shape.Rectangle;

public class Racket extends Rectangle implements Resizable {
    private double speed;

    public Racket(double x, double y, double width, double height){
        super(x,y,width,height);
        this.speed = 15;
    }

    public void moveUp() {
        double newY = getY() - speed;
        if (newY >= 0) {
            setY(newY);
        } else {
            setY(0);
        }
    }

    public void moveDown(double gameHeight) {
        double newY = getY() + speed;
        double height = getHeight();
        double maxHeight = gameHeight - 0.9*height;

        if (newY <= maxHeight) {
            setY(newY);
        } else {
            setY(maxHeight);
        }
    }

    @Override
    public void resizeX(double factor) {
        double newX = getX() * factor;
        double newWidth = getWidth() * factor;
        setX(newX);
        setWidth(newWidth);
    }

    @Override
    public void resizeY(double factor) {
        double newY = getY() * factor;
        double newHeight = getHeight() * factor;
        setY(newY);
        setHeight(newHeight);
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }
}
