package Inputs;

import java.awt.event.*;

import static Main.GameStates.*;

public class KeyBoardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            gamestate = TITLE;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            gamestate = PLAYING;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            gamestate = SETTINGS;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
