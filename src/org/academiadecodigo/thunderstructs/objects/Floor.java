package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor extends Map {

    private Picture floorTile;

    private int mapWidth;
    private int tileSize;
    private int grow;
    private int pos1X;
    private int pos2X;

    private boolean floorDeleted;


    private Picture[] tiles;

    public Floor(int mapWidth) {
        floorDeleted = false;

        this.mapWidth = mapWidth;
        grow = 100;

        floorTile = new Picture(Map.PADDING - 100, 351, "resources/sprites/blockTexture.png");
        floorTile.grow(-grow, -grow);

        tileSize = floorTile.getWidth();

        tiles = new Picture[mapWidth / tileSize + 1];

    }

    public void init() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Picture(i * tileSize - (grow - grow / 10), 351, "resources/sprites/blockTexture.png");
            tiles[i].grow(-grow, -grow);
            tiles[i].draw();
        }
    }


    public void openTile() {

        //System.out.println(tiles[5]);

        if (!floorDeleted) {

            pos1X = tiles[5].getX();
            pos2X = tiles[6].getX();

            tiles[5].delete();
            tiles[6].delete();

            System.out.println("DELETED");
            floorDeleted = true;

        } else {
            tiles[5] = new Picture(pos1X - grow, 351, "resources/sprites/blockTexture.png");
            tiles[6] = new Picture(pos2X - grow, 351, "resources/sprites/blockTexture.png");

            tiles[5].grow(-grow, -grow);
            tiles[6].grow(-grow, -grow);

            tiles[5].draw();
            tiles[6].draw();
            System.out.println("CREATED");
            floorDeleted = false;

        }

    }

    public int getTileSize() {
        return tileSize;
    }

    public boolean isFloorDeleted() {
        return floorDeleted;
    }

    public Picture[] getTiles() {
        return tiles;
    }

}

