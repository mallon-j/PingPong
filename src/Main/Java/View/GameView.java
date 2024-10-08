package View;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import Control.GameController;
import Control.KeyboardListener;
import Model.*;

/**
 * The GameView class represents the visual representation of the game.
 */
public class GameView {
    @SuppressWarnings("unused")
    private Group root;
    private Game game;
    private Canvas canvas;
    private GraphicsContext gc;
    private Label goalLabel;
    private Button pauseButton;
    private inGameMenu gameMenu;
    private Label pauseLabel;
    

    /**
     * Constructs a GameView object with the specified root group and game.
     *
     * @param root T
     * he root group of the scene
     * @param game The game model
     */
    public GameView(Group root, Game game, GameController gameController) {
        this.root = root;
        this.game = game;
        this.gameMenu = new inGameMenu(game, gameController);
        System.out.println(gameMenu.getRoot());
        canvas = new Canvas(game.getGAME_WIDTH(), game.getGAME_HEIGHT());
        gc = canvas.getGraphicsContext2D();
        initializeGoalLabel();
       // initializePauseButton();
        initializePauseLabel();
        root.getChildren().addAll(canvas, goalLabel, pauseLabel);
        root.getChildren().add(gameMenu.getRoot());
        gameMenu.hideGameMenu();
    }

    /**
     * Initializes the goal label.
     */
    private void initializeGoalLabel() {
        goalLabel = new Label();
        goalLabel.setFont(new Font("Arial", 18));
        goalLabel.setTextFill(Color.WHITE);
        goalLabel.setLayoutX(game.getGAME_WIDTH() / 2 - 50);
        goalLabel.setLayoutY(20);
        goalLabel.setVisible(false);
    }

    private void initializePauseLabel() {
        pauseLabel = new Label("Press space to pause");
        pauseLabel.setFont(new Font("Arial", 18));
        pauseLabel.setTextFill(Color.WHITE);
        pauseLabel.setOpacity(0.5); // Set the opacity to make the label feint
        pauseLabel.setLayoutX(game.getGAME_WIDTH() / 2 - 100);
        pauseLabel.setLayoutY(game.getGAME_HEIGHT() - 50);
    }

    /**
     * Displays a goal message indicating which player scored.
     *
     * @param playerName The name of the player who scored
     */
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

    /**
     * Initializes the pause button.
     */
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
                gameMenu.hideGameMenu();
                pauseButton.setText("Pause");
            } else {
                game.pause();
                gameMenu.showGameMenu();
                pauseButton.setText("Resume");
            }
        });
    }

    /**
     * Draws the game components on the canvas.
     */
    public void drawGame() {
        Platform.runLater(() -> {
            clearCanvas();
            drawPlayerLabels();
            drawRackets();
            drawBall();
            if(game.isPaused()){
                pauseLabel.setVisible(false);
                gameMenu.showGameMenu();
           }
           if(!game.isPaused()){
                pauseLabel.setVisible(true);
                gameMenu.hideGameMenu();
           }
        });
    }

    /**
     * Clears the canvas.
     */
    private void clearCanvas() {
        gc.clearRect(0, 0, game.getGAME_WIDTH(), game.getGAME_HEIGHT());
    }

    /**
     * Draws player labels showing their scores.
     */
    private void drawPlayerLabels() {
        gc.setFont(new Font("Arial", 18));
        gc.setFill(Color.WHITE);
        gc.fillText(game.getPlayer1Name() + ": " + game.getPlayer1Score(), 20, 30);
        gc.fillText(game.getPlayer2Name() + ": " + game.getPlayer2Score(), game.getGAME_WIDTH() - 160, 30);
    }

    /**
     * Resizes the canvas to the specified width and height.
     *
     * @param width  The new width of the canvas
     * @param height The new height of the canvas
     */
    public void resizeCanvas(double width, double height) {
        canvas.setWidth(width);
        canvas.setHeight(height);
        game.setGameWidth(width);
        game.setGameHeight(height);
    }

    /**
     * Draws the rackets on the canvas.
     */
    private void drawRackets() {

        gc.setFill(Color.BLUE);
        gc.fillRect(game.getRacket1().getX(), game.getRacket1().getY(), game.getRacket1().getWidth(),
                game.getRacket1().getHeight());
        gc.fillRect(game.getRacket2().getX(), game.getRacket2().getY(), game.getRacket2().getWidth(),
                game.getRacket2().getHeight());
                
    }

    /**
     * Draws the ball on the canvas.
     */
    private void drawBall() {
        gc.setFill(Color.RED);
        gc.fillOval(game.getBall().getCenterX(), game.getBall().getCenterY(), game.getBall().getRadius(),
                game.getBall().getRadius());
    }

    public void setGame(Game loadedGame) {
        this.game = loadedGame;
    }


    public void resetView(){
        clearCanvas();

    }

    public inGameMenu getGameMenu(){
        return this.gameMenu;
    }
    

    

}
