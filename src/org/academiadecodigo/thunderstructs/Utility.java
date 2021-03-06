package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.*;

public class Utility {

    private static final long createdMillis = System.currentTimeMillis();
    private static int numberOfRocks = 10;

    public static boolean victory;
    public static boolean defeat;
    public static boolean start;
    public static boolean reset;

    public static boolean canMove;

    public static Floor floor;
    public static Map map;
    public static Player player;
    public static  Npc npc;

    private static Rat_Enemy rat_enemy;
    public static FallingRock[] rock = new FallingRock[numberOfRocks];
    public static Picture[] floorBlocks;
    public static Picture[] lavaBlock;
    public static Boolean[] isTilesDraw;

    public Utility(Player player, Map map, Npc npc, Rat_Enemy rat_enemy) {

        floor = new Floor(map.getWidth());

        for (int i = 0; i < numberOfRocks; i++) {
            rock[i] = new FallingRock(map.getHeight(), map.getWidth());
        }

        this.player = player;
        this.map = map;
        this.npc = npc;
        this.rat_enemy = rat_enemy;

        start = true;
        canMove = true;

        reset = false;
        victory = false;
        defeat = false;
        reset = false;
    }

    public static void checkVictory() {

        if (player.getX() >= npc.getPosX() - 50) {
            //System.out.println("WIN");
            setVictory();
        }
    }


    public static int timeCounter(double delay) {
        int seconds;
        double delayInSeconds = delay * 1000;
        long nowMillis = System.currentTimeMillis();
        seconds = (int) ((nowMillis - createdMillis) / delayInSeconds);
        return seconds;
    }

    public static void getFloorAnimation(int delay) {

        if (timeCounter(delay) % 2 == 0) {
            floorBlocks[4].draw();
            lavaBlock[4].delete();
            lavaBlock[4].draw();
            isTilesDraw[4] = true;
        } else {
            floorBlocks[4].delete();
            isTilesDraw[4] = false;
        }

        if (timeCounter(delay) % 4 == 0) {
            floorBlocks[10].draw();
            lavaBlock[10].delete();
            lavaBlock[10].draw();
            isTilesDraw[10] = true;
        } else {
            floorBlocks[10].delete();
            isTilesDraw[10] = false;
        }

        if (timeCounter(delay) % 3 == 0) {
            floorBlocks[8].draw();
            lavaBlock[8].delete();
            lavaBlock[8].draw();
            isTilesDraw[8] = true;
        } else {
            floorBlocks[8].delete();
            isTilesDraw[8] = false;
        }

    }

    public static void drawFloor() {
        floorBlocks = new Picture[floor.getTiles().length];

        //Check if floor is drawn or not.
        isTilesDraw = new Boolean[floor.getTiles().length];
        lavaBlock = new Picture[floor.getTiles().length];

        // Floor creation
        for (int i = 0; i < floor.getTiles().length; i++) {

            floorBlocks[i] = new Picture(i * floor.getTileSize() + Map.PADDING, map.getHeight() - 70, "resources/sprites/blockTexture.png");
            floorBlocks[i].draw();
            isTilesDraw[i] = true;
        }

        // Lava Creation
        for (int i = 0; i < floor.getTiles().length; i++) {

            lavaBlock[i] = new Picture(i * floor.getTileSize() + Map.PADDING, map.getHeight() - 40, "resources/sprites/lava_sprite.png");
            lavaBlock[i].draw();
        }
    }

    public static void detectCollision() {
        //Falling rocks
        for (int i = 0; i < rock.length; i++) {
            if (player.getX() < rock[i].getX() + rock[i].getWidth()
                    && player.getX() + player.getWidth() > rock[i].getX()
                    && player.getY() < rock[i].getY() + rock[i].getHeight()
                    && player.getY() + player.getHeight() > rock[i].getY()) {
                setDefeat();
            }
        }

        //Tiles
        for (int i = 0; i < floorBlocks.length - 1; i++) {
            if (player.getX() > floorBlocks[i].getX() && (player.getX() + player.getWidth() - 5 < floorBlocks[i].getX() + floorBlocks[i].getWidth())) {
                if (!isTilesDraw[i]) {

                    player.setHitFloor(false);
                    player.fall();
                    continue;
                }
                //Gravity
                while ((player.getY() + player.getHeight() < floorBlocks[i].getY())) {
                    player.fall();
                    canMove = false;
                }
            }
        }

        //rat collision
        if (!rat_enemy.isDead()) {
            if (player.getX() + player.getWidth() >= rat_enemy.getX() || player.getX() > rat_enemy.getX() + rat_enemy.getWidth()) {
                setDefeat();
            }
        }

        //if player falls
        if (player.getY() + player.getHeight() >= map.getHeight() - 10) {
            setDefeat();
        }

        //Winning place
        if (player.getX() == npc.getPosX() - 60) {
            victory = true;
        }
    }

    public static void detectFloor() {

        for (int i = 0; i < floorBlocks.length - 1; i++) {
            if (rat_enemy.getX() == floorBlocks[i].getX()) {
                if (i == 0) {
                    continue;
                }
                if (!isTilesDraw[i - 1]) {
                    rat_enemy.moveRat(2);
                }
            } else {
                if ((rat_enemy.getX() + rat_enemy.getWidth()) == floorBlocks[i].getX() + floorBlocks[i].getWidth()) {
                    if (i == floorBlocks.length) {
                        continue;
                    }
                    if (!isTilesDraw[i + 1]) {
                        rat_enemy.moveRat(-2);

                    }
                }
            }
            if (rat_enemy.getX() + rat_enemy.getWidth() >= map.getWidth() + Map.PADDING) {
                rat_enemy.moveRat(-2);
            }

        }
    }

    public static FallingRock[] getRock() {
        return rock;
    }

    public static Map getMap() {
        return map;
    }

    public static int getNumberOfRocks() {
        return numberOfRocks;
    }

    public static void setDefeat() {
        defeat = true;
    }

    public static void setVictory() {
        victory = true;
    }

    public static boolean isCanMove() {
        return canMove;
    }

    public static boolean isVictory() {
        return victory;
    }

    public static boolean isDefeat() {
        return defeat;
    }

    public static void setStart() {
        start = false;
    }

    public static void playerDead() {
        defeat = true;
    }

}
