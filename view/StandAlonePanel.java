package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class StandAlonePanel {

    private JFrame window; 
    public StandAlonePanel(JFrame window){
        this.window = window;
    }

    public void init(){
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(400,450));

    }

}
