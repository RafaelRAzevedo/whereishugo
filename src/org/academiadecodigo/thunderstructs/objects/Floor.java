package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor extends Map {

    private int tileSize;
    private Picture floorTile;
    private Picture[] tiles;


    public Floor(int mapWidth) {
        floorTile = new Picture( Map.PADDING, Map.PADDING, "resources/sprites/blockTexture.png");
        tileSize = floorTile.getWidth();
        tiles = new Picture[mapWidth / tileSize];
    }

    public int getTileSize() {
        return tileSize;
    }

    public Picture[] getTiles() {
        return tiles;
    }


}

