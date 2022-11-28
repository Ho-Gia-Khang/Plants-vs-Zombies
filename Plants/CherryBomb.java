package Plants;

import Main.GameScreen;
import Scenes.Playing;

public class CherryBomb extends Plants {
    private int cw=74, ch=76;
    private boolean exploded;
    private Thread tcherry; // waiting time
    public CherryBomb(GameScreen gameScreen, int x, int y) {
        super(gameScreen, x, y);
        super.health = 200;
        tcherry = new Thread(new CherryWaits());
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
            occ[x][y] = 5;
            Playing.plants.add(new CherryBomb(gameScreen, x, y));
            return true;
        }else{
            return false;
        }
    }

    private class CherryWaits implements Runnable {
        public void run() {
            try{
                Thread.sleep(800); //Exploded cherry waits for 800 milliseconds
            } catch (InterruptedException ignored) {}
        }
    }

    private void startTimer(){
        tcherry.start();
    }

    private void enlarge(){
        cw+=1; ch+=1;
    }

    // getters
    private int getCw(){return cw;}
    private int getCh(){return ch;}
    private boolean isExploded(){return exploded;}
    private boolean isTcherryAlive(){return tcherry.isAlive();}

    // setters
    private void setExplode(){
        exploded=true;
    }
}
