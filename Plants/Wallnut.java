package Plants;

import Main.GameScreen;
import Scenes.Playing;

public class Wallnut extends Plants{
    public Wallnut(GameScreen gameScreen, int x, int y) {
        super(gameScreen, x, y);
        super.health = 1000;
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
            occ[x][y]= 4;
            Playing.plants.add(new Wallnut(gameScreen, x, y));
            return true;
        }else{
            return false;
        }
    }
}
