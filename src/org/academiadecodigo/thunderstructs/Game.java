package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.FallingRock;
import org.academiadecodigo.thunderstructs.objects.Floor;
import org.academiadecodigo.thunderstructs.objects.Player;

class Game implements KeyboardHandler {

    private Map map;
    private Player player;
    private int numberOfRocks = 10;
    private FallingRock[] rock = new FallingRock[numberOfRocks];

    private Floor floor;

    private boolean victory, defeat, isStartScreen;
    private boolean start = true;
    private int victoryPosX;
    private int victoryPosY;

    private boolean canMove = true;

    //GameOver Position
    private int centerX;
    private int centerY;

    //time counter
    private final long createdMillis = System.currentTimeMillis();

    private Picture[] floorBlocks;
    private Picture[] lavaBlock;

    private Boolean[] isTilesDraw;

    private Music music;

    Picture welcomeScreen;


    Game() {

        map = new Map();
        player = new Player(map.getWidth(), map.getHeight());
        floor = new Floor(map.getWidth());
        centerX = map.getWidth() / 2;
        centerY = map.getHeight() / 2;

        for (int i = 0; i < numberOfRocks; i++) {
            rock[i] = new FallingRock(map.getHeight(), map.getWidth());
        }

        welcomeScreen = new Picture(0, 0, "resources/sprites/welcome.png");

        victoryPosX = 1650;
        victoryPosY = 300;
        victory = false;
        defeat = false;

    }

    void start() {

        keyboardEvents();
        System.out.println(start);

        while (start) {
            startScreen();
        }
        welcomeScreen.delete();

        //TODO: WELCOME SCREENS
        map.init();

        drawFloor();
        player.init();
        for (FallingRock r : rock) {
            r.init();
        }
        music = new Music();
        music.startMusic();

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
            getAnimation();
            player.checkPlayerMovement();

            if (defeat) {
                Picture gameOver = new Picture(centerX - 350, centerY - 220, "sprites/gameOver.png");
                gameOver.draw();
                return;
            }
        }
    }

    //TODO: ENDING SCREENS

    private void drawFloor() {
        //implementação do array de floor blocks no game em vez da Class Floor
        floorBlocks = new Picture[floor.getTiles().length];

        //Check if floor is drawn or not.
        isTilesDraw = new Boolean[floor.getTiles().length];
        lavaBlock = new Picture[floor.getTiles().length];

        // Floor creation
        for (int i = 0; i < floor.getTiles().length; i++) {

            floorBlocks[i] = new Picture(i * floor.getTileSize() + Map.PADDING, map.getHeight() - 70, "resources/sprites/blockTexture.png");
            floorBlocks[i].draw();
            isTilesDraw[i] = true;
        }

        // Lava Creation
        for (int i = 0; i < floor.getTiles().length; i++) {

            lavaBlock[i] = new Picture(i * floor.getTileSize() + Map.PADDING, map.getHeight() - 40, "resources/sprites/lava_sprite.png");
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
        Keyboard keyboard1 = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        KeyboardEvent right = new KeyboardEvent();
        KeyboardEvent space = new KeyboardEvent();
        KeyboardEvent up = new KeyboardEvent();

        KeyboardEvent leftRelease = new KeyboardEvent();
        KeyboardEvent rightRelease = new KeyboardEvent();
        KeyboardEvent upRelease = new KeyboardEvent();

        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        left.setKey(KeyboardEvent.KEY_LEFT);
        leftRelease.setKey(KeyboardEvent.KEY_LEFT);

        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);

        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        upRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        up.setKey(KeyboardEvent.KEY_UP);
        upRelease.setKey(KeyboardEvent.KEY_UP);

        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);


        if (canMove) {
            keyboard.addEventListener(right);
            keyboard.addEventListener(left);
        }

        keyboard.addEventListener(up);
        keyboard1.addEventListener(space);

        keyboard.addEventListener(leftRelease);
        keyboard.addEventListener(rightRelease);
        keyboard.addEventListener(upRelease);
    }

    public void startScreen() {
        int seconds;
        long nowMillis = System.currentTimeMillis();
        seconds = (int) ((nowMillis - this.createdMillis) / 800);
        if (seconds % 2 == 0) {
            welcomeScreen.draw();
        } else {
            welcomeScreen.delete();
        }
        isStartScreen = true;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            start = false;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
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
            if (player.getX() > floorBlocks[i].getX() && (player.getX() + player.getWidth() - 5 < floorBlocks[i].getX() + floorBlocks[i].getWidth())) {
                if (!isTilesDraw[i]) {

                    player.setHitFloor(false);
                    player.gravity();
                    continue;
                }
                //Gravity
                while ((player.getY() + player.getHeight() < floorBlocks[i].getY())) {
                    player.gravity();
                    canMove = false;
                }
            }
        }

        if (player.getY() + player.getHeight() >= map.getHeight()-10) {
            defeat = true;
        }
    }

    public int timeCounter() {
        int seconds;

        long nowMillis = System.currentTimeMillis();
        seconds = (int) ((nowMillis - this.createdMillis) / 2000);
        return seconds;
    }

    private void setDefeat() {
        defeat = true;
    }


    private void setVictory() {
        victory = true;
    }

    public void getAnimation() {

        if (timeCounter() % 2 == 0) {
            floorBlocks[4].draw();
            lavaBlock[4].delete();
            lavaBlock[4].draw();
            isTilesDraw[4] = true;
        } else {
            floorBlocks[4].delete();
            isTilesDraw[4] = false;
        }
        if (timeCounter() % 4 == 0) {
            floorBlocks[10].draw();
            lavaBlock[10].delete();
            lavaBlock[10].draw();
            isTilesDraw[10] = true;
        } else {
            floorBlocks[10].delete();
            isTilesDraw[10] = false;
        }

    }


}
