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
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {
            Utility.reset = true;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void keyboardEvents() {

        Keyboard keyboard = new Keyboard(player);
        Keyboard keyboard1 = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent space = new KeyboardEvent();
        //KeyboardEvent up = new KeyboardEvent();
        KeyboardEvent r = new KeyboardEvent();

        KeyboardEvent leftRelease = new KeyboardEvent();
        KeyboardEvent rightRelease = new KeyboardEvent();
        KeyboardEvent upRelease = new KeyboardEvent();

        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        leftRelease.setKey(KeyboardEvent.KEY_LEFT);
        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);
        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        //up.setKey(KeyboardEvent.KEY_UP);
        //up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        upRelease.setKey(KeyboardEvent.KEY_UP);
        upRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        r.setKey(KeyboardEvent.KEY_R);
        r.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        if (utility.isCanMove()) {
            keyboard.addEventListener(right);
            keyboard.addEventListener(left);
        }

        //keyboard.addEventListener(up);
        keyboard1.addEventListener(space);
        keyboard1.addEventListener(r);

        keyboard.addEventListener(leftRelease);
        keyboard.addEventListener(rightRelease);
        keyboard.addEventListener(upRelease);
    }

}
