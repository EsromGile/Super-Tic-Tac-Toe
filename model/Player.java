package model;
import java.util.ArrayList;

import java.awt.event.MouseEvent;

import model.StatePattern.GamePlayerX;
import view.GamePanel;

public class Player {
	private GamePanel gamePanel;

	public Player(GamePanel gamePanel){
		this.gamePanel = gamePanel;
	}

	//Player takes turn
	public boolean takeTurn(MouseEvent e) {
		ArrayList<GameElement> marks = gamePanel.getCanvas().getMarks();
		if(!(gamePanel.getPlayerX() ^ gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX)){
			for(int row = 0; row < 5; row++) {
				for(int column = 0; column < 5; column++) {
					if(gamePanel.getTicTacToeGame().getBoundingBox(row, column).contains(e.getX(), e.getY())) {
						if(!gamePanel.getTicTacToeGame().spotTaken(row, column)) return true;
						marks.add(new GameElement(gamePanel.getTicTacToeGame().getBoundingBox(row, column).x + 5,
												gamePanel.getTicTacToeGame().getBoundingBox(row, column).y + 5, 
												gamePanel.getGamePlayerTurn().getState()));
						if(gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
							gamePanel.getTicTacToeGame().setEntry(row, column, 1);	//Set to X
						} else {
							gamePanel.getTicTacToeGame().setEntry(row, column, 2);	//Set to O
						}

						gamePanel.getCanvas().repaint();
						//gamePanel.getTicTacToeGame().printGameBoard();					
						
						gamePanel.getTicTacToeGame().checkWin();
						gamePanel.getTicTacToeGame().checkDraw();
						
						//After the player places the mark, and the game is still in play, it goes to the next turn
						gamePanel.getGamePlayerTurn().goNextState();
			
					}
				}
			}
		}
		return false;
	}
}
