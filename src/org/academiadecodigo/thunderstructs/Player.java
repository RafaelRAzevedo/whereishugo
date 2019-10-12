package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Player implements KeyboardHandler {

    private Picture player;
    private int mapWidth;


    public Player(int mapWidth) {
        player = new Picture(50,300,"sprites/player/player_idle.png");
        this.mapWidth = mapWidth;
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
                if(player.getX() < Map.PADDING){
                    return;
                }
                setMovementLeft();
                break;
            case KeyboardEvent.KEY_RIGHT:
                if(player.getX() > this.mapWidth - player.getWidth()){
                    return;
                }
                setMovementRight();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private void setMovementLeft(){
        player.translate(-10,0);
        player.draw();
    }

    private void setMovementRight(){
        player.translate(10,0);
        player.draw();
    }
}
