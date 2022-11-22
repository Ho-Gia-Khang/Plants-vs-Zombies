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

    private BufferedImage levelmenu, lv1, lv2, lv3, lv4, lv5;
    private myButtons backButton;
    private myButtons play1Button, play2Button, play3Button, play4Button, play5Button;

    public Settings(Game game) {
        super(game);
        loadImg();
        backButton = new myButtons(970, 10, 308, 135, "../data/gfx/back.png");
        play1Button = new myButtons(270, 300, 212, 104, "../data/gfx/playtiny.png");
        play2Button = new myButtons(470, 300, 212, 104, "../data/gfx/playtiny.png");
        play3Button = new myButtons(670, 300, 212, 104, "../data/gfx/playtiny.png");
        play4Button = new myButtons(870, 300, 212, 104, "../data/gfx/playtiny.png");
        play5Button = new myButtons(270, 595, 212, 104, "../data/gfx/playtiny.png");
        
    }

    private void loadImg() {
        InputStream menu = getClass().getResourceAsStream("../data/gfx/levelmenu.png");//the level menu background
        //the levels images
        InputStream level1 = getClass().getResourceAsStream("../data/gfx/L1.png");
        InputStream level2 = getClass().getResourceAsStream("../data/gfx/L2.png");
        InputStream level3 = getClass().getResourceAsStream("../data/gfx/L3.png");
        InputStream level4 = getClass().getResourceAsStream("../data/gfx/L4.png");
        InputStream level5 = getClass().getResourceAsStream("../data/gfx/L5.png");
        try{
            levelmenu = ImageIO.read(menu);
            lv1 = ImageIO.read(level1);
            lv2 = ImageIO.read(level2);
            lv3 = ImageIO.read(level3);
            lv4 = ImageIO.read(level4);
            lv5 = ImageIO.read(level5);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        //draw the background
        g.drawImage(levelmenu, 0, 0, null);
        //draw the levels images
        g.drawImage(lv1,292, 150, null );
        g.drawImage(lv2,492, 150, null );
        g.drawImage(lv3,692, 150, null );
        g.drawImage(lv4,896, 155, null );
        g.drawImage(lv5,292, 450, null );
        //draw the buttons
        backButton.draw(g);
        play1Button.draw(g);
        play2Button.draw(g);
        play3Button.draw(g);
        play4Button.draw(g);
        play5Button.draw(g);



    }

    @Override
    public void mouseClicked(int x, int y) {
        if(backButton.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.TITLE);
        }
        else if(play1Button.getBounds().contains(x, y)){

        }
        else if(play2Button.getBounds().contains(x, y)){

        }
        else if(play3Button.getBounds().contains(x, y)){

        }
        else if(play4Button.getBounds().contains(x, y)){

        }
        else if(play5Button.getBounds().contains(x, y)){

        }
        
    }

    @Override
    public void mouseMoved(int x, int y) {
        backButton.setMouseOver(false);
        play1Button.setMouseOver(false);
        play2Button.setMouseOver(false);
        play3Button.setMouseOver(false);
        play4Button.setMouseOver(false);
        play5Button.setMouseOver(false);
        if(backButton.getBounds().contains(x, y)){
            backButton.setMouseOver(true);
        }
        else if(play1Button.getBounds().contains(x, y)){
            play1Button.setMouseOver(true);
        }
        else if(play2Button.getBounds().contains(x, y)){
            play2Button.setMouseOver(true);
        }
        else if(play3Button.getBounds().contains(x, y)){
            play3Button.setMouseOver(true);
        }
        else if(play4Button.getBounds().contains(x, y)){
            play4Button.setMouseOver(true);
        }
        else if(play5Button.getBounds().contains(x, y)){
            play5Button.setMouseOver(true);
        }
        
    }

    @Override
    public void mousePressed(int x, int y) {
        if(backButton.getBounds().contains(x, y)){
            backButton.setMousePressed(true);
        }
        else if(play1Button.getBounds().contains(x, y)){
            play1Button.setMousePressed(true);
        }
        else if(play2Button.getBounds().contains(x, y)){
            play2Button.setMousePressed(true);
        }
        else if(play3Button.getBounds().contains(x, y)){
            play3Button.setMousePressed(true);
        }
        else if(play4Button.getBounds().contains(x, y)){
            play4Button.setMousePressed(true);
        }
        else if(play5Button.getBounds().contains(x, y)){
            play5Button.setMousePressed(true);
        }
        
    }

    @Override
    public void mouseReleased(int x, int y) {
        backButton.setMousePressed(false);
        play1Button.setMousePressed(false);
        play2Button.setMousePressed(false);
        play3Button.setMousePressed(false);
        play4Button.setMousePressed(false);
        play5Button.setMousePressed(false);
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub
        
    }
    
}
