package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler {

    private Picture player;
    private int mapWidth;
    private int mapHeight;
    private int growX = 0;
    private int growY = 0;
    private boolean hitFloor;

    private int velocityX;
    private int velocityY;
    private boolean left, right, up, jump;
    private long createdMillis;

    private String[] sprites = {"sprites/player/player_idle_right.png",
            "sprites/player/player_walk1.png",
            "sprites/player/player_walk2.png",
            "sprites/player/player_idle.png",};
    //"sprites/player/player_idle_left.png",};

    public Player(int mapWidth, int mapHeight) {
        player = new Picture(50, 385, "sprites/player/player_idle_right.png");
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void init() {
        player.draw();
        player.grow(0, 0);
    }

    public int getX() {
        int x = player.getX() + 5;
        return x;
    }

    public int getY() {

        return player.getY();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                left = true;
                break;
            case KeyboardEvent.KEY_RIGHT:
                right = true;
                break;
            case KeyboardEvent.KEY_SPACE:
                up = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                up = false;
                break;
            case KeyboardEvent.KEY_LEFT:
                left = false;
                break;
            case KeyboardEvent.KEY_RIGHT:
                right = false;
                break;
        }
    }

    public void checkPlayerMovement() {
        if (up && !jump) {
            velocityY = -25;
            jump = true;
        }

        if (left && !right) {
            velocityX = -5;
        }

        if (!left && right) {
            velocityX = 5;
        }

        if (!right && !left) {
            velocityX = 0;
        }

        velocityY *= 0.90;


        if (velocityY == 0 && jump) {

                velocityY = 25;
                velocityY *= 0.90;
            System.out.println(velocityX);
                jump = false;

        }

        player.translate(velocityX, velocityY);
        player.draw();
    }

    public int getWidth() {
        return player.getWidth()-3;
    }

    public int getHeight() {
        return player.getHeight() - growY;
    }

    public void animation(String animation) {
        switch (animation) {
            case "FALLING":
                player.delete();
                player = new Picture(getX(), getY(), "sprites/player/player_cheer2.png");
                break;
            case "IDLE":
                player.delete();
                player = new Picture(getX(), getY(), sprites[0]);
                break;
        }
    }

    public void setHitFloor(boolean hitFloor) {
        this.hitFloor = hitFloor;
    }

    public void gravity() {
        player.translate(0, Gravity.gravity);
    }

    public Picture getPlayer() {
        return player;
    }


}
