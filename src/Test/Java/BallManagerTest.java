package Test.Java;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.*;
import Control.*;
import View.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

/**
 * This class contains unit tests for the BallManager class.
 */
public class BallManagerTest {
    Group root;
    Game game;
    GameView gameView;
    BallManager manager;

    @Before
    public void initialise() {
        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            root = new Group();
            game = new Game();
            gameView = new GameView(root, game);
            manager = new BallManager(game, gameView);
            game.instantiateGame();
        });
        waitForFxToolkit();
    }

    /**
     * Test case to verify the end of the game condition.
     * The end of the game condition is checked when one player's score reaches the
     * score limit.
     */
    @Test
    public void testEndoFGame() {
        // Set up the game with a score limit of 10
        game.setScoreLimit(10);
        game.getPlayer1().setScore(7);
        game.getPlayer2().setScore(11);

        // Verify that the game ends when a player's score reaches the limit
        assertTrue(manager.checkEndOfGame(game));

        // Verify that the game doesn't end if neither player reaches the limit
        game.getPlayer2().setScore(7);
        assertFalse(manager.checkEndOfGame(game));
    }

    /**
     * Test case to verify scoring a goal for Player 2.
     * The goal is scored when the ball's x-coordinate goes beyond the left
     * boundary.
     */
    @Test
    public void testGoalPlayer2() {
        // Set the ball's x-coordinate beyond the left boundary
        game.getBall().setCenterX(-10);

        // Verify that the goal is scored for Player 2
        assertTrue(manager.player2Scores(game));

        // Verify that no goal is scored when the ball is within bounds
        game.getBall().setCenterX(100);
        assertFalse(manager.player2Scores(game));
    }

    /**
     * Test case to verify the handling of racket collisions.
     * The ball's speed should be incremented upon collision with a racket.
     */
    @Test
    public void testHandleRacketCollision() {
        // Create a ball and two rackets
        Ball ball = new Ball(50, 50, 10, 1.0);
        Racket racket1 = new Racket(0, 40, 10, 60);
        Racket racket2 = new Racket(90, 40, 10, 60);

        // Simulate a collision with racket1
        ball.setCenterX(racket1.getX() + racket1.getWidth() / 2);
        ball.setCenterY(racket1.getY() + racket1.getHeight() / 2);
        manager.handleRacketCollision(ball, racket1, racket2, 1.0); // Assuming initial speed is 1.0

        // Verify that the speed is incremented
        assertEquals(1.25, ball.getBallSpeed(), 0.001);

        // Simulate a collision with racket2
        ball.setCenterX(racket2.getX());
        ball.setCenterY(racket2.getY() + racket2.getHeight() / 2);
        manager.handleRacketCollision(ball, racket1, racket2, 1.0); // Assuming initial speed is 1.0

        // Verify that the speed is incremented again
        assertEquals(1.5, ball.getBallSpeed(), 0.001);
    }

    // Method to wait for JavaFX Toolkit to be initialized
    private void waitForFxToolkit() {
        long startTime = System.currentTimeMillis();
        long timeout = 10000; // Timeout after 10 seconds

        while (root == null || game == null || gameView == null || manager == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Timeout waiting for JavaFX toolkit initialization.");
            }
        }
    }
}
