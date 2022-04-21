package controller;

import view.NetworkPanel;
import view.StandAlonePanel;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.GameElement;
import model.StatePattern.GamePlayerO;
import model.StatePattern.GamePlayerX;

public class MouseEventListener implements MouseListener, ActionListener {
	
	private StandAlonePanel saPanel;
	private NetworkPanel nPanel;

	public MouseEventListener(StandAlonePanel saPanel) {
		this.saPanel = saPanel;
	}

	public MouseEventListener(NetworkPanel nPanel) {
		this.nPanel = nPanel;
	}

	public void checkForWin() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//Using saPanel for testing, but still need to implement for nPanel
		ArrayList<GameElement> marks = saPanel.getCanvas().getMarks();


		/* 
			Note: the commented out if statement is to not allow the player to click when it's the AI's turn,
			but since we haven't implemented the AI yet, I have it commented out, so we can still do some testing
			and not have the game freeze.
			-Viviam
		*/
		// //If the player is X and it is X's turn, or if the player is O and it is O's turn, allow the player to click
		// if((saPanel.getGamePlayer().xPlayer == true && saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX) ||
		//    (saPanel.getGamePlayer().xPlayer == false && saPanel.getGamePlayerTurn().getState() instanceof GamePlayerO)) {

			for(int row = 0; row < 5; row++) {
				for(int column = 0; column < 5; column++) {
					if(saPanel.getTicTacToeGame().getBoundingBox(row, column).contains(e.getX(), e.getY())) {
						if(!saPanel.getTicTacToeGame().spotTaken(row, column)) return;
						marks.add(new GameElement(saPanel.getTicTacToeGame().getBoundingBox(row, column).x + 5,
												saPanel.getTicTacToeGame().getBoundingBox(row, column).y + 5, 
												saPanel.getGamePlayerTurn().getState()));
						if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
							saPanel.getTicTacToeGame().setEntry(row, column, 1);	//Set to X
						} else {
							saPanel.getTicTacToeGame().setEntry(row, column, 2);	//Set to O
						}
					}
				}
			}

			saPanel.getCanvas().repaint();

			//Add code here for checking for a win/draw
			saPanel.getTicTacToeGame().printGameBoard();
			
			//After the player places the mark, and the game is still in play, it goes to the next turn
			saPanel.getGamePlayerTurn().goNextState();
		//}

	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void actionPerformed(ActionEvent e) { }


}
