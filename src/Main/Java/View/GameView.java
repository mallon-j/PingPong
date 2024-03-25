package View;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Model.*;

public class GameView {
    @SuppressWarnings("unused")
    private Group root;
    private Game game;
    private Canvas canvas;
    private GraphicsContext gc;
    private Label goalLabel;
    private Button pauseButton;

    public GameView(Group root, Game game) {
        this.root = root;
        this.game = game;
        canvas = new Canvas(game.getGAME_WIDTH(), game.getGAME_HEIGHT());
        gc = canvas.getGraphicsContext2D();
        initializeGoalLabel();
        initializePauseButton();
        root.getChildren().addAll(canvas, goalLabel, pauseButton);
    }

    private void initializeGoalLabel() {
        goalLabel = new Label();
        goalLabel.setFont(new Font("Arial", 18));
        goalLabel.setTextFill(Color.WHITE);
        goalLabel.setLayoutX(game.getGAME_WIDTH() / 2 - 50);
        goalLabel.setLayoutY(20);
        goalLabel.setVisible(false);
    }

    public void displayGoalMessage(String playerName) {
        Platform.runLater(() -> {
            goalLabel.setText("Goal " + playerName);
            goalLabel.setVisible(true);
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        goalLabel.setVisible(false);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    private void initializePauseButton() {
        pauseButton = new Button("Pause");
        pauseButton.setLayoutX(game.getGAME_WIDTH()/2 - 50);
        pauseButton.setLayoutY(60);
        pauseButton.setStyle(
    "-fx-background-color: rgba(255, 0, 0, 0.5); " +
    "-fx-text-fill: white; " +
    "-fx-font-size: 16px; " +
    "-fx-pref-width: 100px; " +
    "-fx-pref-height: 40px;");

        pauseButton.setOnAction(event -> {
            if (game.isPaused()) {
                game.resume();
                pauseButton.setText("Pause");
            } else {
                game.pause();
                pauseButton.setText("Resume");
            }
        });
    }

    public void drawGame() {
        Platform.runLater(() -> {
            clearCanvas();
            drawPlayerLabels();
            drawRackets();
            drawBall();
        });
    }

    private void clearCanvas() {
        gc.clearRect(0, 0, game.getGAME_WIDTH(), game.getGAME_HEIGHT());
    }

    private void drawPlayerLabels() {
        gc.setFont(new Font("Arial", 18));
        gc.setFill(Color.WHITE);
        gc.fillText(game.getPlayer1Name() + ": " + game.getPlayer1Score(), 20, 30);
        gc.fillText(game.getPlayer2Name() + ": " + game.getPlayer2Score(), game.getGAME_WIDTH() - 160, 30);
    }

    public void resizeCanvas(double width, double height) {
        canvas.setWidth(width);
        canvas.setHeight(height);
        game.setGameWidth(width);
        game.setGameHeight(height);
    }

    private void drawRackets() {
        gc.setFill(Color.BLUE);
        gc.fillRect(game.getRacket1().getX(), game.getRacket1().getY(), game.getRacket1().getWidth(),
                game.getRacket1().getHeight());
        gc.fillRect(game.getRacket2().getX(), game.getRacket2().getY(), game.getRacket2().getWidth(),
                game.getRacket2().getHeight());
    }

    private void drawBall() {
        gc.setFill(Color.RED);
        gc.fillOval(game.getBall().getCenterX(), game.getBall().getCenterY(), game.getBall().getRadius(),
                game.getBall().getRadius());
    }
}