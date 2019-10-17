package org.academiadecodigo.thunderstructs;

import jdk.jshell.execution.Util;
import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.FallingRock;
import org.academiadecodigo.thunderstructs.objects.Npc;
import org.academiadecodigo.thunderstructs.objects.Player;
import org.academiadecodigo.thunderstructs.objects.Rat_Enemy;

class Game {


    private Npc npc;
    private Player player;
    private Map map;
    private Rat_Enemy ratEnemy;
    private KeyboardUtility keyboardUtility;
    private Music music;
    private Utility utility;
    private Screens screens;

    public static boolean restarted;

    Game() {

        map = new Map();
        screens = new Screens();
        npc = new Npc();
        ratEnemy = new Rat_Enemy(400);
        player = new Player(map.getWidth(), map.getHeight(), ratEnemy);

        utility = new Utility(player, map, npc, ratEnemy);
        keyboardUtility = new KeyboardUtility(this, player, utility);
        restarted = false;

    }

    void init() {
        keyboardUtility.keyboardEvents();
        screens.welcomeScreen();
        Utility.getMap().init();
        Utility.drawFloor();
        player.init();
        npc.init();
        for (FallingRock r : utility.getRock()) {
            r.init();
        }
    }

    void start() {
        restarted = true;
        ratEnemy.init();

        music = new Music();
        music.startMusic("/resources/music/8BitCave.wav");
        while (!Utility.isVictory()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }

            int number = (int) (Math.random() * Utility.getNumberOfRocks());
            Utility.rock[number].fall();

            Utility.checkVictory();
            Utility.detectCollision();
            Utility.getFloorAnimation(2);
            player.checkPlayerMovement();
            ratEnemy.ratMovement();
            Utility.detectFloor();

            if (Utility.defeat) {

                music.stopMusic();
                music = new Music();
                music.startMusic("/resources/music/gameOver.wav");

                while (!Utility.reset) {

                    screens.gameOver();
                }
                restart();
            }
        }
        screens.winningScreen();

    }


    public void restart() {
        music.stopMusic();
        screens.cleanGameOver();

        player.setPosition(player.getX(), player.getY());
        ratEnemy.setPosition(ratEnemy.getX());
        Utility.defeat = false;
        Utility.reset = false;

        restarted = false;
        start();
    }
}
