package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.EventListener;
import model.Images.ImageStore;

public class MenuPanel {
    private JFrame window;
    private JButton connectNetwork = new JButton("Create Network");
    private JButton connectPeer = new JButton("Connect As Peer");
    private JTextField ipAddress = new JTextField(15);
    private JButton standAloneButton = new JButton("Stand Alone");
    //private JButton networkButton = new JButton("Network");
    private JRadioButton playerOne = new JRadioButton("Player X (Go First)");
    private JRadioButton playerTwo = new JRadioButton("Player O (Go Second)");
    // private boolean network = false;
    private boolean isPeerConnected = false;

    public MenuPanel(JFrame window) {
        this.window = window;
    }

    public void init() {
        Container cp = window.getContentPane();
        JPanel northPanel = new JPanel();
        northPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        JPanel menuPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel playerPanel = new JPanel();
        JPanel networkPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        southPanel.setLayout(new GridLayout(2, 1));
        // menuPanel.setLayout(new GridLayout(3,1));

        southPanel.add(networkPanel);
        southPanel.add(playerPanel);
        GridBagLayout layout = new GridBagLayout();
        menuPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        menuPanel.setPreferredSize(new Dimension(400, 200));
        // north section
        JLabel ticTac = new JLabel("Super Tic-Tac-Toe");
        ticTac.setFont(new Font("Courier New", Font.BOLD, 30));
        northPanel.add(ticTac);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel sLabel = new JLabel("Select Mode to Start: ");
        menuPanel.add(sLabel, gbc);

        // middle section
        gbc.gridx = 1;
        gbc.gridy = 1;

        menuPanel.add(standAloneButton, gbc);
        // gbc.gridx = 1;
        // gbc.gridy = 2;
        // menuPanel.add(networkButton, gbc);

        // bottom section
        GridBagLayout networkLayout = new GridBagLayout();
        networkPanel.setLayout(networkLayout);
        GridBagConstraints ngbc = new GridBagConstraints();

        networkPanel.setBorder(BorderFactory.createTitledBorder("Network(connect peer will begin game): "));
        JLabel player = new JLabel("Enter IP:");
        ngbc.gridx = 0;
        ngbc.gridy = 0;
        networkPanel.add(player, ngbc);
        ngbc.gridx = 0;
        ngbc.gridy = 1;
        networkPanel.add(connectNetwork, ngbc);
        ngbc.gridx = 1;
        ngbc.gridy = 0;
        networkPanel.add(ipAddress, ngbc);
        ngbc.gridx = 1;
        ngbc.gridy = 1;
        networkPanel.add(connectPeer, ngbc);

        GridBagLayout southLayout = new GridBagLayout();
        playerPanel.setLayout(southLayout);
        GridBagConstraints sgbc = new GridBagConstraints();

        sgbc.gridx = 1;
        sgbc.gridy = 0;
        JLabel playerLabel = new JLabel("Choose Your Player:");
        playerPanel.add(playerLabel, sgbc);

        sgbc.gridx = 0;
        sgbc.gridy = 1;
        playerPanel.add(playerOne, sgbc);

        sgbc.gridx = 2;
        sgbc.gridy = 1;
        playerPanel.add(playerTwo, sgbc);

        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(playerOne);
        playerGroup.add(playerTwo);
        playerOne.setSelected(true);

        sgbc.gridx = 0;
        sgbc.gridy = 2;
        JLabel kirbyX = new JLabel(new ImageIcon(ImageStore.xMark));
        playerPanel.add(kirbyX, sgbc);

        sgbc.gridx = 2;
        sgbc.gridy = 2;
        JLabel kirbyO = new JLabel(new ImageIcon(ImageStore.oMark));
        playerPanel.add(kirbyO, sgbc);

        cp.add(BorderLayout.NORTH, northPanel);
        cp.add(BorderLayout.CENTER, menuPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        EventListener listener = new EventListener(this);
        //networkButton.addActionListener(listener);
        connectPeer.addActionListener(listener);
        connectNetwork.addActionListener(listener);
        standAloneButton.addActionListener(listener);

        // standAloneButton.addActionListener(event -> {
        // window.getContentPane().removeAll();
        // var panel = new GamePanel(window, playerOne.isSelected());
        // panel.createStandAlonePanel();
        // window.pack();
        // window.setVisible(true);
        // });

        // networkButton.addActionListener(event -> {

        // });

    }

    public JButton getConnectPeer() {
        return connectPeer;
    }

    public JButton getConnectServer() {
        return connectNetwork;
    }

    public JTextField getIpAddress() {
        return ipAddress;
    }

    

    public JButton getStandAloneButton() {
        return standAloneButton;
    }

    public JFrame getWindow() {
        return window;
    }

    public JRadioButton getPlayerOne() {
        return playerOne;
    }

    public JRadioButton getPlayerTwo() {
        return playerTwo;
    }

   

    public boolean isPeerConnected() {
        return isPeerConnected;
    }

    public void setPeerConnected(boolean isPeerConnected) {
        this.isPeerConnected = isPeerConnected;
    }

}
