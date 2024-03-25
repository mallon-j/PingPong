package View;

import Control.MenuListener;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * The GameMenu class represents the menu of the game.
 */
public class GameMenu {
    private MenuBar menuBar;
    private MenuListener menuListener;
    private BorderPane root;
    private Button startButton;

    /**
     * Constructs a GameMenu object with the given menu listener.
     *
     * @param menuListener The menu listener for handling menu actions
     */
    public GameMenu(MenuListener menuListener) {
        this.menuListener = menuListener;
        initializeMenuBar();
    }

    /**
     * Initializes the menu bar with its menus and menu items.
     */
    private void initializeMenuBar() {
        menuBar = new MenuBar();
        root = new BorderPane();

        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> menuListener.setExit());
        fileMenu.getItems().addAll(exitMenuItem);

        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(event -> menuListener.setAbout());
        helpMenu.getItems().addAll(aboutMenuItem);

        // Players menu
        Menu playersMenu = new Menu("Players");
        MenuItem player1MenuItem = new MenuItem("Set Player 1 Name");
        player1MenuItem.setOnAction(event -> menuListener.setPlayer1Name());
        MenuItem player2MenuItem = new MenuItem("Set Player 2 Name");
        player2MenuItem.setOnAction(event -> menuListener.setPlayer2Name());
        playersMenu.getItems().addAll(player1MenuItem, player2MenuItem);

        // Score menu
        Menu scoreMenu = new Menu("Score");
        MenuItem scoreLimit = new MenuItem("Set Score Limit");
        scoreLimit.setOnAction(event -> menuListener.setScoreLimit());
        scoreMenu.getItems().add(scoreLimit);

        // Rackets menu
        Menu racketMenu = new Menu("Rackets");
        MenuItem racketSize = new MenuItem("Set Racket Size");
        racketSize.setOnAction(event -> menuListener.setRacketSize());
        racketMenu.getItems().add(racketSize);

        // Ball menu
        Menu ballMenu = new Menu("Ball");
        MenuItem ballSpeed = new MenuItem("Ball Speed");
        ballSpeed.setOnAction(event -> menuListener.setBallSpeed());
        ballMenu.getItems().add(ballSpeed);

        // Set up menu bar
        menuBar.getMenus().addAll(fileMenu, helpMenu, playersMenu, scoreMenu, racketMenu, ballMenu);
        root.setTop(menuBar);

        startButton = new Button("Start");
        startButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 40px;");
        root.setCenter(startButton);
    }

    /**
     * Retrieves the root BorderPane containing the menu and start button.
     *
     * @return The root BorderPane
     */
    public BorderPane getRoot() {
        return root;
    }

    /**
     * Retrieves the start button.
     *
     * @return The start button
     */
    public Button getStartButton() {
        return startButton;
    }
}
