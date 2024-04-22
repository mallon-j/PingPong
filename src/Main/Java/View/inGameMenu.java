package View;

import Control.GameController;
import Control.MenuListener;
import Model.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class inGameMenu {
    private MenuBar menuBar;
    private MenuListener menuListener;
    private BorderPane root;
    Menu gameSaveMenu;
    Menu gameLoadMenu;
    private Game game;
    private GameController gameController;

    public inGameMenu(Game game, GameController gameController) {
        this.game = game;
        this.root = new BorderPane();
        this.gameController = gameController;
        menuListener = new MenuListener(game);
        initialiseGameMenu();

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(gameSaveMenu, gameLoadMenu);

        // Add the MenuBar to the root
        root.setTop(menuBar);
    }

    private void initialiseGameMenu() {

        gameSaveMenu = new Menu("Save Game");
        MenuItem gameSaveItem = new MenuItem("Save game to file");
        MenuItem dbSaveItem = new MenuItem("Save to Database");

        gameSaveItem.setOnAction(event -> menuListener.setSerializeGame());
        dbSaveItem.setOnAction(event -> menuListener.setAbout());
        gameSaveMenu.getItems().addAll(gameSaveItem, dbSaveItem);

        gameLoadMenu = new Menu("Load Game");
        MenuItem gameLoadItem = new MenuItem("Load game from file");
        MenuItem dbLoadItem = new MenuItem("Load from Database");

        gameLoadItem.setOnAction(event -> gameController.loadGame());
        dbLoadItem.setOnAction(event -> menuListener.setAbout());
        gameLoadMenu.getItems().addAll(gameLoadItem, dbLoadItem);

    }

    public BorderPane getRoot() {
        return root;
    }

    public void showGameMenu() {
        gameSaveMenu.setVisible(true);
        gameLoadMenu.setVisible(true);
    }

    public void hideGameMenu() {
        gameSaveMenu.setVisible(false);
        gameLoadMenu.setVisible(false);
    }
}