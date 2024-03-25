package Control;
import Model.*;
import View.GameView;

public class BallManager implements Runnable{
    private Game game;
    private GameView gameView;
    public BallManager(Game c, GameView gameView) {
        this.game=c;
        this.gameView=gameView;
    }

    public boolean player2Scores(Game game) {
        return game.getBall().getCenterX() < 10;
    }

    // Method to check the end of the game
    public boolean checkEndOfGame(Game game) {
        int maxScore = Math.max(game.getPlayer1().getScore(), game.getPlayer2().getScore());
        return game.getScoreLimit() <= maxScore;
    }
    @Override
    public void run() {
        Ball ball = game.getBall();
        Racket racket1 = game.getRacket1();
        Racket racket2 = game.getRacket2();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        double initialSpeed = ball.getBallSpeed();

        //Ball increment function causing problems with player2 scoring!!!

        while(true)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(!game.isPaused()){
                ball.move(game.getGAME_HEIGHT());

                double distX1 = Math.abs(ball.getCenterX() - (racket1.getX() + racket1.getWidth() / 2));
                double distY1 = Math.abs(ball.getCenterY() - (racket1.getY() + racket1.getHeight() / 2));
                double distX2 = Math.abs(ball.getCenterX() - racket2.getX());
                double distY2 = Math.abs(ball.getCenterY() - (racket2.getY() + racket2.getHeight() / 2));

                // Check if the distances are less than the sum of the radii
                if (distX1 <= (racket1.getWidth() / 2) && distY1 <= (racket1.getHeight() / 2)) {
                    ball.reverseXDirection();
                    ball.incrementSpeed();
                }

                if (distX2 <= (racket2.getWidth()) && distY2 <= (racket2.getHeight() / 2)) {
                    ball.reverseXDirection();
                    ball.incrementSpeed();
                }

                if (ball.getCenterX() <=  0) {
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
        }    
    }
}
