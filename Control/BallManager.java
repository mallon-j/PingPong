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

                if (ball.intersects(racket1.getBoundsInLocal())) {
                    ball.reverseXDirection();
                 //   ball.incrementSpeed();
                }

                if (ball.intersects(racket2.getBoundsInLocal())) {
                    ball.reverseXDirection();
                   // ball.incrementSpeed();
                }

                if (ball.getCenterX() == 0) {
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
