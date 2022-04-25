package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.Images.ImageStore;
import model.StatePattern.GamePlayerState;
import model.StatePattern.GamePlayerX;

//This class is mainly used to render the X's and O's
public class GameElement {
    private int x;
    private int y;
    private BufferedImage image;

    public GameElement(int x, int y, GamePlayerState state) {
        this.x = x;
        this.y = y;

        //If it is the X player's turn, then the mark rendered is the xMark, otherwise use the oMark
        if(state instanceof GamePlayerX) {
            setImage(ImageStore.xMark);
        }
        else {
            setImage(ImageStore.oMark);
        }
    }

    public void render(Graphics2D g2) {
        g2.drawImage(getImage(), null, getX(), getY());
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
