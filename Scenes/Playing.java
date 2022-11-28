package Scenes;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import Main.Game;
import Main.GameStates;

import UI.*;
import Plants.*;
import Zombies.*;

import javax.swing.Timer;

public class Playing extends gameScenes implements ScenesMethod, ActionListener {
    private BufferedImage level;
    private BufferedImage[] img = new BufferedImage[15];
    private Timer timer;
    private myButtons backButton;
    private playingButtons peaShooterCard, sunFlowerCard, walnutCard, cherryBombCard, repeaterCard, jalapenoCard;

    public static ArrayList<Plants> plants = new ArrayList<>();
    public static ArrayList<Sun> suns = new ArrayList<>();
    public static ArrayList<Pea> peas = new ArrayList<>();


    public Playing(Game game) {
        super(game);
        timer = new Timer(25, this);
        loadImg();
        //the plant cards
        peaShooterCard = new playingButtons(10,10,157, 100,"../data/gfx/peashooterCard.png");
        sunFlowerCard = new playingButtons(10, 120, 157, 100, "../data/gfx/sunflowerCard.png");
        walnutCard = new playingButtons(10, 230, 157, 100, "../data/gfx/wallnutCard.png");
        cherryBombCard = new playingButtons(10, 340, 157, 100, "../data/gfx/cherryBombCard.png");
        repeaterCard = new playingButtons(10, 450, 157, 100, "../data/gfx/repeaterCard.png");
        jalapenoCard = new playingButtons(10, 560, 157, 100, "../data/gfx/jalapenoCard.png");

        backButton = new myButtons(970, 10, 308, 135, "../data/gfx/back.png");

        //Sun.start();
        //Zombies.start();
        timer.start();
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
        // draw the plant cards
        peaShooterCard.draw(g);
        sunFlowerCard.draw(g);
        walnutCard.draw(g);
        cherryBombCard.draw(g);
        repeaterCard.draw(g);
        jalapenoCard.draw(g);
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
        peaShooterCard.setMouseOver(false);
        sunFlowerCard.setMouseOver(false);
        walnutCard.setMouseOver(false);
        cherryBombCard.setMouseOver(false);
        repeaterCard.setMouseOver(false);
        jalapenoCard.setMouseOver(false);

        if(backButton.getBounds().contains(x, y)){
            backButton.setMouseOver(true);
        }
        else if(peaShooterCard.getBounds().contains(x, y)){
            peaShooterCard.setMouseOver(true);
        }
        else if(sunFlowerCard.getBounds().contains(x, y)){
            sunFlowerCard.setMouseOver(true);
        }
        else if(walnutCard.getBounds().contains(x, y)){
            walnutCard.setMouseOver(true);
        }
        else if(cherryBombCard.getBounds().contains(x, y)){
            cherryBombCard.setMouseOver(true);
        }
        else if(repeaterCard.getBounds().contains(x, y)){
            repeaterCard.setMouseOver(true);
        }
        else if(jalapenoCard.getBounds().contains(x, y)){
            jalapenoCard.setMouseOver(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
