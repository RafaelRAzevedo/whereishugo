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
    private String[] sprites = {"sprites/player/player_idle.png",
            "sprites/player/player_walk1.png",
            "sprites/player/player_walk2.png",
            "sprites/player/player_idle.png"};

    public Player(int mapWidth, int mapHeight) {
        player = new Picture(50, 300, "sprites/player/player_idle.png");
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
        for (int i = 0; i < sprites.length; i++) {
            player.delete();
            player = new Picture(getX(), getY(), sprites[i]);
            player.translate(10, 0);
            player.draw();
        }
    }

    public int getWidth() {
        return player.getWidth() - growX;
    }

    public int getHeight() {
        return player.getHeight() - growY;
    }

    public void fall() {

        while (player.getY() <= (mapHeight - 170)) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }
            player.translate(0, Gravity.gravity);
            player.draw();
        }
    }

}