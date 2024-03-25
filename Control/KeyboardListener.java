package Control;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Model.*;

public class KeyboardListener implements EventHandler<KeyEvent> {
    private Game game;
    private boolean isUpPressed = false;
    private boolean isDownPressed = false;
    private boolean isAPressed = false;
    private boolean isZPressed = false;

    public KeyboardListener(Game game) {
        this.game = game;
    }

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