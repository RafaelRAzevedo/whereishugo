package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.FallingRock;
import org.academiadecodigo.thunderstructs.objects.Npc;
import org.academiadecodigo.thunderstructs.objects.Player;

class Game {


    private Npc npc;
    private Player player;
    private Map map;
    private KeyboardUtility keyboardUtility;
    private Music music;
    private Utility utility;
    private Screens screens;

    public static boolean restarted;

    Game() {

        map = new Map();
        player = new Player(map.getWidth(), map.getHeight());
        screens = new Screens();
        npc = new Npc();
        utility = new Utility(player,map, npc);
        keyboardUtility = new KeyboardUtility(this, player, utility);

        restarted = false;

    }

    void init() {
        keyboardUtility.keyboardEvents();
        screens.welcomeScreen();
    }

    void start() {
        restarted = true;
        Utility.getMap().init();
        Utility.drawFloor();

        music = new Music();
        music.startMusic("/resources/music/8BitCave.wav");

        player.init();

        for (FallingRock r : Utility.getRock()) {
            r.init();
        }

        npc.init();

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

            if (Utility.defeat) {
                music.stopMusic();
                music = new Music();
                music.startMusic("/resources/music/gameOver.wav");

                while (!Utility.reset) {
                    screens.gameOver();
                }

                music.stopMusic();
                player = new Player(map.getWidth(),map.getHeight());
                Utility.defeat = false;
                Utility.reset = false;

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }

                restarted = false;
                return;
            }

        }

        screens.winningScreen();
    }
}
