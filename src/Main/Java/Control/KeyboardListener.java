package Control;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Model.*;

/**
 * The KeyboardListener class handles keyboard input for controlling the rackets in the game.
 */
public class KeyboardListener implements EventHandler<KeyEvent> {
    private Game game;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isAPressed = false;
    private boolean isZPressed = false;

    /**
     * Constructs a KeyboardListener object with the specified game.
     *
     * @param game The game model
     */
    public KeyboardListener(Game game) {
        this.game = game;
    }

    /**
     * Handles keyboard events for moving the rackets up and down.
     *
     * @param keyEvent The KeyEvent representing the keyboard event
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (keyCode == KeyCode.K) {
            isUpPressed = keyEvent.getEventType() == KeyEvent.KEY_PRESSED;
        } else if (keyCode == KeyCode.M) {
            isDownPressed = keyEvent.getEventType() == KeyEvent.KEY_PRESSED;
        } else if (keyCode == KeyCode.A) {
            isAPressed = keyEvent.getEventType() == KeyEvent.KEY_PRESSED;
        } else if (keyCode == KeyCode.Z) {
            isZPressed = keyEvent.getEventType() == KeyEvent.KEY_PRESSED;
        }
    }

    /**
     * Updates the positions of the rackets based on the keyboard input.
     */
    public void updateRacketPositions() {
        Racket racket1 = game.getRacket1();
        Racket racket2 = game.getRacket2();

        if (isAPressed) {
            racket1.moveUp();
        } else if (isZPressed) {
            racket1.moveDown(game.getGAME_HEIGHT());
        }

        if (isUpPressed) {
            racket2.moveUp();
        } else if (isDownPressed) {
            racket2.moveDown(game.getGAME_HEIGHT());
        }
    }
}
