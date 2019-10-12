package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor extends Map {

    private Picture floorTile;

    private int mapWidth;
    private int cellSize;

    private Picture[] tiles;

    public Floor(int mapWidth) {
        this.mapWidth = mapWidth;
        floorTile = new Picture(Map.PADDING - 100, 351, "resources/sprites/blockTexture.png");
        floorTile.grow(-100, -100);
        cellSize = floorTile.getWidth();
        tiles = new Picture[33 * cellSize];
    }

    public void init() {
        tileFloor();
    }

    public void tileFloor() {

        System.out.println(cellSize);
        for (int i = 0; i < mapWidth; i += cellSize) {
            //tiles[i] = floorTile;
            tiles[i] = new Picture(i - 90, 351, "resources/sprites/blockTexture.png");
            tiles[i].grow(-100, -100);
            tiles[i].draw();
        }
    }

    public void openTile() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

        tiles[5].delete();
        tiles[6].delete();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }


        tiles[5].draw();
        tiles[6].draw();
    }


}

