package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.thunderstructs.map.Map;

public class Screens {

    private Picture welcomeButtonStart;
    private Picture welcomeScreen;
    private Picture winningScreen;
    private Picture instruction1;
    private Picture instruction2;
    private Picture instructionStartButton;
    private Picture gameOver;
    private Picture gameOverFace;

    public Screens() {

        welcomeButtonStart = new Picture(Map.PADDING, Map.PADDING, "resources/sprites/welcome.png");
        welcomeScreen = new Picture(Map.PADDING, Map.PADDING, "resources/sprites/welcomeScreen.png");
        instruction1 = new Picture(Map.PADDING,Map.PADDING, "resources/sprites/instructions1.png");
        instruction2 = new Picture(Map.PADDING,Map.PADDING,"resources/sprites/instructions2.png");
        instructionStartButton = new Picture(Map.PADDING,Map.PADDING,"resources/sprites/spaceToContinue.png");
        gameOverFace = new Picture(Map.PADDING, Map.PADDING + 10, "sprites/gameOverBg.png");
        gameOver = new Picture(Map.PADDING, Map.PADDING, "sprites/gameOverTxt.png");
    }

    public void welcomeScreen() {

        welcomeScreen.draw();

        while (Utility.start) {
            startScreen();
        }

        welcomeButtonStart.delete();
        welcomeScreen.delete();
        Utility.start = true;

        while(Utility.start){
            instruction1.draw();
            if (Utility.timeCounter(0.5) % 2 == 0) {
                instructionStartButton.draw();
            } else {
                instructionStartButton.delete();
            }
        }
        instruction1.delete();
        Utility.start = true;

        while (Utility.start){
            instruction2.draw();
            if (Utility.timeCounter(0.5) % 2 == 0) {
                instructionStartButton.draw();
            } else {
                instructionStartButton.delete();
            }
        }

    }

    public void startScreen() {

        if (Utility.timeCounter(0.5) % 2 == 0) {
            welcomeButtonStart.draw();
        } else {
            welcomeButtonStart.delete();
        }
    }

    public void winningScreen() {
        winningScreen = new Picture(Map.PADDING, Map.PADDING, "resources/sprites/win_screen.png");
        winningScreen.draw();
    }

    public void gameOver() {

        gameOverFace.draw();
        if (Utility.timeCounter(0.5) % 2 == 0) {
            gameOver.draw();
        } else {
            gameOver.delete();
        }
    }
}
