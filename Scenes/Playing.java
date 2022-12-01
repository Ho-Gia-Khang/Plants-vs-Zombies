package Scenes;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import Main.Game;
import Main.GameStates;

import UI.*;
import Plants.*;
import Zombies.*;
import Helpz.*;

import javax.swing.*;

public class Playing extends gameScenes implements ScenesMethod, ActionListener {
    private static final long serialVersionUID = 1L;

    //width and height for (p)eashooter, (s)unflower, (r)epeater
    private int pwidth=62, pheight=66, swidth=pwidth, sheight=pheight+5, rwidth=pwidth+2, rheight=pheight+2;
    private BufferedImage level;
    private BufferedImage[] img = new BufferedImage[39];
    private Shape[][] field = new Shape[5][9];
    private Rectangle[] rec = new Rectangle[8]; //rectangle for menu and others
    private Ellipse2D e_shovel; //ellipse for shovel
    private Point mouse = new Point(); //point for mouse position
    private int xp, yp, i, j, mouse_x, mouse_y; //coordinate
    private float fxp; //coordinate
    private boolean play=true, win=false, end_sound=true, sun_clicked=false;
    public static boolean start = false;
    private static int wave=0; //zombies wave
    private Timer timer; //set timer
    private Toolkit t = Toolkit.getDefaultToolkit();
    private myButtons backButton;
    private playingButtons peaShooterCard, sunFlowerCard, walnutCard, cherryBombCard, repeaterCard, jalapenoCard;
    private Player player;
    private Plants<Integer> plant = new Plants<>(0, 0, 0);
    private Zombies zombie;
    private Pea pea;
    private Sun sun;

    public static ArrayList<Plants<Integer>> plants = new ArrayList<>();
    public static ArrayList<Zombies> zombies = new ArrayList<>();
    public static ArrayList<Sun> suns = new ArrayList<>();
    public static ArrayList<Pea> peas = new ArrayList<>();


    public Playing(Game game) {
        super(game);
        timer = new Timer(25, this);

        timer.start();
        start();
    }

    public void start(){
        player = new Player();
        Sun.start();
        Zombies.start(16);

        loadImg();
        init();

        timer.start();
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //draw background
        g.drawImage(img[0], 0, 0, 1024, 625, null);

        //draw progress
        xp = Math.round((205.0f/Zombies.getMax())*Zombies.getN());
        yp = Math.round((190.0f/Zombies.getMax())*Zombies.getN());
        g.drawImage(img[27], 498+205-xp, 588, xp, 16, null); //draw greenbar (max 205 pixels)
        g.drawImage(img[26], 490, 572, 215, 40, null); //draw bar
        if(Zombies.getN() <= Zombies.getMax()-5){
            g.drawImage(img[25], 489, 564, 261, 49, null); //draw flag
        }else{ //raise flag for the last 5 zombies
            g.drawImage(img[25], 489, 552+Math.round((12.0f/5)*(Zombies.getMax()-Zombies.getN())), 261, 49, null);
        }
        g.drawImage(img[24], 675-yp, 574, 35, 38, null); //draw zombie head (485 to 675; max 190 pixels)

        //draw plant
        Iterator<Plants<Integer>> itpl = plants.iterator();
        while (itpl.hasNext()){
            plant=itpl.next();

            xp=Plants.getCoor(plant.getX(),plant.getY()).getX(); //x coordinate
            yp=Plants.getCoor(plant.getX(),plant.getY()).getY(); //y coordinate

            if(plant.getType().equals(1)){ //sunflower gif
                g.drawImage(img[5], xp-swidth/2, yp-sheight/2, swidth, sheight, null);
                plant.act();

            }else if(plant.getType().equals(4)){ //wallnut gif
                if(plant.getHealth()>=150){ //wallnut full life
                    g.drawImage(img[37], xp-(pwidth+2)/2, yp-(pheight+4)/2, pwidth+2, pheight+5, null);
                }else{ //wallnut half life
                    g.drawImage(img[38], xp-(pwidth+2)/2, yp-(pheight+4)/2, pwidth+2, pheight+5, null);
                }

            }else if(plant.getType().equals(5)){ //cherrybomb
                if(plant.getCw()<110){ //enlarge
                    g.drawImage(img[30], xp-plant.getCw()/2-4, yp-plant.getCh()/2-4, plant.getCw(), plant.getCh(), null);
                    plant.enlarge();
                    plant.cherry_enlarge(); //play cherry_enlarge sound
                }else{ //explode
                    i=plant.getX();
                    j=plant.getY();
                    if(!plant.isExploded()){
                        plant.cherrybomb(); //play cherrybomb sound
                        plant.setExplode(); //set explode to true
                        plant.startTimer(); //start waiting thread
                        Plants.setOcc(i, j); //set spot to empty

                        //kill zombie
                        Iterator<Zombies> itz = zombies.iterator();
                        while (itz.hasNext()){
                            Zombies zombie=itz.next();
                            if(zombie.getLane()<=(i+1) && zombie.getLane()>=(i-1)
                                    && zombie.getColumn()<=(j+1) && zombie.getColumn()>=(j-1)){ //zombies around plant 3x3
                                zombie.stopEat(); //stop eating plant
                                itz.remove();
                            }
                        }
                    }
                    if(plant.isTcherryAlive()){ //waiting thread running
                        //draw explosion
                        g.drawImage(img[31], xp-150, yp-125, 300, 250, null);
                    }else{ //remove explosion
                        itpl.remove();
                    }
                }

            }else{
                if(plant.getType().equals(2)){ //peashooter gif
                    g.drawImage(img[6], xp-(pwidth+2)/2, yp-(pheight+2)/2, pwidth+2, pheight+2, null);
                }else if(plant.getType().equals(3)){ //repeater gif
                    g.drawImage(img[7], xp-(rwidth+20)/2, yp-(rheight+9)/2, rwidth+26, rheight+13, null);
                }

                //shoot zombie
                xp=plant.getX(); //field row
                yp=plant.getY(); //field column

                A: for(Zombies zombie: zombies){
                    if(xp==zombie.getLane() && yp<=zombie.getColumn()){ //zombies in front of plant
                        if(plant.isIdle()){
                            plant.attack();
                        }
                        plant.setThreat(true);
                        break A;
                    }else{
                        plant.setThreat(false);
                    }
                }
                if(zombies.isEmpty()){ //there is no zombie
                    plant.setThreat(false);
                }
                if(!plant.isThreaten()){ //plant is not threaten: no zombie in front of plant
                    plant.stop();
                }
            }
        }

        //zombie
        Iterator<Zombies> itz = zombies.iterator();
        while (itz.hasNext()){
            Zombies zombie=itz.next();

            //if (zombie intersects plant) -> eat; else -> move
            if(zombie.getType()!=3){ //not flying zombie
                zombie.attack();
            }

            fxp=zombie.getCoorX(); //get zombie x coordinate (float)
            yp=zombie.getLane(); //get zombie lane (int)

            //draw zombie
            if(zombie.getType()==1){ //standard zombie
                g.drawImage(img[8], Math.round(fxp), zombie.getCoorY(), pwidth+11, pheight+53, null);
            }else if(zombie.getType()==2){ //football zombie
                if(zombie.getHealth()>=45){ //zombie uses helmet
                    g.drawImage(img[9], Math.round(fxp), zombie.getCoorY(), null);
                }else{ //zombie doesn't use helmet
                    g.drawImage(img[20], Math.round(fxp), zombie.getCoorY(), null);
                }
            }else if(zombie.getType()==3){ //flying zombie
                g.drawImage(img[33], Math.round(fxp), zombie.getCoorY()-15, 101, 120, null);
                zombie.move();
            }

            //check if zombie intersects pea
            Iterator<Pea> itpea = peas.iterator();
            while (itpea.hasNext()){
                pea=itpea.next();
                if(pea.getX()==yp){ //same lane
                    if(zombie.getType()==1){ //normal zombie
                        if((pea.getCoorX()>=fxp-6) && (pea.getCoorX()<=fxp+92)){
                            pea.splat(); //play splat sound
                            zombie.hit(pea.getDamage()); //damage zombie
                            itpea.remove(); //remove pea from list
                        }
                    }else if(zombie.getType()==2){ //football zombie
                        if((pea.getCoorX()>=fxp+7) && (pea.getCoorX()<=fxp+105)){
                            pea.shieldhit(); //play shieldhit sound
                            zombie.hit(pea.getDamage()); //damage zombie
                            itpea.remove(); //remove pea from list
                        }
                    }else if(zombie.getType()==3){ //flying zombie
                        if((pea.getCoorX()>=fxp+24) && (pea.getCoorX()<=fxp+92)){
                            pea.splat(); //play splat sound
                            zombie.hit(pea.getDamage()); //damage zombie
                            itpea.remove(); //remove pea from list
                        }
                    }
                }
            }

            //check if zombie is dead
            if(zombie.isDead()){
                zombie.stopEat(); //stop eating plant
                if(zombie.getType()==2){ //football zombie
                    zombie.yuck2();
                }else{
                    zombie.yuck();
                }
                itz.remove();
            }

            //check if zombie reaches house
            if(zombie.gameOver()){
                play=false;
                if(fxp<=23){ //remove zombie
                    itz.remove();
                }
            }
        }

        //check if all zombies before wave are dead
        if(wave==0 && Zombies.getN()==Zombies.getWave() && zombies.isEmpty()){
            Zombies.startWave(); //start wave
        }

        //check if all zombies are dead
        if(Zombies.getN()==Zombies.getMax() && zombies.isEmpty()){
            play=false;
            win=true;
        }

        //draw plant menu
        g.drawImage(img[34], 15, 22, 150, 580, null);

        //draw sunflower points
        player.draw(g2);

        //draw black&white plant menu
        if(player.getCredits()<200){
            g.drawImage(img[15], 33, 339, rwidth+2, rheight+2, null); //draw repeater g
            if(player.getCredits()<150){
                g.drawImage(img[32], 30, 512, rwidth+7, rheight+6, null); //draw cherrybomb g
                if(player.getCredits()<100){
                    g.drawImage(img[14], 34, 255, pwidth+2, pheight, null); //draw peashooter g
                    if(player.getCredits()<50){
                        g.drawImage(img[13], 34, 164, swidth, sheight, null); //draw sunflower g
                        g.drawImage(img[36], 32, 426, swidth-1, sheight-2, null); //draw wallnut g
                    }
                }
            }
        }

        //draw shovel
        if(!player.getShovel()){ //if shovel is idle
            g.drawImage(img[22], 171, 548, 70, 70, null);
        }else{ //if shovel is taken
            g.drawImage(img[23], 171, 548, 70, 70, null);
            //draw shovel following mouse position
            g.drawImage(img[21], mouse.getX(), mouse.getY()-70, 68, 70, null);
        }

        //draw transparent plant following mouse position
        if(player.getChoice()==1){ //sunflower
            g2.setComposite(AlphaComposite.SrcOver.derive(0.7f)); //set alpha to 0.7
            g2.drawImage(img[2], mouse_x - swidth/2, mouse_y - sheight/2, swidth, sheight, null);
            g2.setComposite(AlphaComposite.SrcOver.derive(1f)); //set alpha back to 1
        }else if(player.getChoice()==2){ //peashooter
            g2.setComposite(AlphaComposite.SrcOver.derive(0.7f));
            g2.drawImage(img[3], mouse_x - pwidth/2+1, mouse_y - pheight/2, pwidth+2, pheight, null);
            g2.setComposite(AlphaComposite.SrcOver.derive(1f));
        }else if(player.getChoice()==3){ //repeater
            g2.setComposite(AlphaComposite.SrcOver.derive(0.7f));
            g2.drawImage(img[4], mouse_x - rwidth/2+2, mouse_y - rheight/2+2, rwidth+2, rheight+2, null);
            g2.setComposite(AlphaComposite.SrcOver.derive(1f));
        }else if(player.getChoice()==4){ //wallnut
            g2.setComposite(AlphaComposite.SrcOver.derive(0.7f));
            g2.drawImage(img[35], mouse_x - 32, mouse_y - 36, 61, 69, null);
            g2.setComposite(AlphaComposite.SrcOver.derive(1f));
        }else if(player.getChoice()==5){ //cherrybomb
            g2.setComposite(AlphaComposite.SrcOver.derive(0.7f));
            g2.drawImage(img[30], mouse_x - 37, mouse_y - 38, 74, 76, null);
            g2.setComposite(AlphaComposite.SrcOver.derive(1f));
        }

        if(play){
            //draw pea
            Iterator<Pea> itpea_p = peas.iterator();
            while (itpea_p.hasNext()){
                pea=itpea_p.next();
                if(pea.getType()==2){ //peashooter
                    g.drawImage(img[10], pea.getCoorX(), pea.getCoorY(), null);
                }else{ //repeater
                    g.drawImage(img[19], pea.getCoorX(), pea.getCoorY(), null);
                }
                pea.move();

                if(pea.getCoorX()>1030){ //pea move beyond the frame
                    itpea_p.remove();
                }
            }

            //draw falling sun
            Iterator<Sun> its = suns.iterator();
            while (its.hasNext()){
                sun=its.next();
                if(sun.isSunflower()){ //sun from sunflower
                    if(!sun.isWaiting()){ //if the sun is not waiting
                        sun.startTimer(); //start waiting thread
                        sun.setWaiting(); //set waiting variable to true
                    }
                    if(sun.isTsunAlive()){ //waiting thread running
                        g.drawImage(img[1],sun.getX(),sun.getY(),80,80,null);
                        sun.setE(new Ellipse2D.Float(sun.getX(), sun.getY(), 80, 80));
                    }else{ //remove sunflower sun
                        its.remove();
                    }
                }else{ //sun from the sky
                    if(sun.getY()<sun.getLimit()){ //sun falls
                        g.drawImage(img[1],sun.getX(),sun.getY(),80,80,null);
                        sun.setE(new Ellipse2D.Float(sun.getX(), sun.getY(), 80, 80));
                        sun.lower();
                    }else if(sun.getY()<(sun.getLimit()+300)){ //sun waits a while until gone
                        if(!sun.isWaiting()){ //if the sun is not waiting
                            sun.startTimer(); //start waiting thread
                            sun.setWaiting(); //set waiting variable to true
                        }
                        if(sun.isTsunAlive()){ //waiting thread running
                            g.drawImage(img[1],sun.getX(),sun.getY(),80,80,null);
                            sun.setE(new Ellipse2D.Float(sun.getX(), sun.getY(), 80, 80));
                        }else{ //remove falling sun
                            its.remove();
                        }
                    }
                }
            }

            //wave
            if(wave==1){ //a huge wave of zombies is approaching
                g.drawImage(img[28], 160, 290, 743, 42, null);
            }else if(wave==2){ //final wave
                g.drawImage(img[29], 380, 280, 300, 61, null);
            }

        }else{ //play=false, win or game over
            player.setChoice(0);
            for(Plants plant: plants){
                plant.stop();
            }
            Zombies.stop();
            Sun.stop();
            suns.clear();
            peas.clear();

            if(win){
                if(end_sound){
                    Audio.win(); //play win sound
                    end_sound=false;
                }
                g2.setColor(Color.WHITE);
                g2.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                g2.fill(rec[2]);
                g2.setComposite(AlphaComposite.SrcOver.derive(1f));
                rec[1] = new Rectangle(442, 410, 140, 65);

                g.drawImage(img[16],263,130,500,250,null); //win image
                g.drawImage(img[17],442,410,140,65,null); //play again image

            }else{ //lose
                if(end_sound){
                    Audio.lose(); //play lose sound
                    end_sound=false;
                }
                g2.setColor(Color.WHITE);
                g2.setComposite(AlphaComposite.SrcOver.derive(0.6f));
                g2.fill(rec[2]);
                g2.setComposite(AlphaComposite.SrcOver.derive(1f));
                rec[1] = new Rectangle(400, 395, 220, 45);

                g.drawImage(img[18],425,85,180,210,null); //brain image
                g.drawImage(img[11],365,190,null); //lose image
                g.drawImage(img[12],410,405,200,25,null); //try again image
            }
        }
        g.dispose();
    }

    @Override
    public void mouseClicked(int x, int y) {
        
    }

    @Override
    public void mouseMoved(int x, int y) {
        mouse_x = x;
        mouse_y = y;
    }

    @Override
    public void mousePressed(int x, int y) {
        if(play){ //the game is playing
            Iterator<Sun> its = suns.iterator();
            while (its.hasNext()){
                sun=its.next();
                try{
                    if(sun.getE().contains(x, y)){ //click falling sun
                        sun.points(); //play points sound
                        player.addSunCredits(); //add 25 sun points;
                        sun_clicked=true;
                        its.remove();
                        break;
                    }
                }catch(Exception ex){}
            }
            if(!sun_clicked){ //sun is not clicked
                // check if mouse clicked plants
                if(rec[3].contains(x, y)) { //click sunflower
                    if(player.getCredits()>=50){
                        Audio.seedlift(); //play seedlift sound
                        player.setChoice((player.getChoice()==1) ? 0:1);
                    }else{
                        Audio.buzzer(); //play buzzer sound
                        player.setChoice(0);
                    }
                }else if(rec[4].contains(x, y)) { //click peashooter
                    if(player.getCredits()>=100){
                        Audio.seedlift(); //play seedlift sound
                        player.setChoice((player.getChoice()==2) ? 0:2);
                    }else{
                        Audio.buzzer(); //play buzzer sound
                        player.setChoice(0);
                    }
                }else if(rec[5].contains(x, y)) { //click repeater
                    if(player.getCredits()>=200){
                        Audio.seedlift(); //play seedlift sound
                        player.setChoice((player.getChoice()==3) ? 0:3);
                    }else{
                        Audio.buzzer(); //play buzzer sound
                        player.setChoice(0);
                    }
                }else if(rec[6].contains(x, y)) { //click wallnut
                    if(player.getCredits()>=50){
                        Audio.seedlift(); //play seedlift sound
                        player.setChoice((player.getChoice()==4) ? 0:4);
                    }else{
                        Audio.buzzer(); //play buzzer sound
                        player.setChoice(0);
                    }
                }else if(rec[7].contains(x, y)) { //click cherrybomb
                    if(player.getCredits()>=150){
                        Audio.seedlift(); //play seedlift sound
                        player.setChoice((player.getChoice()==5) ? 0:5);
                    }else{
                        Audio.buzzer(); //play buzzer sound
                        player.setChoice(0);
                    }
                }else if(player.getChoice()!=0){ //to click field
                    A: for(i=0;i<5;i++){
                        for(j=0;j<9;j++){
                            if(field[i][j].contains(x, y)){ //plant the plant in field
                                if(plant.put(i,j,player.getChoice())){ //empty spot
                                    Audio.plant(); //play plant sound
                                    player.plant();
                                }
                                player.setChoice(0);
                                break A;
                            }
                        }
                    }
                    if(i==5){ //not selected a plant-able area
                        player.setChoice(0);
                    }
                }
            }else{sun_clicked=false;}

            //check shovel
            if(player.getShovel()){
                A: for(i=0;i<5;i++){
                    for(j=0;j<9;j++){
                        if(field[i][j].contains(x, y)){ //click field
                            if(Plants.getOcc(i, j)!=0){ //plant exist
                                Plants.setOcc(i, j); //remove plant
                                B: for(Plants plant: plants){
                                    if(plant.getX()==i && plant.getY()==j){
                                        plant.stop(); //stop plant's activity
                                        Audio.remove(); //play remove sound
                                        plants.remove(plant);
                                        break B;
                                    }
                                }
                                for(Zombies zombie: zombies){
                                    if(zombie.getLane()==i && zombie.getColumn()==j){
                                        zombie.stopEat(); //stop eating plant
                                    }
                                }
                            }
                            break A; //empty spot
                        }
                    }
                }
                player.setShovel(false);

            }else if(e_shovel.contains(x, y)){ //click shovel
                player.setShovel(true);
                Audio.shovel(); //play shovel sound
            }

        }else{ //the game is not playing
            if (rec[1].contains(x, y)) { //click try or play again
                play=true;
                win=false;
                end_sound=true;
                for(Zombies zombie: zombies){
                    zombie.stopEat(); //stop eating plant
                }
                plants.clear();
                zombies.clear();
                Zombies.resetN();
                Zombies.resetGameOver();
                player.resetCredits();
                for(i=0;i<5;i++){
                    for(j=0;j<9;j++){
                        Plants.setOcc(i, j);
                    }
                }

                Audio.begin();
                Sun.start();
                Zombies.start(16);
            }
        }
    }


    @Override
    public void mouseReleased(int x, int y) {
        
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
    }
    public void loadImg() {
        try{ //load image
            img[0] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Background.jpg"));
            img[1] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Sun.png"));
            img[2] = ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Sunflower.png"));
            img[3]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Peashooter.png"));
            img[4]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Repeater.png"));
            img[5]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Sunflower.gif"));
            img[6]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Peashooter.gif"));
            img[7]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Repeater.gif"));
            img[8]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Zombie.gif"));
            img[9]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Zombief.gif"));
            img[10]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Pea_p.png"));
            img[11]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Wasted.png"));
            img[12]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Tryagain.png"));
            img[13]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Sunflower_g.png"));
            img[14]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Peashooter_g.png"));
            img[15]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Repeater_g.png"));
            img[16]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Win.png"));
            img[17]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Playagain.png"));
            img[18]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Brain.png"));
            img[19]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Pea_r.png"));
            img[20]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Zombief_half.gif"));
            img[21]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Shovel1.png"));
            img[22]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Shovel2.png"));
            img[23]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Shovel3.png"));
            img[24]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Progress1.png"));
            img[25]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Progress2.png"));
            img[26]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Progress3.png"));
            img[27]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Progress4.png"));
            img[28]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/HugeWave.png"));
            img[29]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/FinalWave.png"));
            img[30]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Cherry.png"));
            img[31]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Powie.png"));
            img[32]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Cherry_g.png"));
            img[33]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Zombie_fly.gif"));
            img[34]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Background_menu.png"));
            img[35]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Wallnut.png"));
            img[36]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/image/Wallnut_g.png"));
            img[37]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Wallnut_full.gif"));
            img[38]=ImageIO.read(getClass().getResourceAsStream("../data/gfx/gif/Wallnut_half.gif"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void init(){
        //create rectangle for plant menu and end game
        rec[2] = new Rectangle(0, 0, 1024, 626); //end
        rec[3] = new Rectangle(23, 156, pwidth+73, pheight+21); //sunflower
        rec[4] = new Rectangle(23, 249, pwidth+73, pheight+12); //peashooter
        rec[5] = new Rectangle(23, 333, pwidth+73, pheight+14); //repeater
        rec[6] = new Rectangle(23, 419, pwidth+73, pheight+17); //wallnut
        rec[7] = new Rectangle(23, 508, pwidth+73, pheight+19); //cherrybomb

        //create ellipse for shovel
        e_shovel = new Ellipse2D.Float(171, 548, 70, 70);

        //create rectangle clickable area for field
        int[] fw = {0,90,165,250,330,410,492,570,651,749}; //field width
        int[] fh = {0,118,215,323,405,516}; //field height
        for(i=0;i<5;i++){
            for(j=0;j<9;j++){
                //set rectangle field area
                field[i][j] = new Rectangle(245+fw[j], 50+fh[i], fw[j+1]-fw[j], fh[i+1]-fh[i]);

                //set plant field
                Plants.setOcc(i, j);
                Plants.setCoor(i, j);
            }
        }
    }

    public static void setWave(int w){
        wave=w;
    }
}

