package Plants;

import java.awt.*;
import java.awt.ActiveEvent;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import Main.*;

public abstract class Plants {
    protected int health = 50;
    private BufferedImage img = null;
    private int x, y;
    private GameScreen gameScreen;

    public Timer time;

    public Plants(GameScreen gameScreen, int x, int y){
        this.gameScreen = gameScreen;
        this.x = x;
        this.y = y;
        time = new Timer(0, (ActionEvent e) -> {});
    }

    public abstract void attack();
    public void hit(int damage){
        health -= damage;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public void stop(){
        time.stop();
    }

    public int getHealth(){
        return health;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public BufferedImage getImg(){
        return img;
    }

    public GameScreen getGameScreen(){
        return gameScreen;
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

    public void setImg(BufferedImage img){
        this.img = img;
    }

}
