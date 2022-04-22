/*
 *	Last Updated By: Eli
 * 	NOTES: this is just a rough draft for the logic of determining a win or draw 
 * 	P.S. If it's bad please don't yell at me... 
 */

package model.ObserverPattern;

import model.TicTacToe;
import view.StandAlonePanel;

public class GameElementObserver implements Observer {

	private StandAlonePanel saPanel;

	public GameElementObserver(StandAlonePanel panel) {
		this.saPanel = panel;
	}

	@Override
	public void winCondition() {
		System.out.println("Someone won");
		System.exit(1);
	}

	@Override
	public void drawCondition() {
		System.out.println("Draw");
		System.exit(0);
	}
}