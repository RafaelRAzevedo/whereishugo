package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class FallingRock implements Gravity {

    private int mapHeight;
    private Picture rock;
    private int posX;
    private int posY;
    private final int grow = 0;

    public FallingRock(int mapHeight, int mapWidth) {

        posX = (int) (Math.random() * (mapWidth - grow - 60));
        posY = Map.PADDING-grow;

        rock = new Picture(posX, posY, "resources/sprites/rocktest.png");

        rock.grow(-grow, -grow);

        this.mapHeight = mapHeight;
    }

    public void init() {
        rock.draw();
        //System.out.println(rock.getWidth());
    }


    @Override
    public void fall() {
        if (rock.getY() <= mapHeight - rock.getHeight()-59) {
            rock.translate(0, Gravity.gravity);
        } else {
            rock.translate(0, -mapHeight+rock.getHeight());
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
        return rock.getWidth();
    }

    public int getHeight() {
        return rock.getHeight();
    }

    public int getGrow(){
        return grow;
    }

}
