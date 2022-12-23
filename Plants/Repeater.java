package Plants;

import Scenes.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Repeater extends Plants {
    public Repeater(int type, int x, int y) {
        super(3, x, y);
        super.health = 100;
    }

    {
        //repeater shoots second pea every 2.2 seconds
        timer2=new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Playing.peas.add(new Pea(3, x, y));
            }
        });
        timer2.setInitialDelay(2200);
    }
}
