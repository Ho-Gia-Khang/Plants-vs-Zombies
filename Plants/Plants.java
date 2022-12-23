package Plants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Scenes.Playing;
import UI.Point;
import Helpz.Audio;

public class Plants{
    protected int type;
    protected int health;
    protected boolean idle=true, threaten=false, exploded=false;
    private Timer timer, timer2, timer3; //set timer
    protected int x, y; //array for plant location [5][9]
    private int cw=74, ch=76; //cherrybomb
    private static int[][] occ = new int[5][10];
    private static Point[][] coor = new Point[5][9]; //array for plants coordinate
    private Clip clip, clip2;
    private Thread tcherry; //thread for waiting time

    public Plants(int type, int x, int y){
        this.type=type;
        this.x=x;
        this.y=y;
        if(type.equals(1)){ //Sunflower
            health = 60;
        }
        else if(type.equals(3)){ //Repeater
            health = 100;
        }else if(type.equals(4)){ //Wallnut
            health = 1000;
        }else if(type.equals(5)){ //Cherrybomb
            health = 200;
            tcherry = new Thread(new CherryWaits());
            try{
                clip = AudioSystem.getClip();
                clip2 = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Audio.class.getResource(("../data/sfx/Cherry_enlarge.wav"))));
                clip2.open(AudioSystem.getAudioInputStream(Audio.class.getResource(("../data/sfx/Cherrybomb.wav"))));
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }else{}
    }

    //initialization block
    {
        //repeater shoots second pea every 2.2 seconds
        timer2=new Timer(2000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Playing.peas.add(new Pea(3, x, y));
            }
        });
        timer2.setInitialDelay(2200);

        //drop sun every 10 seconds
        timer3=new Timer(10000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Playing.suns.add(new Sun(x, y));
            }
        });
    }

    //getter
    public int getX(){return x;}
    public int getY(){return y;}
    public int getType(){return type;}
    public int getHealth(){return health;}
    public boolean isThreaten(){return threaten;}
    public static int getOcc(int x, int y){return occ[x][y];}
    public static Point getCoor(int x, int y){return coor[x][y];}
    public boolean isIdle(){return idle;}

    //setter
    public static void setOcc(int i, int j){
        occ[i][j]=0;
    }
    public static void setCoor(int i, int j){
        coor[i][j]=new Point(296+j*81,117+i*98);
    }
    public void setThreat(boolean threat){
        threaten=threat;
    }

    public boolean put(int x, int y, int type){
        if(occ[x][y]==0){ //empty spot
            occ[x][y]=(int)type;
            Playing.plants.add(new Plants(type, x, y));
            return true;
        }else{
            return false;
        }
    }
    public void attack(){
        timer.start();
        if(getType() == 3){ //repeater
            timer2.start();
        }
        idle=false;
    }
    public void hit(int damage){
        health-=damage;
    }

    //check is Actor dead
    public boolean isDead(){
        return health<=0;
    }
    public void act(){
        timer3.start();
    }
    public void stop(){
        timer.stop();
        timer2.stop();
        timer3.stop();
        idle=true;
    }


    //cherrybomb
    //private class Threading
    private class CherryWaits implements Runnable {
        public void run() {
            try{
                Thread.sleep(800); //Exploded cherry waits for 800 milliseconds
            } catch (InterruptedException e) {}
        }
    }
    public void startTimer(){
        tcherry.start();
    }

    public void enlarge(){
        cw+=1; ch+=1;
    }
    public int getCw(){return cw;}
    public int getCh(){return ch;}
    public boolean isExploded(){return exploded;}
    public boolean isTcherryAlive(){return tcherry.isAlive();}
    public void setExplode(){
        exploded=true;
    }
    public void cherry_enlarge(){ //play cherry_enlarge sound
        clip.start();
    }
    public void cherrybomb(){ //play cherrybomb sound
        clip.stop();
        clip2.start();
    }
}


