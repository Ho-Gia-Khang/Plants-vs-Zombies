package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import Main.GameStates;

import UI.myButtons;

public class Settings extends gameScenes implements ScenesMethod {

    private BufferedImage img;
    private myButtons backButton;

    public Settings(Game game) {
        super(game);
        loadImg();
        backButton = new myButtons(970, 10, 308, 135, "../data/gfx/back.png");
        
    }

    private void loadImg() {
        InputStream is = getClass().getResourceAsStream("../data/gfx/levelmenu.png");
        try{
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        
        g.drawImage(img, 0, 0, null);
        backButton.draw(g);
        
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(backButton.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.TITLE);
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
        // TODO Auto-generated method stub
        
    }
    
}
