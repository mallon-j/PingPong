package Model;

import javafx.application.Application;
import javafx.stage.Stage;
import Control.*;
import Control.GameController;
import Control.MenuListener;
import View.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * The Main class serves as the entry point for the Ping Pong Game application.
 * It extends the Application class from JavaFX, providing the start method
 * required for launching the application.
 * <p>
 * This class initializes the game environment, creates the main menu, and
 * handles the transition to the game scene upon user interaction.
 * </p>
 *
 * @author James Mallon
 */
public class Main extends Application {
    
    /**
     * The main method serves as the entry point for the Java application.
     * It launches the JavaFX application by calling the launch method with
     * the provided arguments.
     *
     * @param args the command-line arguments passed to the Java application
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * The start method is called when the JavaFX application is launched.
     * It sets up the initial stage, creates the game environment, and displays
     * the main menu.
     *
     * @param stage the primary stage for the JavaFX application
     * @throws Exception if an exception occurs during application startup
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Initialize the game environment
        Game game = new Game();
        // Create a menu listener for handling menu events
        MenuListener menuListener = new MenuListener(game);
        // Create the main game menu
        GameMenu gameMenu = new GameMenu(menuListener);
        // Set background color for the menu
        gameMenu.getRoot().setStyle("-fx-background-color: black;");
        // Create the scene for the main menu
        Scene menuScene = new Scene(gameMenu.getRoot(), 600, 400);
        menuScene.setFill(Color.BLACK);

        // Set the title and scene for the primary stage
        stage.setTitle("Ping Pong Game");
        stage.setScene(menuScene);
        stage.show();

        // Handle the start button action
        gameMenu.getStartButton().setOnAction(event -> {
            // Instantiate the game environment
            game.instantiateGame();
            // Create a game controller for managing the game flow
            GameController gameController = new GameController(stage, game, menuScene, gameMenu);
            // Start the game
            gameController.startGame();
            // Resume the game
            game.resume();
        });
    }
}
