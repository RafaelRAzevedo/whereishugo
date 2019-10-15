package org.academiadecodigo.thunderstructs.objects;

import org.academiadecodigo.simplegraphics.graphics.Text;
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
        npc = new Picture(800, 450, "sprites/player/player_stand.png");
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void init() {
        npc.draw();
        //npc.grow(0, 0);
        //talk(); //Podemos meter talk animation idk

    }
    public int getPosX() {
        return npc.getX();
    }

    public int getPosY(){
        return npc.getY();
    }




}