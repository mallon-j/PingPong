package Model;

/**
 * The Resizable interface represents an object that can be resized along the x and y axes.
 */
public interface Resizable {
    /**
     * Resizes the object along the x-axis by the specified factor.
     *
     * @param factor The resizing factor
     */
    public void resizeX(double factor);

    /**
     * Resizes the object along the y-axis by the specified factor.
     *
     * @param factor The resizing factor
     */
    public void resizeY(double factor);
}
