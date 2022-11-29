package Zombies;

import Plants.Plants;
import Scenes.Playing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.Timer;

public abstract class Zombies implements Comparable<Zombies> {
    protected int health;
    protected int zombieDamage, lane, coorY, yp;
    protected float zombieSpeed, coorX; //zombie x coordinate
    protected int[] column = {296,377,458,539,620,701,782,863,944}; //9
    protected static int[] arrY = new int[5]; //zombie y coordinate
    protected static int n=0, max=50, interval, random, wave=20;
    protected static boolean gameOver=false;
    protected static Timer timer; //spawning zombie timer
    protected Timer timer2; //attacking plant timer
    public Zombies(){
        coorX = 1280f;
        coorY = arrY[setLane()];
    }
    //initialization block
    {
        //attacking plant every 1 second
        timer2=new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for(Plants plant: Playing.plants){
                    if(plant.getX()==lane && plant.getY()==yp){ //intersect plant
                        plant.hit(zombieDamage); //damage plant
                    }
                }
            }
        });
        timer2.setInitialDelay(200);
    }

    //static initialization block
    static{
        for(int i=0;i<5;i++){
            arrY[i]=117+i*98-82; //initialize zombies y coordinate
        }
    }

    //spawning zombie automatically
    public static void start(int inter){
        interval=inter;
        timer=new Timer(interval*1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(n<max){
                    n++; //increase count zombie
                    //deploy zombie
                    int type = setType();
                    switch (type){
                        case 1:
                            Playing.zombies.add(new NormalZombie());
                            break;
                        case 2:
                            Playing.zombies.add(new ConeHeadZombie());
                            break;
                        case 3:
                            Playing.zombies.add(new BucketHeadZombie());
                            break;
                    }
                    //sort zombie based on lane
                    Collections.sort(Playing.zombies);

                }
            }
        });
        timer.start();
        timer.setDelay(interval*900);
    }
    public void attack(){
        yp=getColumnEat();
        //check if zombie intersects plant
        if(Plants.getOcc(lane, yp)!=0){ //intersect plant
            for(Plants plant: Playing.plants){
                if(plant.getX()==lane && plant.getY()==yp){
                    timer2.start();
                    if(plant.isDead()){ //plant dies
                        plant.stop(); //stop plant's activity
                        Plants.setOcc(lane, yp); //set spot to empty
                        timer2.stop(); //stop attacking plant
                        Playing.plants.remove(plant);
                        break;
                    }
                }
            }
        }else{ //field empty
            move();
        }
    }
    public void move(){
        coorX-=zombieSpeed; //move
    }
    public void stopEat(){
        timer2.stop(); //stop eating plant
    }

    public static void startWave(){ //start final wave
        timer.setInitialDelay(4000);
        timer.start();
        //Playing.setWave(1); //set wave to 1
    }
    public static void stop(){
        timer.stop();
    }; //stop deploying zombie

    public boolean gameOver(){
        if(coorX>210){ //zombie hasn't reached house yet
            return false;
        }else{ //zombie reaches house
            gameOver=true;
            return true;
        }
    }
    public static void resetGameOver(){gameOver=false;}
    public static void resetN(){n=0;}


    //setter
    public int setLane(){
        lane=(int)(Math.random() * 5); //generate zombie lane from 0 to 4
        return lane;
    }

    public static int setType(){
        if(n<=3){ //easy
            timer.setDelay(interval*550);
            return 1; //normal zombie
        }else if(n<=6){ //medium
            timer.setDelay(interval*250);
            if((int)(Math.random() * 3)==2){ //generate zombie type from 0 to 2
                return 2; //football zombie
            }else{
                return 1; //normal zombie
            }
        }else if(n<=wave){ //hard
            timer.setDelay(interval*200);
            if(n==wave){ //stop when final wave zombies are about to come
                timer.stop(); //wait for final wave
            }
            random=(int)(Math.random() * 4); //generate zombie type from 0 to 3
            if(random<=1){ //0 or 1
                return 1; //normal zombie
            }else if(random==2){ //2
                return 2; //football zombie
            }else{ //3
                return 3; //flying zombie
            }
        }else{ //(n<=max) extreme
            timer.setDelay(interval*100);
            random=(int)(Math.random() * 6); //generate zombie type from 0 to 5
            if(random<=2){ //0, 1, or 2
                return 2; //football zombie
            }else if(random<=4){ //3 or 4
                return 3; //flying zombie
            }else{ //5
                return 1; //normal zombie
            }
        }
    }

    //getter
    public static int getN(){return n;}
    public static int getMax(){return max;}
    public static int getWave(){return wave;}
    public int getDamage(){return zombieDamage;}
    public int getHealth(){return health;}
    public float getCoorX(){return coorX;}
    public int getCoorY(){return coorY;}
    public int getLane(){return lane;}
    @Override
    public int compareTo(Zombies z) { //sort zombies based on lane
        return lane-z.getLane();
    }


    //Abstract methods
    public void hit(int damage){
        health-=damage;
    }

    //check is the zombie dead
    public boolean isDead(){
        return health<=0;
    }

    public abstract int getColumn(); //convert x coordinate to field column

    public abstract int getColumnEat();//convert x coordinate to field column for attacking plant
}
