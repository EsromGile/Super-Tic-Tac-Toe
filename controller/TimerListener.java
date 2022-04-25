package controller;

import java.util.LinkedList;

import view.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum  EventType{
	// event types
};

public class TimerListener implements ActionListener {
	private LinkedList<EventType> eventQueue;
	private GamePanel gamePanel;

	public TimerListener(GamePanel gamePanel){
		this.gamePanel= gamePanel;
		eventQueue = new LinkedList<>();
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void processEventQueue() {

	}

	public void update() {
		
	}

	public void statTimer() {
		
	}
}
