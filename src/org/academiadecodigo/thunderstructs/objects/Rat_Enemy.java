package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.Bomb;
import org.academiadecodigo.thunderstructs.Utility;

public class Rat_Enemy {

    private Picture rat;
    private boolean isDead;

    public Rat_Enemy(int posX) {
        rat = new Picture(posX, 510, "resources/sprites/Enemies/ratEnemyLeft.png");
    }

    public void init() {
        rat.draw();
    }

    public void ratMovement() {
        if (!isDead) {
            if (Utility.timeCounter(2) % 2 == 0) {
                rat.translate(1, 0);
                rat.draw();
            } else {
                rat.translate(-1, 0);
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

    public int getY() {
        return rat.getY();
    }

    public int getHeight() {
        return rat.getHeight();
    }

    public void moveRat(int posX) {
        rat.translate(posX, 0);
    }

    public boolean isDead(){
        return isDead;
    }
}
