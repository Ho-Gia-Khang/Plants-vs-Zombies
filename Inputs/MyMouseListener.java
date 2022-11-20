package Inputs;

import java.awt.event.*;

import Main.Game;
import Main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Game game;

    public MyMouseListener(Game game){
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameStates.gamestate) {
            case TITLE -> game.getTitleScreen().mouseDragged(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseDragged(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mouseDragged(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gamestate) {
            case TITLE -> game.getTitleScreen().mouseMoved(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseMoved(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mouseMoved(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gamestate) {
                case TITLE -> game.getTitleScreen().mouseClicked(e.getX(), e.getY());
                case PLAYING -> game.getPlaying().mouseClicked(e.getX(), e.getY());
                case SETTINGS -> game.getSettings().mouseClicked(e.getX(), e.getY());
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.gamestate) {
            case TITLE -> game.getTitleScreen().mousePressed(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mousePressed(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mousePressed(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gamestate) {
            case TITLE -> game.getTitleScreen().mouseReleased(e.getX(), e.getY());
            case PLAYING -> game.getPlaying().mouseReleased(e.getX(), e.getY());
            case SETTINGS -> game.getSettings().mouseReleased(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}