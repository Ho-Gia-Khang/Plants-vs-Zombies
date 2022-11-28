package Plants;

import Main.GameScreen;

import javax.swing.*;

import UI.Point;
import Scenes.Playing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class PeaShooter extends Plants {

    public PeaShooter(GameScreen gameScreen, int x, int y) {
        super(gameScreen, x, y);
        super.health = 100;
    }

    // shoot pea per 2 seconds
    {
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playing.peas.add(new Pea(2, x, y));
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
            occ[x][y]= 2;
            Playing.plants.add(new PeaShooter(gameScreen, x, y));
            return true;
        }else{
            return false;
        }
    }

}
