package Plants;

import Main.GameScreen;
import Scenes.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SunFlower extends Plants {

    public SunFlower(GameScreen gameScreen, int x, int y) {
        super(gameScreen, x, y);
        super.health = 60;
    }

    // drop sun per 10 seconds
    {
        timer = new Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Playing.suns.add(new Sun(x, y));
            }
        });
    }

    @Override
    public void attack() {
        this.timer.start();
        this.idle = false;
    }

    @Override
    public void stop() {
        this.timer.stop();
        this.idle = true;
    }

    @Override
    public boolean put(int x, int y, GameScreen gameScreen) {
        if(occ[x][y]==0){ //empty spot
            occ[x][y]= 1;
            Playing.plants.add(new SunFlower(gameScreen, x, y));
            return true;
        }else{
            return false;
        }
    }
}
