package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StandAlonePanel {

    private JFrame window; 
    private TicTacToeCanvas canvas; 
    public StandAlonePanel(JFrame window){
        this.window = window;
    }

    public void init(){
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(400,450));
        
        //main tic-tac-toe panel
        JPanel mainPanel = new JPanel();
        canvas = new TicTacToeCanvas(this);
        mainPanel.add(canvas);

        //south panel 
        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Back"); 
        southPanel.add(backButton);

        cp.add(BorderLayout.CENTER, mainPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        //action listener
        backButton.addActionListener(event->{
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });

    }

}
