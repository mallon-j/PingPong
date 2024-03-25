package Control;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Model.*;
import View.*;

public class GameController {
    private Game game;
    private GameView gameView;
    private BallManager ballManager;
    private KeyboardListener keyboardListener;
    private Scene menuScene;
    private GameMenu gameMenu;
    private Stage stage;

    public GameController(Stage stage, Game game, Scene menuScene, GameMenu gameMenu) {
        this.game = game;
        this.menuScene = menuScene;
        this.gameMenu = gameMenu;
        this.stage = stage;
        Group root = new Group();
        this.gameView = new GameView(root, game);
        Scene scene = new Scene(root, game.getGAME_WIDTH(), game.getGAME_HEIGHT(), Color.BLACK);
        this.keyboardListener = new KeyboardListener(game);
        scene.setOnKeyPressed(keyboardListener);
        scene.setOnKeyReleased(keyboardListener);
        stage.setScene(scene);
        stage.setTitle("Ping Pong Game");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.show();
        stage.widthProperty().addListener(observable -> {
            double factor = stage.getWidth() / game.getGAME_WIDTH();
            resizeGame(factor, stage.getHeight(), stage);
            gameView.drawGame();
        });

        stage.heightProperty().addListener(observable -> {
            double factor = stage.getHeight() / game.getGAME_HEIGHT();
            resizeGame(stage.getWidth(), factor, stage);
            gameView.drawGame();
        });
        this.ballManager = new BallManager(game, gameView);
        
        
    }

    public void resizeGame(double widthFactor, double heightFactor, Stage stage) {
        resizeX(widthFactor);
        resizeY(heightFactor);
        game.setGameWidth(stage.getWidth());
        game.setGameHeight(stage.getHeight());
        gameView.resizeCanvas(widthFactor, heightFactor);
    
    }

    public void resizeX(double factor) {
        game.getRacket1().resizeX(factor);
        game.getRacket2().resizeX(factor);
        game.getBall().resizeX(factor);
    }

    public void resizeY(double factor) {
        game.getRacket1().resizeY(factor);
        game.getRacket2().resizeY(factor);
        game.getBall().resizeY(factor);
    }

    private void gameLoop() {
        while (true) {
            if (!game.isPaused()) {
                keyboardListener.updateRacketPositions();
                Platform.runLater(() -> {
                    gameView.drawGame();
                    checkForWinner(); // Check if there's a winner
                });
            }
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void startGame(){
        Thread thread = new Thread(ballManager);
        thread.start();
        new Thread(this::gameLoop).start();
    }


    private void checkForWinner() {
        if (game.getPlayer1Score() >= game.getScoreLimit()) {
            endGame(game.getPlayer1());
            game.pause();
            System.out.println("Player1 wins");
        } else if (game.getPlayer2Score() >= game.getScoreLimit()) {
            game.pause();
            System.out.print("Player2 Wins");
            endGame(game.getPlayer2());
        }
    }

    private void restartGame(){
        game.restartGame();
        game.resume();
    }

    private void endGame(Player winner) {
        game.pause();
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.NONE);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("Game Over! " + winner.getName() + " wins!");
    
            // Create the buttons
            ButtonType restartButton = new ButtonType("Restart Game");
            ButtonType mainMenuButton = new ButtonType("Switch to Main Menu");
            alert.getButtonTypes().setAll(restartButton, mainMenuButton);
    
            // Handle button click events
            alert.setOnCloseRequest(e -> {
                if (alert.getResult() == restartButton) {
                    restartGame();
                } else if (alert.getResult() == mainMenuButton) {
                    switchToMainMenu();
                }
            });
    
            alert.showAndWait();
        });
    }
    private void switchToMainMenu() {
        System.out.println("Switching to Main Menu...");
        System.out.println("Menu Scene Root: " + menuScene.getRoot());
        stage.setScene(menuScene);
        //setupGameMenu();
    }

}
