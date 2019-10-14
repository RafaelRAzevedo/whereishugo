package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor extends Map {

    private Picture floorTile;
    private Picture lava;


    private int tileSize;


    //private boolean floorDeleted;

    private Picture[] tiles;


    public Floor(int mapWidth) {
        floorTile = new Picture( Map.PADDING, Map.PADDING, "resources/sprites/blockTexture.png");
        tileSize = floorTile.getWidth();
        tiles = new Picture[mapWidth / tileSize + 1];
    }

    public int getTileSize() {
        return tileSize;
    }

    public Picture[] getTiles() {
        return tiles;
    }


}

