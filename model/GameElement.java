package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import model.Images.ImageStore;

//This class is mainly used to render the X's and O's
public class GameElement {
    private int x;
    private int y;
    private BufferedImage image;

    public GameElement(int x, int y, boolean xPlayer) {
        this.x = x;
        this.y = y;

        //If it is the X player, then the mark rendered is the xMark, otherwise use the oMark
        if(xPlayer == true) {
            setImage(ImageStore.xMark);
        }
        else {
            setImage(ImageStore.oMark);
        }
    }

    public void render(Graphics2D g2) {
        g2.drawImage(getImage(), null, getX(), getX());
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
