package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Player implements KeyboardHandler, Gravity {

    private Picture player;
    private int mapWidth;
    private int mapHeight;
    private int growX = 0;
    private int growY = 0;
    private boolean hitFloor;

    private String[] sprites = {"sprites/player/player_idle_right.png",
            "sprites/player/player_walk1.png",
            "sprites/player/player_walk2.png",
            "sprites/player/player_idle.png",};
    //"sprites/player/player_idle_left.png",};

    public Player(int mapWidth, int mapHeight) {
        player = new Picture(50, 355, "sprites/player/player_idle_right.png");
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void init() {
        player.draw();
        player.grow(0, 0);
        fall();
    }

    public int getX() {

        return player.getX();
    }

    public int getY() {

        return player.getY();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                if (player.getX() < Map.PADDING) {
                    return;
                }
                setMovementLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (player.getX() > this.mapWidth - player.getWidth()) {
                    return;
                }
                setMovementRight();
                break;
            case KeyboardEvent.KEY_SPACE:
                setMovementUp();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private void setMovementLeft() {

        player.translate(-10, 0);
        player.draw();
    }

    private void setMovementRight() {

        player.translate(10, 0);
        player.draw();
    }

    private void setMovementUp() {
        int maxHeithJump = 100;
        int initialY = player.getY();
        System.out.println("Jumping");
        while (player.getY() < initialY) {
            System.out.println(player.getY());
            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
            player.translate(getX(), gravity - 15);
            player.draw();
        }
    }

    public int getWidth() {
        return player.getWidth();
    }

    public int getHeight() {
        return player.getHeight() - growY;
    }

    public void fall() {
        //while (player.getY() + player.getHeight() <= mapHeight - 50) {//58 is tile height
        while (hitFloor) {
            // if((player.getY()+player.getHeight())
            //System.out.println("não cai");
            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }

            animation("FALLING");

            player.draw();
        }
        animation("IDLE");
        player.draw();
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

    public Picture getPlayer(){
        return player;
    }
}
