package Plants;

import Scenes.Playing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PeaShooter extends Plants{

    public PeaShooter(int x, int y) {
        super(2, x, y);
        super.health = 100;
    }

    {
        //shoot pea every 2 seconds
        timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Playing.peas.add(new Pea((int) type, x, y));
            }
        });
    }
}
