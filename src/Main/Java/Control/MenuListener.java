package Control;

import Model.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The MenuListener class handles actions triggered by menu items in the game menu.
 */
public class MenuListener {
    private Game game;

    /**
     * Constructs a MenuListener object with the specified game.
     *
     * @param game The game model
     */
    public MenuListener(Game game) {
        this.game = game;
    }

    /**
     * Exits the application.
     */
    public void setExit() {
        Platform.exit();
    }

    /**
     * Displays information about the game.
     */
    public void setAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Super Ping Pong");
        alert.setHeaderText("Developed By James Mallon");
        alert.setContentText("All rights reserved");
        alert.showAndWait().ifPresent((btnType) -> {});
    }

    /**
     * Sets the name of Player 1.
     */
    public void setPlayer1Name() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set Player 1 Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter Player 1 Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            game.setPlayer1Name(name);
            System.out.println("Player 1 Name set to: " + name);
        });
    }

    /**
     * Sets the name of Player 2.
     */
    public void setPlayer2Name() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Set Player 2 Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter Player 2 Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            game.setPlayer2Name(name);
            System.out.println("Player 2 Name set to: " + name);
        });
    }

    /**
     * Sets the score limit for the game.
     */
    public void setScoreLimit() {
        List<Integer> scoreOptions = Arrays.asList(10, 15, 20);
        ChoiceDialog<Integer> dialog = new ChoiceDialog<>(null, scoreOptions);
        dialog.setTitle("Set Score Limit");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose Score Limit:");

        Optional<Integer> result = dialog.showAndWait();
        result.ifPresent(scoreLimit -> {
            game.setScoreLimit(scoreLimit);
            System.out.println("Score Limit set to: " + scoreLimit);
        });
    }

    /**
     * Sets the size of the rackets.
     */
    public void setRacketSize() {
        List<String> racketOptions = Arrays.asList("Small", "Medium", "Large");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Medium", racketOptions);
        dialog.setTitle("Set Racket Size");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose Racket Size:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(racketSize -> {
            game.setRacketSize(racketSize);
            System.out.println("Racket size set to: " + racketSize);
        });
    }

    /**
     * Sets the speed of the ball.
     */
    public void setBallSpeed() {
        List<String> speedOptions = Arrays.asList("Slow", "Medium", "Fast");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Medium", speedOptions);
        dialog.setTitle("Set Ball Speed");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose Ball Speed:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ballSpeed -> {
            game.setBallSpeed(ballSpeed);
            System.out.println("Ball speed set to: " + ballSpeed);
        });
    }

    public void setSerializeGame() {
        TextInputDialog dialog = new TextInputDialog("game");
        dialog.setTitle("Save Game");
        dialog.setHeaderText("Enter a name for the saved game:");
        dialog.setContentText("Name:");
    
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            String fileName = result.get() + ".ser";
            GameSerializer.getInstance().serialize(game, fileName);
        }
    }

    public void setDeserializeGame() {
        
    
        
    }
}
