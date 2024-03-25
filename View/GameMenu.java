package View;

import Control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class GameMenu {
    private MenuBar menuBar;
    private MenuListener menuListener;
    private BorderPane root;
    private Button startButton;

    public GameMenu(MenuListener menuListener) {
        this.menuListener = menuListener;
        initializeMenuBar();
    }

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

        Menu playersMenu = new Menu("Players");

        // Create the menu items for setting player names
        MenuItem player1MenuItem = new MenuItem("Set Player 1 Name");
        player1MenuItem.setOnAction(event -> menuListener.setPlayer1Name());

        MenuItem player2MenuItem = new MenuItem("Set Player 2 Name");
        player2MenuItem.setOnAction(event -> menuListener.setPlayer2Name());

        // Add the menu items to the "Players" menu
        playersMenu.getItems().addAll(player1MenuItem, player2MenuItem);

        Menu scoreMenu = new Menu("Score");
        MenuItem scoreLimit = new MenuItem("Set Score Limit");
        scoreLimit.setOnAction(event -> menuListener.setScoreLimit());
        scoreMenu.getItems().add(scoreLimit);

        Menu racketMenu = new Menu("Rackets");
        MenuItem racketSize = new MenuItem("Set Racket Size");
        racketSize.setOnAction(event -> menuListener.setRacketSize());
        racketMenu.getItems().add(racketSize);

        Menu ballMenu = new Menu("Ball");
        MenuItem ballSpeed = new MenuItem("Ball Speed");
        ballSpeed.setOnAction(event -> menuListener.setBallSpeed());
        ;
        ballMenu.getItems().add(ballSpeed);

        // Set up menu bar
        menuBar.getMenus().addAll(fileMenu, helpMenu, playersMenu, scoreMenu, racketMenu, ballMenu);
        root.setTop(menuBar);
        startButton = new Button("Start");
        startButton.setStyle(
                "-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 100px; -fx-pref-height: 40px;");
        root.setCenter(startButton);
        ;
    }

    public BorderPane getRoot() {
        return root;
    }

    public Button getStartButton() {
        return startButton;
    }
}