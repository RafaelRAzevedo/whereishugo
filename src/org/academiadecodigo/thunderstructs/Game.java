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


        player = new Player();
        map = new Map();
        victoryPosX = 55;
        victoryPosY = 300;
        victory = false;

    }

    public void start() {

        //TODO: WELCOME SCREENS
        Keyboard keyboard = new Keyboard(player);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();

        map.init();

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);


        System.out.println(player.getX());
        System.out.println(player.getY());

        while(!victory) {

            player.init();
            if (player.getX() >= victoryPosX && player.getY() == victoryPosY) {

                System.out.println("WIN");
                victory = true;
            }
        }






        //TODO: ENDING SCREENS

    }

}
