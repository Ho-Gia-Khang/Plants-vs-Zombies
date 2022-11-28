package Plants;

import Main.GameScreen;
import Scenes.Playing;

public class Jalapeno extends Plants{
    private int jw = 74, jh = 76;
    private boolean exploded;
    private Thread tJalapeno; // waiting time
    public Jalapeno(GameScreen gameScreen, int x, int y) {
        super(gameScreen, x, y);
        super.health = 200;
        tJalapeno = new Thread(new JalapenoWaits());
    }

    @Override
    public void attack() {
        this.idle = false;
    }

    @Override
    public void stop() {
        this.idle = true;
    }

    @Override
    public boolean put(int x, int y, GameScreen gameScreen) {
        if(occ[x][y]==0){ //empty spot
            occ[x][y] = 6;
            Playing.plants.add(new Jalapeno(gameScreen, x, y));
            return true;
        }else{
            return false;
        }
    }

    private class JalapenoWaits implements Runnable {
        public void run() {
            try{
                Thread.sleep(800); //Exploded Jalapeno waits for 800 milliseconds
            } catch (InterruptedException ignored) {}
        }
    }

    private void startTimer(){
        tJalapeno.start();
    }

    private void enlarge(){
        jw += 1;
        jh += 1;
    }

    // getters
    private int getCw(){return jw;}
    private int getCh(){return jh;}
    private boolean isExploded(){return exploded;}
    private boolean isTcherryAlive(){return tJalapeno.isAlive();}

    // setters
    private void setExplode(){
        exploded=true;
    }
}
