package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler {
    public static final int PADDING = 10;
    private Picture player;


    public Player() {
        player = new Picture(50,300,"sprites/player/player_idle.png");
    }

    public void init() {
        player.draw();
        player.grow(0,0);
    }

    public int getX(){

        return player.getX();
    }

    public int getY(){

        return player.getY();
    }
    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_LEFT:
                player.translate(-10,0);
                player.draw();
                break;
            case KeyboardEvent.KEY_RIGHT:
                player.translate(10,0);
                player.draw();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
