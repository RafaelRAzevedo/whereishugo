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
    private int numberOfRocks = 10;
    private FallingRock[] rock = new FallingRock[numberOfRocks];

    private Floor floor;

    private boolean victory;
    private boolean defeat;
    private boolean isTrapOpen;

    private int victoryPosX;
    private int victoryPosY;

    //GameOver Position
    private int centerX;
    private int centerY;

    //time counter
    private final long createdMillis = System.currentTimeMillis();

    private Picture[] floorBlocks;
    private Picture[] lavaBlock;

    private Boolean[] isTilesDraw;
    private boolean start = true;

    Game() {

        map = new Map();
        player = new Player(map.getWidth(), map.getHeight());
        floor = new Floor(map.getWidth());
        centerX = map.getWidth()/2;
        centerY = map.getHeight()/2;

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

        keyboardEvents();

/*        while (start) {
            welcomeScreen();
        }*/

        //TODO: WELCOME SCREENS
        map.init();
        //floor.init();

        drawFloor();

        player.init();

        for (FallingRock r : rock) {
            r.init();
        }

        while (!victory) {
            int number = (int) (Math.random() * numberOfRocks);
            detectCollision();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }

            rock[number].fall();

            checkVictory();
            if (Math.random() > 0.99) {
                floor.openTile();
            }

            getAnimation();
            player.checkPlayerMovement();

            if (defeat) {
                Picture gameOver = new Picture(centerX-250,centerY-150,"sprites/gameOver.png");
                gameOver.draw();
                return;
            }
        }



    }

    //TODO: ENDING SCREENS

    private void drawFloor(){
        //implementação do array de floor blocks no game em vez da Class Floor
        floorBlocks = new Picture[floor.getTiles().length];

        //Verifição se o chão está draw() ou delete()
        isTilesDraw = new Boolean[floor.getTiles().length];

        lavaBlock = new Picture[floor.getTiles().length];

        for (int i = 0; i < floor.getTiles().length; i++) {
            //what the fuck is this?! : i * floor.getTileSize() - (100 - 100 / 10)
            floorBlocks[i] = new Picture(i * floor.getTileSize() - (100 - 100 / 10), 351, "resources/sprites/blockTexture.png");
            floorBlocks[i].grow(-100, -100);
            floorBlocks[i].draw();
            isTilesDraw[i] = true;
        }

        for (int i = 0; i < floor.getTiles().length; i++) {
            lavaBlock[i] = new Picture(i * floor.getTileSize() - (100 - 100 / 10), 400, "resources/sprites/lava_sprite.png");
            lavaBlock[i].grow(-100, -100);
            lavaBlock[i].draw();
        }
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
        KeyboardEvent space = new KeyboardEvent();
        KeyboardEvent leftRelease = new KeyboardEvent();
        KeyboardEvent rightRelease = new KeyboardEvent();
        KeyboardEvent spaceRelease = new KeyboardEvent();

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        spaceRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        left.setKey(KeyboardEvent.KEY_LEFT);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        space.setKey(KeyboardEvent.KEY_SPACE);

        leftRelease.setKey(KeyboardEvent.KEY_LEFT);
        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);
        spaceRelease.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(space);

        keyboard.addEventListener(leftRelease);
        keyboard.addEventListener(rightRelease);
        keyboard.addEventListener(spaceRelease);
    }

    private void detectCollision() {
        //Falling rocks
        for (int i = 0; i < rock.length; i++) {
            if ((player.getX() >= rock[i].getX())
                    && (player.getX() + player.getWidth() <= rock[i].getX() + rock[i].getWidth())) {
                if (player.getY() <= rock[i].getY() + rock[i].getHeight()) {
                    System.out.println("hit");
                    setDefeat();
                }
            }
        }

        //Tiles
        for (int i = 0; i < floorBlocks.length - 1; i++) {
            if (player.getX() > floorBlocks[i].getX() && (player.getX()+player.getWidth()-5 < floorBlocks[i].getX() + floorBlocks[i].getWidth())) {
                if (!isTilesDraw[i]) {
                    player.setHitFloor(false);
                    player.gravity();
                    continue;
                }
                player.setHitFloor(true);
            }
        }

        if (player.getY() + player.getHeight() >= map.getHeight()) {
            player.getPlayer().delete();
            defeat = true;
        }
    }

    private void welcomeScreen() {
        Picture welcomeScreen = new Picture(0,0, "resources/sprites/welcome.png");

        welcomeScreen.grow(-100,-200);
        welcomeScreen.draw();

    }

    private void setDefeat() {
        defeat = true;
    }


    private void setVictory() {
        victory = true;
    }

    public void getAnimation() {

        if(timeCounter() % 2 == 0){
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

    public int timeCounter(){
        int seconds;
        int lastSecond = 0;

        long nowMillis = System.currentTimeMillis();
        seconds = (int)((nowMillis - this.createdMillis) / 2000);
        return seconds;
    }
}
