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
    private BufferedImage[] img = new BufferedImage[14];
    private myButtons backButton;
    private playingButtons peaShooterCard, sunFlowerCard, walnutCard, cherryBombCard, repeaterCard, jalapenoCard;

    public Playing(Game game) {
        super(game);
        loadImg();

        peaShooterCard = new playingButtons(10,100,176, 112,"../data/gfx/peashooterCard.png");
        sunFlowerCard = new playingButtons(10, 300, 105, 67, "../data/gfx/sunflowerCard.png");
        backButton = new myButtons(970, 10, 308, 135, "../data/gfx/back.png");
    }

    public void loadImg() {
        try {
            level = ImageIO.read(getClass().getResourceAsStream("../data/gfx/lawn.png"));
            //the plants
            img[0] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/peashooter.gif"));
            img[1] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/sunflower.gif"));
            img[2] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/repeater.gif"));
            img[3] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/walnut_full_life.gif"));
            img[4] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/walnut_half_life.gif"));
            img[5] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/walnut_dying.gif"));
            img[6] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/powie.gif"));
            img[7] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/jalapeno.gif"));
            img[8] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/jalapenoFire.gif"));
            //the zombies
            img[9] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/normalzombie.gif"));
            img[10] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/coneheadzombie.gif"));
            img[11] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/bucketheadzombie.gif"));
            img[12] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/burntZombie.gif"));
            // the mower
            img[13] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/lawnmowerIdle.gif"));
            img[14] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/lawnmowerActivated.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(level,0,0,null);
        backButton.draw(g);
        peaShooterCard.draw(g);
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
