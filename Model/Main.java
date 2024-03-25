package Model;

import javafx.application.Application;
import javafx.stage.Stage;
import Control.*;
import View.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game game = new Game();
        MenuListener menuListener = new MenuListener(game);
        GameMenu gameMenu = new GameMenu(menuListener);
        gameMenu.getRoot().setStyle("-fx-background-color: black;");
        Scene menuScene = new Scene(gameMenu.getRoot(), 600, 400);
        menuScene.setFill(Color.BLACK);

        stage.setTitle("Ping Pong Game");
        stage.setScene(menuScene);
        stage.show();
        

        gameMenu.getStartButton().setOnAction(event -> {
            game.instantiateGame();
            GameController gameController = new GameController(stage, game);
            gameController.startGame();
        });
        
    }
}