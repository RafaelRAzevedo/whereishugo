package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Floor {

    private Picture floorTile;

    private int mapWidth;
    private int mapHeight;
    private int posX;
    private int posY;

    private Floor[] tiles;

    public Floor(){
        floorTile = new Picture(Map.PADDING - 100,351,"resources/sprites/blockTexture.png");
        floorTile.grow(-100,-100);
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void init() {
        tileFloor();
    }

    public Picture getPicture(){
        return this.floorTile;
    }

    public void tileFloor() {
        //for (int i = 0; i > -1; i++){
            floorTile.draw();
        //}


    }
}
