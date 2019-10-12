package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor extends Map {

    private Picture floorTile;

    private int mapWidth;
    private int cellsize;

    private Picture[] tiles;

    public Floor(int mapWidth) {
        this.mapWidth = mapWidth;
        floorTile = new Picture(Map.PADDING - 100, 351, "resources/sprites/blockTexture.png");
        floorTile.grow(-100, -100);
        cellsize = floorTile.getWidth();
        tiles = new Picture[33 * cellsize];
    }

    public void init() {
        tileFloor();
    }

    public Picture getPicture() {
        return this.floorTile;
    }

    public void tileFloor() {

        System.out.println(cellsize);
        for (int i = 0; i < mapWidth; i += cellsize) {
            //tiles[i] = floorTile;
            tiles[i] = new Picture(i - 90, 351, "resources/sprites/blockTexture.png");
            tiles[i].grow(-100, -100);
            tiles[i].draw();
            //System.out.println(floorTile.getY());
        }
        //floorTile.translate(20,0);
        //floorTile.draw();floorTile.draw();
    }


}

