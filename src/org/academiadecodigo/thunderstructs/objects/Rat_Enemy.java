package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.Utility;

public class Rat_Enemy {

    private Picture rat;
    private boolean isDead;
    private int posX;

    public Rat_Enemy(int posX) {
        this.posX = posX;
        rat = new Picture(posX, 503, "resources/sprites/Enemies/ratEnemyLeft.png");
    }

    public void init() {
        rat.draw();
    }

    public void ratMovement() {
        if (!isDead) {
            if (Utility.timeCounter(2) % 2 == 0) {
                rat.translate(2, 0);
                rat.draw();
            } else {
                rat.translate(-2, 0);
                rat.draw();
            }
        }
    }

    public void damage(Bomb bomb) {
        if (rat.getX() > bomb.getX() && (rat.getX() + rat.getWidth() < bomb.getX() + bomb.getWidth())) {
            rat.delete();
            isDead = true;
        }
    }

    public int getX() {
        return rat.getX();
    }

    public int getWidth() {
        return rat.getWidth();
    }

    public void moveRat(int posX) {
        rat.translate(posX, 0);
    }

    public boolean isDead(){
        return isDead;
    }

    public void setDead(boolean dead){
        this.isDead = dead;
    }

    public void setPosition(int x){
        rat.translate(posX-x,0);
    }
}
