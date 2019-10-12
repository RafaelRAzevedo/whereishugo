package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class FallingRock implements Gravity {

    private int mapHeight;
    private Picture rock;
    private int posX;
    private int posY;

    public FallingRock(int mapHeight, int mapWidth) {
        posX = (int) (Math.random() * (mapWidth - 430)) + 80;
        posY = Map.PADDING;

        rock = new Picture(posX, posY, "resources/sprites/stone.png");

        rock.grow(-370, -370);

        this.mapHeight = mapHeight;
    }

    public void init() {
        rock.draw();
    }


    @Override
    public void fall() {
        if (rock.getY() <= mapHeight - rock.getHeight()) {
            rock.translate(0, Gravity.gravity);
        } else {
            rock.translate(0, -mapHeight);
        }


        // while (posY != mapHeight) {

        //}

    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public int getWidth() {
        return this.getWidth();
    }

    public int getHeight() {
        return this.getHeight();
    }

}
