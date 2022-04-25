package controller;

import view.StandAlonePanel;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.GameElement;
import model.StatePattern.GamePlayerO;
import model.StatePattern.GamePlayerState;
import model.StatePattern.GamePlayerX;

public class MouseEventListener implements MouseListener, ActionListener {
	
	private StandAlonePanel saPanel;

	public MouseEventListener(StandAlonePanel saPanel) {
		this.saPanel = saPanel;
	}


	public void checkForWin() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Moved turn code to Player.java
		if(!saPanel.getGamePlayerTurn().getCanClick()) return;
		if(saPanel.getManPlayer().takeTurn(e)) return;
		//AI takes turn after player goes
		saPanel.getAiPlayer().takeTurn();
		
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
