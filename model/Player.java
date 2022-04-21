package model;
import java.util.ArrayList;

import java.awt.event.MouseEvent;

import model.ObserverPattern.Observer;
import model.StatePattern.GamePlayerX;
import view.StandAlonePanel;

public class Player extends GamePlayer{
	private ArrayList<Observer> observers;
	private StandAlonePanel saPanel;

	public Player(StandAlonePanel saPanel){
		this.saPanel = saPanel;
	}

	public void addGameLIstener(Observer o) {

	}

	public void removeGameLIstener(Observer o) {

	}

	public void notifyObservers(MouseEvent e) {

	}

	//Player takes turn
	public boolean takeTurn(MouseEvent e) {
		ArrayList<GameElement> marks = saPanel.getCanvas().getMarks();
		if(!(saPanel.getPlayerX() ^ saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX)){
			for(int row = 0; row < 5; row++) {
				for(int column = 0; column < 5; column++) {
					if(saPanel.getTicTacToeGame().getBoundingBox(row, column).contains(e.getX(), e.getY())) {
						if(!saPanel.getTicTacToeGame().spotTaken(row, column)) return true;
						marks.add(new GameElement(saPanel.getTicTacToeGame().getBoundingBox(row, column).x + 5,
												saPanel.getTicTacToeGame().getBoundingBox(row, column).y + 5, 
												saPanel.getGamePlayerTurn().getState()));
						if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
							saPanel.getTicTacToeGame().setEntry(row, column, 1);	//Set to X
						} else {
							saPanel.getTicTacToeGame().setEntry(row, column, 2);	//Set to O
						}

						saPanel.getCanvas().repaint();
						saPanel.getTicTacToeGame().printGameBoard();					
						//To Do update Observer
						
						//After the player places the mark, and the game is still in play, it goes to the next turn
						saPanel.getGamePlayerTurn().goNextState();
			
					}
				}
			}
		}
		return false;
	}

	public void render() {
		
	}

}
