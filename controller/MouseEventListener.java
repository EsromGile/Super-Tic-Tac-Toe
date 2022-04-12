package controller;

import view.NetworkPanel;
import view.StandAlonePanel;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.GameElement;

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
		System.out.println("X: " + e.getX() + ", Y: " + e.getY());
		//Using saPanel for testing, but still need to implement for nPanel
		ArrayList<GameElement> marks = saPanel.getCanvas().getMarks();

		//Add code here for checking which grid position the mouse clicked within, and adding the mark into the array

		//This code currently places the mark wherever the mouse clicks, 
		//but in the future, the mark should be placed in the middle fo the grid position that the mouse clicked within
		marks.add(new GameElement(e.getX() - 35, e.getY() - 35, saPanel.getGamePlayerTurn().getState()));
		System.out.println(saPanel.getGamePlayerTurn().getState().stateToString());
		saPanel.getCanvas().repaint();

		//Add code here for checking for a win/draw
		
		//After the player places the mark, and the game is still in play, it goes to the next turn
		saPanel.getGamePlayerTurn().goNextState();

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
