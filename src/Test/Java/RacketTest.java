package Test.Java;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.Racket;

public class RacketTest {
    private Racket racket;
    private double speed = 15;

    @Before
    public void setUp() {
        // Create a new Racket instance for each test
        racket = new Racket(50, 50, 20, 100); // Example parameters for racket initialization
        racket.setSpeed(speed);
    }

    @Test
    public void testMoveUp() {
        double initialY = racket.getY();
        racket.moveUp();
        double newY = racket.getY();
        // Check if the racket moved up correctly
        assertEquals(initialY - speed, newY, 0.01); // Use delta to handle floating point imprecisions
    }

    @Test
    public void testMoveDown() {
        double initialY = racket.getY();
        double gameHeight = 400; // Example game height
        racket.moveDown(gameHeight);
        double newY = racket.getY();
        // Check if the racket moved down correctly
        assertEquals(initialY + speed, newY, 0.01); // Use delta to handle floating point imprecisions
    }
}
