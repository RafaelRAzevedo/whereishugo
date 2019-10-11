package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.map.Map;

public class Game {

    private Map map = new Map();
    private  Player player = new Player();

    public Game() {



    }

    public void start() {
        map.init();
        player.init();


    }

}
