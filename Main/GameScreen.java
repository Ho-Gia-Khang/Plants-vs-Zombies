package Main;

import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GameScreen extends JPanel {

    private Dimension size;

    private Game game;
    
    public GameScreen(Game game){
        size = new Dimension(1280, 720);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);

        this.game = game;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);
    }
}
