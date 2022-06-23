package view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.EventListener;
import model.Images.ImageStore;

public class MenuPanel {
    private JFrame window;
    private JButton connectNetwork = new JButton("Create Server");
    private JButton connectPeer = new JButton("Join a Server");
    private JTextField ipAddress = new JTextField(15);
    private JButton standAloneButton = new JButton("Stand Alone Mode");
    // private JButton networkButton = new JButton("Network Mode");
    private JButton networkButton = new JButton("Network Mode (Unavailable)");
    private JRadioButton playerOne = new JRadioButton("Player X (Go First)");
    private JRadioButton playerTwo = new JRadioButton("Player O (Go Second)");
    private EventListener listener = new EventListener(this);

    public MenuPanel(JFrame window) {
        this.window = window;
        window.setLocation(400, 100);
        window.setTitle("Menu Screen");
        window.setPreferredSize(new Dimension(500, 500));
    }

    public void init() {
        Container cp = window.getContentPane();
        JPanel northPanel = new JPanel();
        northPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        JPanel menuPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel playerPanel = new JPanel();
        southPanel.setBorder(new EmptyBorder(0, 0, 50, 0));

        southPanel.add(playerPanel);
        GridBagLayout layout = new GridBagLayout();
        menuPanel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        menuPanel.setPreferredSize(new Dimension(400, 200));
        // north section
        JLabel ticTac = new JLabel("Super Tic-Tac-Toe");
        ticTac.setFont(new Font("Courier New", Font.BOLD, 30));
        northPanel.add(ticTac);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel sLabel = new JLabel("Select Mode to Start: ");
        menuPanel.add(sLabel, gbc);

        // middle section
        gbc.gridx = 0;
        gbc.gridy = 1;
        menuPanel.add(standAloneButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        menuPanel.add(networkButton, gbc);

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

        standAloneButton.addActionListener(listener);

        networkButton.addActionListener(event -> {
            showNetworkOptions();
        });

        // disable network button
        networkButton.setEnabled(false);

    }

    public void showNetworkOptions() {
        window.getContentPane().removeAll();
        window.setTitle("Network Options");
        window.setPreferredSize(new Dimension(400, 200));

        JPanel networkPanel = new JPanel();

        GridBagLayout networkLayout = new GridBagLayout();
        networkPanel.setLayout(networkLayout);
        GridBagConstraints ngbc = new GridBagConstraints();

        JLabel player = new JLabel("Enter IP Address:");
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

        connectPeer.addActionListener(listener);
        connectNetwork.addActionListener(listener);

        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Back");
        southPanel.add(backButton);

        backButton.addActionListener(event -> {
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });

        Container cp = window.getContentPane();
        cp.add(networkPanel, BorderLayout.CENTER);
        cp.add(BorderLayout.SOUTH, southPanel);

        window.pack();
        window.setVisible(true);

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

}
