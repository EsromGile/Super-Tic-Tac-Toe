package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel {
    private JFrame window; 
    public MenuPanel(JFrame window){
        this.window = window; 
    }
    public void init(){
        Container cp = window.getContentPane();
        JPanel northPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        //menuPanel.setLayout(new GridLayout(3,1));
        GridBagLayout layout = new GridBagLayout();
        menuPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        menuPanel.setPreferredSize(new Dimension(400,400));

        JLabel ticTac = new JLabel("Super Tic-Tac-Toe");
        ticTac.setFont(new Font("Courier New", Font.BOLD, 30));
        northPanel.add(ticTac);
       
        gbc.gridx = 0; 
        gbc.gridy = 0;
        JLabel sLabel = new JLabel("Select Mode to Start: ");
        menuPanel.add(sLabel, gbc);

        //gbc.weightx = 1;
        //gbc.weighty = 1;
        gbc.gridx = 0; 
        gbc.gridy = 1;
        JButton standAloneButton = new JButton("Stand Alone");
        menuPanel.add(standAloneButton, gbc);
        gbc.gridx = 0; 
        gbc.gridy = 2;
        JButton networkButton = new JButton("Network");
        menuPanel.add(networkButton, gbc);

        cp.add(BorderLayout.NORTH, northPanel);
        cp.add(BorderLayout.CENTER, menuPanel);

        standAloneButton.addActionListener(event->{
            window.getContentPane().removeAll();
            var panel = new StandAlonePanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });

        networkButton.addActionListener(event ->{
            window.getContentPane().removeAll();
            var panel = new NetworkPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });
        
    }
}
