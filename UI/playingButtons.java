package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class playingButtons {
    private Rectangle bounds;
    private BufferedImage img;
    public int x, y, width, height;
    private String path;
    private boolean mouseOver, mousePressed;

    public playingButtons(int x, int y, int width, int height, String path){
        this.x = x;
        this.y = y;
        this. width = width;
        this.height = height;
        this.path = path;
        initBounds();

    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){
        //draw the body
        drawBody(g);

        //draw the border
        drawBorder(g);
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        if(mouseOver){
            g.drawRect(x, y, width + 1, height + 1);
            g.drawRect(x, y, width + 2, height + 2);
        }
        if(mousePressed){
            g.drawRect(x, y, width + 1, height + 1);
            g.drawRect(x, y, width + 2, height + 2);
            g.drawRect(x, y, width + 3, height + 3);
            g.drawRect(x, y, width + 4, height + 4);
        }
    }

    private void drawBody(Graphics g) {
        InputStream is = getClass().getResourceAsStream(path);
        try{
            img = ImageIO.read(is);
        }catch(IOException e){
            e.printStackTrace();
        }

        g.drawImage(img, x, y, null);
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public boolean isMousePressed(){
        return mousePressed;
    }

    public Rectangle getBounds(){
        return bounds;
    }

}
