package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.thunderstructs.map.Map;

public class Game {

    private Map map;
    private Player player;
    private boolean victory;

    private int victoryPosX;
    private int victoryPosY;

    public Game() {

        map = new Map();
        victoryPosX = 500;
        victoryPosY = 300;
        victory = false;

    }

    public void start() {

        //TODO: WELCOME SCREENS

        map.init();
        player.init();

        while (!victory) {

            player.move();


            if(player.getX() == victoryPosX && player.getY() == victoryPosY) {
                victory = true;
            }
        }

        Keyboard keyboard = new Keyboard(player);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);

        //TODO: ENDING SCREENS

    }

}
