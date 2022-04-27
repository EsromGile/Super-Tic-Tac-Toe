package controller;

import java.util.LinkedList;

import javax.swing.JFrame;

import view.GamePanel;
import view.MenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

enum EventType {
	// event types
};

public class EventListener implements ActionListener {
	private LinkedList<EventType> eventQueue;
	private GamePanel gamePanel;
	private MenuPanel menuPanel;

	private OutputStream os;
	private ObjectOutputStream oos;
	private int x = 0;
	private int y = 0;
	private Peer peer;
	private String name = "";
	private Socket socket = null;

	public EventListener(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		eventQueue = new LinkedList<>();
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
				// JFrame window = menuPanel.getWindow();
				// window.getContentPane().removeAll();
				// gamePanel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
				// // addPeer();
				// gamePanel.createNetworkPanel(); // needs to be changed to createNetworkPanel
				// window.pack();
				// window.setVisible(true);
				// run server
				Server.main(null, gamePanel);
				// also create peer
				addPeer();

			} catch (Exception e2) {
				System.out.println(e2);
			}
		} else if (e.getSource() == menuPanel.getConnectPeer()) {
			try {
				callNetwork();
				addPeer();

			} catch (Exception e3) {
				System.out.println(e3);
			}
		}
	}

	public void processEventQueue() {

	}

	public void update() {

	}

	public void statTimer() {

	}

	public String getName() {
		return name;
	}
	public void callNetwork() {
	try {
	// create game panel
	JFrame window = menuPanel.getWindow();
	window.getContentPane().removeAll();
	gamePanel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
	//addPeer();
	gamePanel.createNetworkPanel(); // needs to be changed to createNetworkPanel
	window.pack();
	window.setVisible(true);

	} catch (Exception e) {
	System.out.println(e);
	}
	}

	public void addPeer() {
		try {

			System.out.println("1 ");
			// get player x or o
			if (!gamePanel.getPlayerX()) {
				name = "X";
			} else {
				name = "O";
			}

			// get info for socket and connect socket
			System.out.println("2");
			String ipAddress = menuPanel.getIpAddress().getText();
			Socket socket = new Socket();
			SocketAddress sa = new InetSocketAddress(ipAddress, 4216);
			socket.connect(sa);

			System.out.println("3");
			// create peer
			peer = new Peer(socket, name, gamePanel);
			gamePanel.setPeer(peer);

			System.out.println("4");
			menuPanel.getConnectPeer().setEnabled(false);
			oos = peer.getOos();
			gamePanel.setOos(oos);
			System.out.println(name);
			// if(oos == null) System.out.println("null " + test);
			// else System.out.println("not null " + test);
			oos.writeObject(name);
			oos.reset();
			oos.flush();
			gamePanel.getCanvas().repaint();
			menuPanel.getConnectPeer().setEnabled(false);
			menuPanel.setPeerConnected(true);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Socket getSocket() {
		return socket;
	}

	private void sendXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	private int getX() {
		return x;
	}

	private int getY() {
		return y;
	}
}
