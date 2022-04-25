package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.Images.ImageStore;

public class MenuPanel {
    private JFrame window; 
    public MenuPanel(JFrame window){
        this.window = window; 
    }
    public void init(){
        Container cp = window.getContentPane();
        JPanel northPanel = new JPanel();
        northPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        JPanel menuPanel = new JPanel();
        JPanel southPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        //menuPanel.setLayout(new GridLayout(3,1));
        
        GridBagLayout layout = new GridBagLayout();
        menuPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        menuPanel.setPreferredSize(new Dimension(400, 200));

        JLabel ticTac = new JLabel("Super Tic-Tac-Toe");
        ticTac.setFont(new Font("Courier New", Font.BOLD, 30));
        northPanel.add(ticTac);
       
        gbc.gridx = 1; 
        gbc.gridy = 0;
        JLabel sLabel = new JLabel("Select Mode to Start: ");
        menuPanel.add(sLabel, gbc);

        //gbc.weightx = 1;
        //gbc.weighty = 1;
        gbc.gridx = 1; 
        gbc.gridy = 1;
        JButton standAloneButton = new JButton("Stand Alone");
        menuPanel.add(standAloneButton, gbc);
        gbc.gridx = 1; 
        gbc.gridy = 2;
        JButton networkButton = new JButton("Network");
        menuPanel.add(networkButton, gbc);

        GridBagLayout southLayout = new GridBagLayout();
        southPanel.setLayout(southLayout);
        GridBagConstraints sgbc = new GridBagConstraints();

        sgbc.gridx = 1; 
        sgbc.gridy = 0;
        JLabel playerLabel = new JLabel("Choose Your Player:");
        southPanel.add(playerLabel, sgbc);

        sgbc.gridx = 0; 
        sgbc.gridy = 1;
        JRadioButton playerOne = new JRadioButton("Player X (Go First)");
        southPanel.add(playerOne, sgbc);

        sgbc.gridx = 2; 
        sgbc.gridy = 1;
        JRadioButton playerTwo = new JRadioButton("Player O (Go Second)");
        southPanel.add(playerTwo, sgbc);

        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(playerOne);
        playerGroup.add(playerTwo);
        playerOne.setSelected(true);

        sgbc.gridx = 0; 
        sgbc.gridy = 2;
        JLabel kirbyX = new JLabel(new ImageIcon(ImageStore.xMark));
        southPanel.add(kirbyX, sgbc);

        sgbc.gridx = 2; 
        sgbc.gridy = 2;
        JLabel kirbyO = new JLabel(new ImageIcon(ImageStore.oMark));
        southPanel.add(kirbyO, sgbc);

        cp.add(BorderLayout.NORTH, northPanel);
        cp.add(BorderLayout.CENTER, menuPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        standAloneButton.addActionListener(event->{
            window.getContentPane().removeAll();
            var panel = new GamePanel(window, playerOne.isSelected());
            panel.createStandAlonePanel();
            window.pack();
            window.setVisible(true);
        });

        networkButton.addActionListener(event ->{
            window.getContentPane().removeAll();
            var panel = new GamePanel(window, playerOne.isSelected());
            panel.createStandAlonePanel();
            window.pack();
            window.setVisible(true);
        });
        
    }
}
