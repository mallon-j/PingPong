package Control;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

import Model.*;
import View.*;

/**
 * The GameController class controls the flow of the game, including managing
 * scenes, game loop, and handling events.
 */
public class GameController {
    private Game game;
    private GameView gameView;
    private BallManager ballManager;
    private KeyboardListener keyboardListener;
    private Scene menuScene;
    private GameMenu gameMenu;
    private Stage stage;
    

    /**
     * Constructs a GameController object with the specified stage, game, menu
     * scene, and game menu.
     *
     * @param stage     The primary stage of the application
     * @param game      The game model
     * @param menuScene The scene for the main menu
     * @param gameMenu  The game menu view
     */
    public GameController(Stage stage, Game game, Scene menuScene, GameMenu gameMenu) {
        this.game = game;
        this.menuScene = menuScene;
        this.gameMenu = gameMenu;
        this.stage = stage;
        Group root = new Group();
        this.keyboardListener = new KeyboardListener(game);
        this.gameView = new GameView(root, game, this);
        Scene scene = new Scene(root, game.getGAME_WIDTH(), game.getGAME_HEIGHT(), Color.BLACK);
        
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

    /**
     * Resizes the game components based on the width and height factors.
     *
     * @param widthFactor  The factor by which the width is scaled
     * @param heightFactor The factor by which the height is scaled
     * @param stage        The primary stage
     */
    public void resizeGame(double widthFactor, double heightFactor, Stage stage) {
        resizeX(widthFactor);
        resizeY(heightFactor);
        game.setGameWidth(stage.getWidth());
        game.setGameHeight(stage.getHeight());
        gameView.resizeCanvas(widthFactor, heightFactor);
    }

    /**
     * Resizes the game components along the X-axis based on the given factor.
     *
     * @param factor The factor by which the X-axis is scaled
     */
    public void resizeX(double factor) {
        game.getRacket1().resizeX(factor);
        game.getRacket2().resizeX(factor);
        game.getBall().resizeX(factor);
    }

    /**
     * Resizes the game components along the Y-axis based on the given factor.
     *
     * @param factor The factor by which the Y-axis is scaled
     */
    public void resizeY(double factor) {
        game.getRacket1().resizeY(factor);
        game.getRacket2().resizeY(factor);
        game.getBall().resizeY(factor);
    }

    /**
     * Initiates the game loop and the ball manager thread.
     */
    public void startGame() {
        Thread thread = new Thread(ballManager);
        thread.start();
        new Thread(this::gameLoop).start();
        game.pause();
        
    }

    /**
     * The main game loop responsible for updating the game state.
     */
    private void gameLoop() {
        while (true) {
            if (!game.isPaused()) {
                Platform.runLater(() -> gameView.getGameMenu().hideGameMenu());
                keyboardListener.updateRacketPositions();
                Platform.runLater(() -> {
                    gameView.drawGame();
                    checkForWinner(); // Check if there's a winner
                });
            } else {
                Platform.runLater(() -> gameView.getGameMenu().showGameMenu());
            }
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if there is a winner in the game based on the score limit.
     * If a winner is found, ends the game.
     */
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

    /**
     * Restarts the game when requested.
     */
    private void restartGame() {
        game.restartGame();
        game.resume();
    }

    /**
     * Ends the game, displays an alert dialog indicating the winner, and provides
     * options to restart or return to the main menu.
     *
     * @param winner The player who won the game
     */
    private void endGame(Player winner) {
        game.pause();
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
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

    /**
     * Switches the scene to the main menu.
     */
    private void switchToMainMenu() {
        System.out.println("Switching to Main Menu...");
        System.out.println("Menu Scene Root: " + menuScene.getRoot());
        stage.setScene(menuScene);
    }

    public void loadGame() {

        TextInputDialog dialog = new TextInputDialog("game");
        dialog.setTitle("Load Game");
        dialog.setHeaderText("Enter the name of the saved game:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String fileName = result.get() + ".ser";
            Game loadedGame = GameSerializer.getInstance().deserialize(fileName);
            if (loadedGame == null) {
                System.out.println("Deserialization failed");
                return;
            }

            // Deserialize the game object

            // Update the game and gameView to reference the new game object
            this.game = loadedGame;
            this.gameView.setGame(loadedGame);

            // Update the keyboardListener to reference the new game object
            this.keyboardListener.setGame(loadedGame);

            this.keyboardListener.reset();

            // Update the ballManager to reference the new game object
            this.ballManager.setGame(loadedGame);

            this.gameView.resetView();
    
            game.getRacket1().printSpeed();

            // Redraw the game
            this.gameView.drawGame();
            game.resume();
            startGame();
        }
    }

    
}
