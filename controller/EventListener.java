package controller;

import javax.swing.JFrame;

import view.GamePanel;
import view.MenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class EventListener implements ActionListener {
	private GamePanel gamePanel;
	private MenuPanel menuPanel;

	public EventListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public EventListener(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuPanel.getStandAloneButton()) {
			try {
				JFrame window = menuPanel.getWindow();
				window.getContentPane().removeAll();
				var panel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
				panel.createStandAlonePanel();
				window.pack();
				window.setVisible(true);
			} catch (Exception e1) {
				System.out.println(e1);
			}
		} else if (e.getSource() == menuPanel.getConnectServer()) {
			try {
				// create GamePanel
				callNetwork();
				Server.main(null, gamePanel);
				gamePanel.setServerPeer(true);

			} catch (Exception e2) {
				System.out.println(e2);
			}
		} else if (e.getSource() == menuPanel.getConnectPeer()) {
			try {
				callNetwork();
				String ipAddress = menuPanel.getIpAddress().getText();
				Socket socket = new Socket();
				SocketAddress sa = new InetSocketAddress(ipAddress, 4216);
				socket.connect(sa);
				System.out.print(socket.isConnected());

				Peer peer = new Peer(socket, gamePanel);
				gamePanel.getCanvas().repaint();
				gamePanel.setServerPeer(false);

			} catch (Exception e3) {
				System.out.println(e3);
			}
		}
	}

	public void callNetwork() {
		try {
			// create game panel
			JFrame window = menuPanel.getWindow();
			window.getContentPane().removeAll();
			gamePanel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
			gamePanel.createNetworkPanel();
			window.pack();
			window.setVisible(true);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
