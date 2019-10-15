package org.academiadecodigo.thunderstructs.objects;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class FallingRock implements Gravity{

    private int mapHeight;
    private Picture rock;
    private int posX;
    private int posY;
    private final int grow;

    public FallingRock(int mapHeight, int mapWidth) {
        grow = 0;

        posX = (int) (Math.random() * (mapWidth - (grow + 60)));  // 60 = Rock's width
        posY = Map.PADDING-grow;

        rock = new Picture(posX, posY, "resources/sprites/finalStone.png");
        rock.grow(-grow, -grow);

        this.mapHeight = mapHeight;
    }

    public void init() {
        rock.draw();
    }


    @Override
    public void fall() {

        if (rock.getY() <= mapHeight - (rock.getHeight()+50)) {
            rock.translate(0, Gravity.gravity);
            posY += Gravity.gravity;
        } else {
            rock.translate(0, -mapHeight+rock.getHeight());
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

    public int getGrow(){
        return grow;
    }

}
