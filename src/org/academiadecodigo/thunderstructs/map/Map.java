package org.academiadecodigo.thunderstructs.map;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Map {

    public static final int PADDING = 10;
    private Picture background;

    public Map() {
        background = new Picture(PADDING,PADDING,"sprites/background_1.jpg");
    }

    public void init() {
        background.draw();
        background.grow(400,400);
    }
}
