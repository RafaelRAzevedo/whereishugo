package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.thunderstructs.map.Map;
import org.academiadecodigo.thunderstructs.objects.FallingRock;
import org.academiadecodigo.thunderstructs.objects.Npc;
import org.academiadecodigo.thunderstructs.objects.Player;

class Game {


    private Npc npc;
    private Player player;
    private Map map;
    private KeyboardUtility keyboardUtility;

    private Screens screens;
    private Utility utility;

    private boolean reset;
    private boolean start = true;

    private Music music;


    Game() {

        map = new Map();
        player = new Player(map.getWidth(), map.getHeight());
        screens = new Screens();
        npc = new Npc();
        utility = new Utility(player,map, npc);
        keyboardUtility = new KeyboardUtility(this, player, utility);

    }

    void start() {
        keyboardUtility.keyboardEvents();
        screens.welcomeScreen();

        //TODO: WELCOME SCREENS
        utility.getMap().init();
        npc.init();

        utility.drawFloor();
        player.init();
        for (FallingRock r : utility.getRock()) {
            r.init();
        }
        music = new Music();
        music.startMusic("/resources/music/8BitCave.wav");

        while (!utility.isVictory()) {

            int number = (int) (Math.random() * utility.getNumberOfRocks());
            utility.checkVictory();
            utility.detectCollision();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }

            utility.getRock()[number].fall();

            utility.getFloorAnimation(2);
            player.checkPlayerMovement();

            if (utility.isDefeat()) {
                music.stopMusic();
                music = new Music();
                music.startMusic("/resources/music/gameOver.wav");


                while (!reset) {
                    screens.gameOver();
                }
                return;
            }

        }

        screens.winningScreen();
        //Winning Screen
    }
}
