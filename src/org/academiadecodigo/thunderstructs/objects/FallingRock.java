package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class FallingRock implements Gravity {

    private int mapHeight;
    private Picture rock;
    private int posX;
    private int posY;

    public FallingRock(int mapHeight, int mapWidth) {

        posX = (int) (Math.random() * (mapWidth - 200) + 130);  // 35 = Rock's width
        posY = Map.PADDING+5;

        rock = new Picture(posX, posY, "resources/sprites/finalStone.png");

        this.mapHeight = mapHeight;
    }

    public void init() {
        rock.draw();
    }

    @Override
    public void fall() {

        if (rock.getY() <= mapHeight - (rock.getHeight() + 50)) {
            rock.translate(0, Math.floor(3*Gravity.gravity/2));
            posY += 3*Gravity.gravity/2;
        } else {
            rock.translate(0, -mapHeight + (50 + Map.PADDING + rock.getHeight()));
            posY = Map.PADDING;
        }

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

}
