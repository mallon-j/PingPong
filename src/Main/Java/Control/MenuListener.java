package Control;
import Model.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class MenuListener {
    private Game game;
    public MenuListener(Game game){
        this.game=game;
    }

    public void setExit() {
        Platform.exit();
    }

    public void setAbout() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Super Ping pong");
        alert.setHeaderText("Developed By James Mallon");
        alert.setContentText("All rights resereved");
        alert.showAndWait().ifPresent((btnType) -> {});
    }
    
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

    public void setRacketSize(){
        List<String> racketOptions = Arrays.asList("Small", "Medium", "Large");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("M", racketOptions);
        dialog.setTitle("Set Racket Size");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose Racket:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(racketSize -> {
            game.setRacketSize(racketSize);
            System.out.println("Racket size set to: " + racketSize);
        });
    }

    public void setBallSpeed(){
        List<String> speedOptions = Arrays.asList("Slow", "Medium", "Fast");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Medium", speedOptions);
        dialog.setTitle("Set Ball Speed");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose Speed:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(ballSpeed -> {
            game.setBallSpeed(ballSpeed);
            System.out.println("Ball speed set to: " + ballSpeed);
        });
    }



}