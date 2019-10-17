package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.thunderstructs.objects.Player;

public class KeyboardUtility implements KeyboardHandler {

    private Game game;
    private Player player;
    private Utility utility;

    public KeyboardUtility(Game game, Player player, Utility utility) {
        this.game = game;
        this.player = player;
        this.utility = utility;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            Utility.start = false;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            Utility.reset = true;
            Utility.defeat = false;
        }
    }

    public void keyboardEvents() {

        Keyboard keyboard = new Keyboard(player);
        Keyboard keyboard1 = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent space = new KeyboardEvent();
        KeyboardEvent b = new KeyboardEvent();
        KeyboardEvent r = new KeyboardEvent();

        KeyboardEvent leftRelease = new KeyboardEvent();
        KeyboardEvent rightRelease = new KeyboardEvent();
        KeyboardEvent upRelease = new KeyboardEvent();

        b.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        b.setKey(KeyboardEvent.KEY_B);

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        leftRelease.setKey(KeyboardEvent.KEY_LEFT);

        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);

        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        r.setKey(KeyboardEvent.KEY_R);
        r.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        if (utility.isCanMove()) {
            keyboard.addEventListener(right);
            keyboard.addEventListener(left);
        }

        keyboard1.addEventListener(space);
        keyboard1.addEventListener(r);
        keyboard.addEventListener(b);

        keyboard.addEventListener(leftRelease);
        keyboard.addEventListener(rightRelease);
        keyboard.addEventListener(upRelease);
    }

}
