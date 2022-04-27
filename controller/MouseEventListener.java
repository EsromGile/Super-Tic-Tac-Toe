package controller;

import view.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MouseEventListener implements MouseListener, ActionListener {

	private GamePanel saPanel;

	public MouseEventListener(GamePanel saPanel) {
		this.saPanel = saPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Moved turn code to Player.java
		try {
			if (!saPanel.getGamePlayerTurn().getCanClick())
				return;
			if (saPanel.getManPlayer().takeTurn(e))
				return;
			// AI takes turn after player goes
			saPanel.getAiPlayer().takeTurn();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
