package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Player implements KeyboardHandler, Gravity {

    private Picture player;
    private int mapWidth;
    private int mapHeight;
    private boolean hitFloor;
    private Bomb bomb;
    private boolean deploy;
    private Rat_Enemy rat_enemy;

    private int velocityX;
    private int velocityY;
    private boolean left, right, up, jump, deployedBomb;

    private String[] sprites = {"sprites/player/player_idle_right.png",
            "sprites/player/player_walk1.png",
            "sprites/player/player_walk2.png",
            "sprites/player/player_idle.png",
            "sprites/player/player_idle_left.png",};

    public Player(int mapWidth, int mapHeight, Rat_Enemy rat_enemy) {
        player = new Picture(50, 445, "resources/sprites/characters/player-stand.png");
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.velocityX = 0;
        this.velocityY = 0;
        this.rat_enemy = rat_enemy;
    }

    public void init() {
        player.draw();
        player.grow(0, 0);
    }

    public int getX() {
        int x = player.getX() + 5;
        return x;
    }

    public int getY() {

        return player.getY();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                left = true;
                break;
            case KeyboardEvent.KEY_RIGHT:
                right = true;
                break;
            case KeyboardEvent.KEY_UP:
                up = true;
                break;
            case KeyboardEvent.KEY_B:
                deploy = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            /*case KeyboardEvent.KEY_UP:
                up = false;
                break;*/
            case KeyboardEvent.KEY_LEFT:
                //animation("LEFT");
                left = false;
                break;
            case KeyboardEvent.KEY_RIGHT:
                right = false;
                break;
        }
    }

    public void checkPlayerMovement() {
/*        if (up && !jump) {
            velocityY = -25;
            jump = true;
        }
        velocityY *= 0.90;

        if (velocityY == 0 && jump) {

            velocityY = 25;
            jump = false;

        }*/

        if (left && !right) {
            //velocityX = -3;
            velocityX = (player.getX() - Map.PADDING) >= Map.PADDING ? -3 : 0;
        }

        if (!left && right) {
            //velocityX = 5;
            velocityX = player.getX() + player.getWidth() <= mapWidth ? 3 : 0;
        }

        if (!right && !left) {
            velocityX = 0;
        }

        velocityY = 0;

        velocityX *= 0.90;
        player.translate(velocityX, velocityY);
        player.draw();

        if(deployedBomb){
            deploy = false;
           deployedBomb = bomb.deployBomb();
        }

        if (deploy) {
            bomb = new Bomb(this, rat_enemy);
            deployedBomb = bomb.drawBomb();
        }
    }

    public int getWidth() {
        return player.getWidth() - 3;
    }

    public int getHeight() {
        return player.getHeight();
    }

   /* public void animation(String animation) {
        switch (animation) {
            case "FALLING":
                player.delete();
                player = new Picture(getX(), getY(), "sprites/player/player_cheer2.png");
                break;
            case "LEFT":
                player = new Picture(getX(), getY(), sprites[4]);
                break;
        }
    }*/

    public void setHitFloor(boolean hitFloor) {
        this.hitFloor = hitFloor;
    }

    public void fall() {
        player.translate(0, Gravity.gravity);
    }

    public Picture getPlayer() {
        return player;
    }

    public void setPosition(int x, int y) {
        player.translate(50-x,445-y);
    }


}
