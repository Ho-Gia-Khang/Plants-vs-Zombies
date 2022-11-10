package Main;

import java.awt.*;

public class Render {

    private Game game;

    public Render(Game game){
        this.game = game;
    }

    public void render(Graphics g){
        switch(GameStates.gamestate){
            case TITLE:
                game.getTitleScreen().render(g);
                break;
            case PLAYING:
                game.getPlaying().render(g);
                break;
            case SETTINGS:
                game.getSettings().render(g);
                break;
        }
    }
    
}
