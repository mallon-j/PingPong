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

    @Test
    public void testEndoFGame() {
        game.setScoreLimit(10);
        game.getPlayer1().setScore(7);
        game.getPlayer2().setScore(11);
        // score is 11-7 up to 10
        assertTrue(manager.checkEndOfGame(game));
        // score is 7-7 up to 10
        game.getPlayer2().setScore(7);
        assertFalse(manager.checkEndOfGame(game));
    }

    @Test
    public void testGoalPlayer2() {
        game.getBall().setCenterX(-10);
        assertTrue(manager.player2Scores(game));
        game.getBall().setCenterX(100);
        assertFalse(manager.player2Scores(game));
    }

    @Test
public void testHandleRacketCollision() {
    Ball ball = new Ball(50, 50, 10, 1.0);
    
    Racket racket1 = new Racket(0, 40, 10, 60);
    Racket racket2 = new Racket(90, 40, 10, 60); 
    
    // Simulating a collision with racket1
    ball.setCenterX(racket1.getX() + racket1.getWidth() / 2);
    ball.setCenterY(racket1.getY() + racket1.getHeight() / 2);
    manager.handleRacketCollision(ball, racket1, racket2, 1.0); // Assuming initial speed is 1.0
    
    // Verify the speed is incremented
    assertEquals(1.25, ball.getBallSpeed(), 0.001);
    
    // Simulating a collision with racket2
    ball.setCenterX(racket2.getX());
    ball.setCenterY(racket2.getY() + racket2.getHeight() / 2);
    manager.handleRacketCollision(ball, racket1, racket2, 1.0); // Assuming initial speed is 1.0
    
    // Verify the speed is incremented
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
                fail("Timeout waiting for JavaFX toolkit initialization.");
            }
        }
    }
}
