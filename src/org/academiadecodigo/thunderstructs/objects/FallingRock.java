package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class FallingRock implements Gravity {

    private int mapHeight;
    private Picture rock;
    private int posX;
    private int posY;

    public FallingRock(int mapHeight, int mapWidth) {
        posX = Map.PADDING;
        posY = (int) (Math.random() * mapWidth);

        rock = new Picture(posX, posY, "resources/block_sprite.png");
        rock.draw();

        this.mapHeight = mapHeight;
    }

    @Override
    public void fall(int position) {

        while (posY != mapHeight) {
            rock.translate(0, Gravity.gravity);
        }

    }
}
