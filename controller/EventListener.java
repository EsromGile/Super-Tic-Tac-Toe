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
	private Peer peer;
	private String name = "";

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
				var panel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
				panel.createStandAlonePanel(); // needs to be changed to createNetworkPanel
				window.pack();
				window.setVisible(true);
				//run server 
				Server.main(null, panel);
				//also create peer
				peerConnect(panel);

			} catch (Exception e2) {
			}
		} else if (e.getSource() == menuPanel.getConnectPeer()) {
			JFrame window = menuPanel.getWindow();
			window.getContentPane().removeAll();
			var panel = new GamePanel(window, menuPanel.getPlayerOne().isSelected());
			panel.createStandAlonePanel(); // needs to be changed to createNetworkPanel
			window.pack();
			window.setVisible(true);
			peerConnect(panel);
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
	public void peerConnect(GamePanel panel){
		try {
				String ipAddress = menuPanel.getIpAddress().getText();
				if (panel.getPlayerX()) {
					name = ")(";
				} else {
					name = "()";
				}
				Socket socket = new Socket();
				SocketAddress sa = new InetSocketAddress(ipAddress, 4216);
				socket.connect(sa);
				peer = new Peer(socket, name, panel);
				menuPanel.getConnectPeer().setEnabled(false);
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os);
		}catch(Exception e){
			
		}
	}
}
