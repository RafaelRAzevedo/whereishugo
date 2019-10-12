package org.academiadecodigo.thunderstructs.objects;

interface Gravity {

    int gravity = 10;
    int terminalVelocity = 300;
    int verticalPosition = 0;

    void fall(int position);
}
