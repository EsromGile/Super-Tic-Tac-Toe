package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetworkPanel {

    private JFrame window; 
    private TicTacToeCanvas canvas;
    public NetworkPanel(JFrame window){
        this.window = window; 
        window.setTitle("Super Tic-Tac-Toe (AI vs. AI)");
    }

    public void createNetworkPanel(){
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(600,650));
        
        //main tic-tac-toe panel
        JPanel mainPanel = new JPanel();
        canvas = new TicTacToeCanvas(this);
        mainPanel.add(canvas);

        //south panel
        JPanel southPanel = new JPanel(); 
        JLabel ipAdd = new JLabel("Enter IP Address: ");
        southPanel.add(ipAdd);
        JTextField ipAddress = new JTextField(10);
        southPanel.add(ipAddress);
        JButton enterButton = new JButton("Enter");
        southPanel.add(enterButton);
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

    public TicTacToeCanvas getCanvas() {
        return canvas;
    }
}
