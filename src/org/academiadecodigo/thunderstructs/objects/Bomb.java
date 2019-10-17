package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.Music;
import org.academiadecodigo.thunderstructs.Utility;

public class Bomb {

    private Picture bomb;
    private Player player;
    private Rat_Enemy rat_enemy;
    private int counter;
    private boolean isBombDeployed;
    private Music sfxBomb;

    public Bomb(Player player, Rat_Enemy rat_enemy) {
        bomb = new Picture(player.getX(), player.getY() + player.getHeight() - 35, "resources/sprites/bomb.png");
        this.rat_enemy = rat_enemy;
        this.player = player;
        isBombDeployed = true;
        sfxBomb = new Music();
    }

    public boolean deployBomb() {
        counter++;
        if (counter > 250 && isBombDeployed) {
            counter = 0;
            bomb.delete();
            bomb = new Picture(bomb.getX() - 100, 410, "resources/sprites/explosion_1.png");
            bomb.draw();
            sfxBomb.startMusic("/resources/music/Bomb.wav");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
            damage(player);
            rat_enemy.damage(this);
            isBombDeployed = false;
            sfxBomb.stopMusic();
            bomb.delete();
            return false;
        }
        return isBombDeployed;
    }

    public boolean drawBomb() {
        bomb.draw();
        return true;
    }

    public void damage(Player player) {
        if (player.getX() > bomb.getX() && (player.getX() + player.getWidth() < bomb.getX() + bomb.getWidth())) {
                Utility.playerDead();
        }

    }

    public int getX(){
        return bomb.getX();
    }

    public int getWidth(){
        return bomb.getWidth();
    }

}

