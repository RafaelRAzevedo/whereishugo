package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Npc {

    private Picture npc;
    private int mapWidth;
    private int mapHeight;
    private int posX;
    private int posY;

    private String[] npcSprites = {
            "sprites/player/player_stand.png"
    };

    public Npc() {
        npc = new Picture(900, 445, "resources/sprites/characters/hugo.png");
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void init() {
        npc.draw();
        //npc.grow(0, 0);
        //talk();

    }
    public int getPosX() {
        return npc.getX();
    }

    public int getPosY(){
        return npc.getY();
    }




}