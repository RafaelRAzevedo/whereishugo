package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Player implements KeyboardHandler {

    private Picture player;
    private int mapWidth;

    private String[] animationSprites = {
        "sprites/player/player_walk3.png",
       "sprites/player/player_walk1.png",
        "sprites/player/player_walk2.png"
    };

    public Player(int mapWidth) {
        player = new Picture(50, 300, "sprites/player/player_walk3.png");
        this.mapWidth = mapWidth;
    }

    public void init() {
        player.draw();
        player.grow(0,0);
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
                player.translate(-10, 0);
                player.draw();
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (player.getX() < this.mapWidth - player.getWidth())
                    movingRightAnimation();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void movingRightAnimation() {

        for (int i = 0; i < 3; i++) {
            player.delete();
            player = new Picture(getX(),getY(),animationSprites[i]);
            player.translate(i*10,0);
            player.draw();
        }
    }

}
