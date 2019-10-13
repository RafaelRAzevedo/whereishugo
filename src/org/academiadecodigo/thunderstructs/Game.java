package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.FallingRock;
import org.academiadecodigo.thunderstructs.objects.Floor;
import org.academiadecodigo.thunderstructs.objects.Player;

class Game {

    private Map map;
    private Player player;
    private int numberOfRocks = 15;
    private FallingRock[] rock = new FallingRock[numberOfRocks];

    private Floor floor;

    private boolean victory;
    private boolean defeat;
    private boolean isTrapOpen;

    private int victoryPosX;
    private int victoryPosY;

    //time counter
    private int seconds;
    private int lastSecond = 0;
    private final long createdMillis = System.currentTimeMillis();

    Picture[] floorBlocks;
    private Boolean[] isTilesDraw;

    Game() {

        map = new Map();
        player = new Player(map.getWidth(), map.getHeight());
        floor = new Floor(map.getWidth());

        for (int i = 0; i < numberOfRocks; i++) {
            rock[i] = new FallingRock(map.getHeight(), map.getWidth());
            //System.out.println(floorBlocks[i]);
        }

        victoryPosX = 1650;
        victoryPosY = 300;
        victory = false;
        defeat = false;
    }

    void start() {

        //TODO: WELCOME SCREENS
        map.init();
        //floor.init();

        //implementação do array de floor blocks no game em vez da Class Floor
        floorBlocks = new Picture[floor.getTiles().length];
        isTilesDraw = new Boolean[floor.getTiles().length];

        for (int i = 0; i < floor.getTiles().length; i++) {
            floorBlocks[i] = new Picture(i * floor.getTileSize() - (100 - 100 / 10), 351, "resources/sprites/blockTexture.png");
            floorBlocks[i].grow(-100, -100);
            floorBlocks[i].draw();
            isTilesDraw[i] = true;
        }

        player.init();

        keyboardEvents();
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
            if (Math.random() > 0.99) {
                floor.openTile();
            }

            getAgeInSeconds();
        }

        if (defeat) {
            System.out.println("wasted");
            //break;
            return;
        }

    }

    //TODO: ENDING SCREENS

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
        KeyboardEvent space = new KeyboardEvent();

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        // space.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        space.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(space);
    }

    private void detectCollision() {
        for (int i = 0; i < rock.length; i++) {
            if ((player.getX() >= rock[i].getX())
                    && (player.getX() + player.getWidth() <= rock[i].getX() + rock[i].getWidth())) {
                //   System.out.println(rock[i].getY());
                if (player.getY() <= rock[i].getY() + rock[i].getHeight()) {
                    setDefeat();
                }
            }
        }
        for (int i = 0; i < floorBlocks.length - 1; i++) {
            // if ((player.getX() >= floorBlocks[i].getX())) {
            if (player.getX() > floorBlocks[i].getX() && (player.getX() < floorBlocks[i].getX()+floorBlocks[i].getWidth())) {
                if (!isTilesDraw[i]) {
                    //System.out.println("cair");
                    player.setHitFloor(false);
                    player.gravity();
                    player.fall();
                }
                player.setHitFloor(true);
            }
        }
        if (player.getY() + player.getHeight() >= map.getHeight()) {
            //System.out.println("Dead");
            defeat = true;
        }
    }

    private void setDefeat() {
        defeat = true;
    }


    private void setVictory() {
        victory = true;
    }

    public void getAgeInSeconds() {
        long nowMillis = System.currentTimeMillis();
        seconds = (int)((nowMillis - this.createdMillis) / 1000);

        if(seconds == lastSecond){
            return;
        }

        if(seconds % 4 == 0){
            floorBlocks[5].draw();
            isTilesDraw[5] = true;
            floorBlocks[6].draw();
            isTilesDraw[6] = true;
        }
        else{
            floorBlocks[5].delete();
            isTilesDraw[5] = false;
            floorBlocks[6].delete();
            isTilesDraw[6] = false;
        }
    }

}
