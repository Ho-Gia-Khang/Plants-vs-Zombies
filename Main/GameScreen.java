package Main;

import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

import Inputs.*;

public class GameScreen extends JPanel {

    private Dimension size;
    private Game game;

    private  MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;
    
    public GameScreen(Game game){
        size = new Dimension(1280, 720);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);

        this.game = game;
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyBoardListener = new KeyBoardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);

        requestFocus();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);
    }
}
