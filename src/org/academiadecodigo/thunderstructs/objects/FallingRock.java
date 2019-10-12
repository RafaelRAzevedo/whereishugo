package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class FallingRock implements Gravity {

    private int mapHeight;
    private Picture rock;
    private int posX;
    private int posY;
    private final int grow = 370;

    public FallingRock(int mapHeight, int mapWidth) {

        posX = (int) (Math.random() * (mapWidth - grow - 60));
        posY = Map.PADDING-grow;

        rock = new Picture(posX, posY, "resources/sprites/stone.png");

        rock.grow(-grow, -grow);

        this.mapHeight = mapHeight;
    }

    public void init() {
        rock.draw();
        //System.out.println(rock.getWidth());
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
