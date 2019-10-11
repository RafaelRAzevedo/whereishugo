package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.thunderstructs.map.Map;

class Game {

    private Map map;
    private Player player;
    private boolean victory;

    private int victoryPosX;
    private int victoryPosY;

    Game() {

        player = new Player();
        map = new Map();

        victoryPosX = 55;
        victoryPosY = 300;
        victory = false;

    }

    void start() {

        //TODO: WELCOME SCREENS

        map.init();
        keyboardEvents();

        while (!victory) {
            detectCollision();
            checkVictory();
        }
        //TODO: ENDING SCREENS

    }


    private void checkVictory() {

        player.init();

        if (player.getX() >= victoryPosX && player.getY() == victoryPosY) {
            System.out.println("WIN");
            setVictory();
        }

    }

    private void keyboardEvents() {

        Keyboard keyboard = new Keyboard(player);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);

    }

    private void detectCollision() {

    }



    private void setVictory() {
        victory = true;
    }

}
