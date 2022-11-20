package Scenes;

import java.awt.Graphics;
import java.awt.Color;

import Main.Game;

public class Settings extends gameScenes implements ScenesMethod {

    public Settings(Game game) {
        super(game);
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 1280, 720);
        
    }

    @Override
    public void mouseClicked(int x, int y) {

    }
    
}
