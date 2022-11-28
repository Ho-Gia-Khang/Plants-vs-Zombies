package Plants;

import java.awt.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import javax.swing.*;
import Main.*;
import Scenes.Playing;
import UI.Point;

public abstract class Plants {
    protected int health;
    protected boolean idle = true, threaten = false;
    protected static int[][] occ = new int[5][10];
    protected static UI.Point[][] coor = new Point[5][9]; //array for plants coordinate
    protected int x, y;
    protected GameScreen gameScreen;

    protected Timer timer;

    public Plants(GameScreen gameScreen, int x, int y){
        this.gameScreen = gameScreen;
        this.x = x;
        this.y = y;
    }

    public abstract void attack();
    public void hit(int damage){
        health -= damage;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public abstract void stop();

    // getters
    public int getHealth(){
        return health;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isThreaten(){return threaten;}
    public static int getOcc(int x, int y){return occ[x][y];}
    public static UI.Point getCoor(int x, int y){return coor[x][y];}
    public boolean isIdle(){return idle;}
    public GameScreen getGameScreen(){
        return gameScreen;
    }

    //setter
    public static void setOcc(int i, int j){
        occ[i][j]=0;
    }
    public static void setCoor(int i, int j){
        coor[i][j] = new Point(296+j*81,117+i*98);
    }

    public abstract boolean put(int x, int y, GameScreen gameScreen);
    public void setThreat(boolean threat){
        threaten=threat;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(){
        this.y = y;
    }

}
