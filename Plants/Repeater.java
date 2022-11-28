package Plants;

import Main.GameScreen;
import Scenes.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Repeater extends Plants {
    public Repeater(GameScreen gameScreen, int x, int y) {
        super(gameScreen, x, y);
        super.health = 100;
    }

    // shoot 2 peas per 2.2 seconds
    {
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Playing.peas.add(new Pea(3, x, y));
            }
        });
        timer.setInitialDelay(2200);
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
            occ[x][y] = 3;
            Playing.plants.add(new Repeater(gameScreen, x, y));
            return true;
        }else{
            return false;
        }
    }
}
