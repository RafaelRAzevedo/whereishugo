package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.Utility;

public class Rat_Enemy {

    private Picture rat;

    public Rat_Enemy(int posX){
       rat = new Picture(posX,485,"resources/sprites/Enemies/ratEnemyLeft.png");

    }

    public void init(){
        rat.draw();
    }

    public void ratMovement(){
        if (Utility.timeCounter(2) % 2 == 0) {
           rat.translate(2,0);
           rat.draw();
        } else {
            rat.translate(-2,0);
            rat.draw();
        }
    }

    public int getX(){
        return rat.getX();
    }

    public int getWidth(){
        return rat.getWidth();
    }

    public int getY(){
        return rat.getY();
    }

    public int getHeight(){
        return rat.getHeight();
    }

    public void gravity() {
        rat.translate(0, Gravity.gravity);
    }
}
