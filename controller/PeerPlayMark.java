package controller;

import java.io.Serializable;

public class PeerPlayMark implements Serializable{
    private int x; 
    private int y; 
    public PeerPlayMark(int x, int y){
        this.x = x; 
        this.y = y; 
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
