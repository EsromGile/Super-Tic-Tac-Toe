package model;

import java.util.ArrayList;
import java.util.Random;

import org.w3c.dom.events.MouseEvent;

import model.ObserverPattern.Observer;
import model.StatePattern.GamePlayerX;
import view.StandAlonePanel;

public class AI extends GamePlayer {
	private ArrayList<Observer> observers;
	private StandAlonePanel saPanel;

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
		int x = r.nextInt(5);
		int y = r.nextInt(5);
		System.out.println(x + " " + y);
		while(!saPanel.getTicTacToeGame().spotTaken(x, y)){
			x = r.nextInt(5);
			y = r.nextInt(5);
			System.out.println(x + " " + y);
		}
		if((saPanel.getPlayerX() ^ saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX)){
			//Using saPanel for testing, but still need to implement for nPanel
			ArrayList<GameElement> marks = saPanel.getCanvas().getMarks();
		
						// if(!saPanel.getTicTacToeGame().spotTaken(x, y)) return;
							marks.add(new GameElement(saPanel.getTicTacToeGame().getBoundingBox(x, y).x,
														  saPanel.getTicTacToeGame().getBoundingBox(x, y).y, 
														  saPanel.getGamePlayerTurn().getState()));
						if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
							saPanel.getTicTacToeGame().setEntry(x, y, 1);	//Set to X
						} else {
							saPanel.getTicTacToeGame().setEntry(x, y, 2);	//Set to O
						}
			
	
			saPanel.getCanvas().repaint();
	
			//Add code here for checking for a win/draw
			saPanel.getTicTacToeGame().printGameBoard();
			
			//To Do update Observer
			
			
			//After the player places the mark, and the game is still in play, it goes to the next turn
			saPanel.getGamePlayerTurn().goNextState();

			
		}
	}

	public void render() {
		
	}

}
