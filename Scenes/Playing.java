package Scenes;

import java.awt.Graphics;
import java.awt.Color;

import Main.Game;

public class Playing extends gameScenes implements ScenesMethod {

    public Playing(Game game) {
        super(game);
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 1280, 720);
        
    }
    @Override
    public  void mouseClicked(int x, int y) {

    }
    
}
