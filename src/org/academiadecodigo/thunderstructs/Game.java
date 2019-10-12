package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.FallingRock;

class Game {

    private Map map;
    private Player player;
    private int numberOfRocks = 10;
    private FallingRock[] rock = new FallingRock[numberOfRocks];

    private boolean victory;

    private int victoryPosX;
    private int victoryPosY;

    Game() {

        map = new Map();
        player = new Player(map.getWidth());

        for (int i = 0; i < numberOfRocks; i++) {

            rock[i] = new FallingRock(map.getHeight(), map.getWidth());


        }

        victoryPosX = 950;
        victoryPosY = 300;
        victory = false;

    }

    void start() {

        //TODO: WELCOME SCREENS

        map.init();
        keyboardEvents();
        player.init();
        for (FallingRock r : rock) {
            r.init();
        }

        while (!victory) {
            int number = (int) (Math.random() * numberOfRocks);

            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }

            rock[number].fall();
            detectCollision();
            checkVictory();

        }

        //TODO: ENDING SCREENS

    }


    private void checkVictory() {

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
