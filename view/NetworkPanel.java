package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetworkPanel {

    private JFrame window; 
    public NetworkPanel(JFrame window){
        this.window = window; 
    }

    public void init(){
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(400,450));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,5));
        JPanel southPanel = new JPanel(); 
        JLabel ipAdd = new JLabel("Enter IP Address: ");
        JTextField ipAddress = new JTextField(10);
        southPanel.add(ipAdd);
        southPanel.add(ipAddress);

        cp.add(BorderLayout.CENTER, mainPanel);
        cp.add(BorderLayout.SOUTH, southPanel);
    }
}
