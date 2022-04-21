package controller;

import java.util.LinkedList;

import view.NetworkPanel;
import view.StandAlonePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum  EventType{
	// event types
};

public class TimerListener implements ActionListener {
	private LinkedList<EventType> eventQueue;
	private StandAlonePanel saPanel;
	private NetworkPanel nPanel;

	public TimerListener(StandAlonePanel saPanel){
		this.saPanel= saPanel;
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
