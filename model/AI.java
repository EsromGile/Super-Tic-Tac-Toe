package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.w3c.dom.events.MouseEvent;

import model.ObserverPattern.Observer;
import model.StatePattern.GamePlayerX;
import view.StandAlonePanel;

public class AI extends GamePlayer {
	private ArrayList<Observer> observers;
	private StandAlonePanel saPanel;
	private int seconds;
	private int x;
	private int y;

	public AI(StandAlonePanel saPanel){
		this.saPanel = saPanel;
	}

	public void addGameLIstener(Observer o) {

	}

	public void removeGameLIstener(Observer o) {

	}

	public void notifyObservers(MouseEvent e) {

	}

	public void takeTurn() {
		// Sample "AI" Turn
		Random r = new Random();
		x = r.nextInt(5);
		y = r.nextInt(5);

		while(!saPanel.getTicTacToeGame().spotTaken(x, y)){
			x = r.nextInt(5);
			y = r.nextInt(5);
		}

		if((saPanel.getPlayerX() ^ saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX)) {

			ArrayList<GameElement> marks = saPanel.getCanvas().getMarks();

			//Adds a 1.5 second delay before the AI places its mark
			Timer timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					marks.add(new GameElement(saPanel.getTicTacToeGame().getBoundingBox(x, y).x + 5,
										saPanel.getTicTacToeGame().getBoundingBox(x, y).y + 5, 
										saPanel.getGamePlayerTurn().getState()));
					if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
						saPanel.getTicTacToeGame().setEntry(x, y, 1);	//Set to X
					} else {
						saPanel.getTicTacToeGame().setEntry(x, y, 2);	//Set to O
					}
					timer1.cancel();
					timer1.purge();
				}
			}, 1500);
	
			saPanel.getCanvas().repaint();
	
			//Add code here for checking for a win/draw
			saPanel.getTicTacToeGame().printGameBoard();

			saPanel.getTicTacToeGame().checkWin();
			saPanel.getTicTacToeGame().checkDraw();
			
			//To Do update Observer
			
			
			//After the AI places mark, delay for 5 seconds, then go to next state
			Timer timer2 = new Timer();

			timer2.schedule(new TimerTask() {
				@Override
				public void run() {
					saPanel.getGamePlayerTurn().goNextState();
					System.out.print("Timer Ended");
					timer2.cancel();
					timer2.purge();
				}
			}, 5000L);

			saPanel.getCanvas().repaint();

			//saPanel.getGamePlayerTurn().goNextState();
			
		}
	}

	public void render() {
		
	}

}
