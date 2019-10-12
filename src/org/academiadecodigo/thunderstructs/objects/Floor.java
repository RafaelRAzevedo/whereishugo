package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor extends Map {

    private Picture floorTile;

    private int mapWidth;
    private int cellSize;
    private int grow;

    private Picture[] tiles;

    public Floor(int mapWidth) {
        this.mapWidth = mapWidth;
        grow = 100;

        floorTile = new Picture(Map.PADDING - 100, 351, "resources/sprites/blockTexture.png");
        floorTile.grow(-grow, -grow);

        cellSize = floorTile.getWidth();

        tiles = new Picture[mapWidth/cellSize+1];

    }

    public void init() {
        for (int i = 0; i < tiles.length; i ++) {
            tiles[i] = new Picture(i*cellSize-(grow-grow/10), 351, "resources/sprites/blockTexture.png");
            tiles[i].grow(-grow, -grow);
            tiles[i].draw();
        }
    }

    public void openTile() {

        System.out.println(mapWidth/cellSize);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

        int pos1X = tiles[5].getX();

        int pos2X = tiles[6].getX();

        tiles[5].delete();
        tiles[6].delete();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

        tiles[5] = new Picture(pos1X-grow, 351, "resources/sprites/blockTexture.png");
        tiles[6] = new Picture(pos2X-grow, 351, "resources/sprites/blockTexture.png");

        tiles[5].grow(-grow,-grow);
        tiles[6].grow(-grow,-grow);

        tiles[5].draw();
        tiles[6].draw();

    }


}

