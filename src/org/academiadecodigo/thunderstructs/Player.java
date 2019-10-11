package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player {
    public static final int PADDING = 10;
    private Picture player;

    public Player() {
        player = new Picture(PADDING,PADDING,"sprites/background_1.jpg");
    }

    public void init() {
        player.draw();
        player.grow(0,0);
    }
}
