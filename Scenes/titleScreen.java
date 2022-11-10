package Scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Main.Game;

public class titleScreen extends gameScenes implements ScenesMethod {

    private BufferedImage img;

    public titleScreen(Game game) {
        super(game);
        importImg();
 
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, null);
        
    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("../data/gfx/mainMenu.png");

        System.out.println(is);

        try{
            img = ImageIO.read(is);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    
    }
    
}
