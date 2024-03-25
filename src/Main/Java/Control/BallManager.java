package Control;

import Model.*;
import View.GameView;

/**
 * The BallManager class manages the behavior and movements of the ball in the game.
 */
public class BallManager implements Runnable {
    private Game game;
    private GameView gameView;

    /**
     * Constructs a BallManager object with the specified game model and game view.
     *
     * @param game     The game model
     * @param gameView The game view
     */
    public BallManager(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    /**
     * Checks if player 2 scores by detecting if the ball crosses the left boundary.
     *
     * @param game The game model
     * @return true if player 2 scores, false otherwise
     */
    public boolean player2Scores(Game game) {
        return game.getBall().getCenterX() <= 0;
    }

    /**
     * Checks if the game has reached its end based on the score limit.
     *
     * @param game The game model
     * @return true if the game has ended, false otherwise
     */
    public boolean checkEndOfGame(Game game) {
        int maxScore = Math.max(game.getPlayer1().getScore(), game.getPlayer2().getScore());
        return maxScore >= game.getScoreLimit();
    }

    /**
     * Handles collision of the ball with the rackets, adjusting its direction and speed accordingly.
     *
     * @param ball    The ball object
     * @param racket1 Racket object for player 1
     * @param racket2 Racket object for player 2
     * @param initialSpeed The initial speed of the ball
     */
    public void handleRacketCollision(Ball ball, Racket racket1, Racket racket2, double initialSpeed) {
        double distX1 = Math.abs(ball.getCenterX() - (racket1.getX() + racket1.getWidth() / 2));
        double distY1 = Math.abs(ball.getCenterY() - (racket1.getY() + racket1.getHeight() / 2));
        double distX2 = Math.abs(ball.getCenterX() - racket2.getX());
        double distY2 = Math.abs(ball.getCenterY() - (racket2.getY() + racket2.getHeight() / 2));

        if (distX1 <= (racket1.getWidth() / 2) && distY1 <= (racket1.getHeight() / 2)) {
            ball.reverseXDirection();
            ball.incrementSpeed();
        }

        if (distX2 <= (racket2.getWidth()) && distY2 <= (racket2.getHeight() / 2)) {
            ball.reverseXDirection();
            ball.incrementSpeed();
        }
    }

    /**
     * Handles collision of the ball with the boundaries, updating scores and resetting ball position if necessary.
     *
     * @param ball      The ball object
     * @param player1   Player 1 object
     * @param player2   Player 2 object
     * @param initialSpeed The initial speed of the ball
     */
    public void handleBoundaryCollision(Ball ball, Player player1, Player player2, double initialSpeed) {
        if (ball.getCenterX() <= 0) {
            player2.incrementScore();
            gameView.displayGoalMessage(player2.getName());
            ball.setBallSpeed(initialSpeed);
            ball.reset();
        }
        if (ball.getCenterX() > game.getGAME_WIDTH() - ball.getRadius()) {
            player1.incrementScore();
            gameView.displayGoalMessage(player1.getName());
            ball.setBallSpeed(initialSpeed);
            ball.reset();
        }
    }

    @Override
    public void run() {
        Ball ball = game.getBall();
        Racket racket1 = game.getRacket1();
        Racket racket2 = game.getRacket2();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        double initialSpeed = ball.getBallSpeed();

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!game.isPaused()) {
                ball.move(game.getGAME_HEIGHT());
                handleRacketCollision(ball, racket1, racket2, initialSpeed);
                handleBoundaryCollision(ball, player1, player2, initialSpeed);
            }
        }
    }
}
