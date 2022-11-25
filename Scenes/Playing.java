package Scenes;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import Main.Game;
import Main.GameStates;

import UI.*;


public class Playing extends gameScenes implements ScenesMethod {
    private BufferedImage level;
    private myButtons backButton;
    private playingButtons pea;

    public Playing(Game game) {
        super(game);
        loadImg();

        pea = new playingButtons(100,100,176, 112,"../data/gfx/peashooterCard.png");
        backButton = new myButtons(970, 10, 308, 135, "../data/gfx/back.png");
    }

    public void loadImg() {
        InputStream map = getClass().getResourceAsStream("../data/gfx/lawn.png");
        try {
            level = ImageIO.read(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(level,0,0,null);
        backButton.draw(g);
        pea.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(backButton.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.SETTINGS);
        }
        
    }

    @Override
    public void mouseMoved(int x, int y) {
        backButton.setMouseOver(false);

        if(backButton.getBounds().contains(x, y)){
            backButton.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(backButton.getBounds().contains(x, y)){
            backButton.setMousePressed(true);
        }
        
    }

    @Override
    public void mouseReleased(int x, int y) {
        backButton.setMousePressed(false);
        
    }

    @Override
    public void mouseDragged(int x, int y) {
    }
    
}
