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
	private int x = 0 ; 
	private int y = 0 ; 
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
			JFrame window = menuPanel.getWindow();
			window.getContentPane().removeAll();
			var panel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
			panel.createStandAlonePanel();
			window.pack();
			window.setVisible(true);
		} else if (e.getSource() == menuPanel.getNetworkButton()) {

		} else if (e.getSource() == menuPanel.getConnectServer()) {
			try {
				//create GamePanel
				JFrame window = menuPanel.getWindow();
				window.getContentPane().removeAll();
				gamePanel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
				gamePanel.createNetworkPanel(); // needs to be changed to createNetworkPanel
				window.pack();
				window.setVisible(true);
				//run server 
				//gamePanel.setNetwork(true);
				Server.main(null, gamePanel);
				//also create peer
				peerConnect(gamePanel);

			} catch (Exception e2) {
				System.out.println(e2);
			}
		} else if (e.getSource() == menuPanel.getConnectPeer()) {
			JFrame window = menuPanel.getWindow();
			window.getContentPane().removeAll();
			gamePanel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
			gamePanel.createNetworkPanel(); // needs to be changed to createNetworkPanel
			window.pack();
			window.setVisible(true);
			peerConnect(gamePanel);
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
	public void peerConnect(GamePanel panel){ // calls process to connect peer to server
		try {
				String ipAddress = menuPanel.getIpAddress().getText();
				if (panel.getPlayerX()) {
					name = "X";
				} else {
					name = "O";
				}
				String test = "player : "+ name;
				Socket socket = new Socket();
				SocketAddress sa = new InetSocketAddress(ipAddress, 4216);
				socket.connect(sa);
				peer = new Peer(socket, name, panel);
				
				// System.out.println("1. "+test);
				menuPanel.getConnectPeer().setEnabled(false);
				// System.out.println("2. "+test);
				oos = peer.getOos();
				// System.out.println("1. "+test);
				gamePanel.setOos(oos);
				// System.out.println("2. "+test);
				System.out.println(test);
				// if(oos == null) System.out.println("null " + test);
				// else System.out.println("not null " + test);
				oos.writeObject(test);
				oos.reset();
				oos.flush();
				gamePanel.getCanvas().repaint();
                menuPanel.getConnectPeer().setEnabled(false);
                menuPanel.setPeerConnected(true);

		}catch(Exception e){
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
