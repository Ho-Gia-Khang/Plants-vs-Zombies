package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Helpz.Audio;
import Main.Game;
import Main.GameStates;
import UI.myButtons;

public class titleScreen extends gameScenes implements ScenesMethod {

    private BufferedImage img;
    private myButtons startButton, quitButton;

    public titleScreen(Game game) {
        super(game);
        importImg();
        startButton = new myButtons(225, 500 , 230, 100, "../data/gfx/startgame.png");
        quitButton = new myButtons(625, 500, 230, 100, "../data/gfx/exit.png");
        Audio.menu();
 
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, null);
        startButton.draw(g);
        quitButton.draw(g);
        
    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("../data/gfx/Menu.png");

        try{
            img = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(startButton.getBounds().contains(x, y)){
            GameStates.setGameState(GameStates.SETTINGS);
        }
        else if(quitButton.getBounds().contains(x, y)){
            System.exit(0);
        }
        
    }

    @Override
    public void mouseMoved(int x, int y) {
        //set the default state is that the mouse is not inside the buttons
        startButton.setMouseOver(false);
        quitButton.setMouseOver(false);

        //check if the mouse is inside the buttons
        if(startButton.getBounds().contains(x, y)){
            startButton.setMouseOver(true);
        }
        else if(quitButton.getBounds().contains(x, y)){
            quitButton.setMouseOver(true);
        }
        
    }

    @Override
    public void mousePressed(int x, int y) {
        if(startButton.getBounds().contains(x, y)){
            startButton.setMousePressed(true);
        }
        else if(quitButton.getBounds().contains(x ,y)){
            quitButton.setMousePressed(true);
        }
        
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
        
    }

    private void resetButtons() {
        startButton.setMousePressed(false);
        quitButton.setMousePressed(false);
    }

    @Override
    public void mouseDragged(int x, int y) {
        
        
    }
    
}
