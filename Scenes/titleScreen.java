package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;
import ui.MyButton;

public class titleScreen extends gameScenes implements ScenesMethod {

    private BufferedImage img;
    private MyButton bPlaying;

    public titleScreen(Game game) {
        super(game);
        importImg();
        initButtons();
 
    }

    private void initButtons() {
        bPlaying = new MyButton("Play", 100, 100 , 100, 30);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, null);
        drawButtons(g);
        
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
    }

    @Override
    public  void mouseClicked(int x, int y) {

    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("../data/gfx/Menu.png");

        System.out.println(is);

        try{
            img = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    
}
