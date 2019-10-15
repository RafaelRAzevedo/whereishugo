package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Player implements KeyboardHandler {

    private Picture player;
    private int mapWidth;

    public Player(int mapWidth) {
        player = new Picture(50, 350, "sprites/player/player_walk3.png");
        this.mapWidth = mapWidth;
    }

    public void init() {
        player.draw();
        player.grow(0, 0);
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
                serMovingLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                setMovingRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void setMovingRight() {
        /*Picture[] animationSprites = {new Picture(getX(), getY(), "sprites/player/player_walk3.png"),
                new Picture(getX(), getY(), "sprites/player/player_walk1.png"),
                new Picture(getX(), getY(), "sprites/player/player_walk2.png")};

            for (int i = 1; i < 3; i++) {
                animationSprites[i-1].delete();
                animationSprites[i].translate(i * 10, 0);
                animationSprites[i].draw();
            }*/
        player.translate(10, 0);
    }

    public void serMovingLeft(){
        player.translate(-10,0);
    }

}
