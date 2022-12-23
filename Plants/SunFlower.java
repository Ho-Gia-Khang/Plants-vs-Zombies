package Plants;

import Scenes.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SunFlower extends Plants {
    private int type = 2;

    public SunFlower(int type, int x, int y) {
        super(1, x, y);
        super.health = 60;
    }

    {
        //drop sun every 10 seconds
        timer3=new Timer(10000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Playing.suns.add(new Sun(x, y));
            }
        });
    }
}
