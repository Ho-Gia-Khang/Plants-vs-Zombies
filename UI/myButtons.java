package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class myButtons {
    private Rectangle bounds;
    private BufferedImage img;
    public int x, y, width, height;
    private String path;
    private boolean mouseOver, mousePressed;

    public myButtons(int x, int y, int width, int height, String path){
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
        g.drawRect(x + 35, y + 35, width - 70, height - 70);
        if(mouseOver){
            g.drawRect(x + 35, y + 35, width - 69, height - 69);
            g.drawRect(x + 35, y + 35, width - 68, height - 68);
        }
        if(mousePressed){
            g.drawRect(x + 35, y + 35, width - 69, height - 69);
            g.drawRect(x + 35, y + 35, width - 68, height - 68);
            g.drawRect(x + 35, y + 35, width - 67, height - 67);
            g.drawRect(x + 35, y + 35, width - 66, height - 66);
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
