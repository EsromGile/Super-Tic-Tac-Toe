/*
 *	Last Updated By: Eli
 * 	NOTES: this is just a rough draft for the logic of determining a win or draw 
 * 	P.S. If it's bad please don't yell at me... 
 */

package model.ObserverPattern;
import model.StatePattern.GamePlayerState;
import model.StatePattern.GamePlayerX;
import view.GamePanel;

public class GameElementObserver implements Observer {

	private GamePanel gamePanel;

	public GameElementObserver(GamePanel panel) {
		this.gamePanel = panel;
	}

	@Override
	public void winCondition() {
		GamePlayerState state = gamePanel.getGamePlayerTurn().getState();
		
		if (state instanceof GamePlayerX) {
			System.out.println("X won for some reason");
		} else {
			System.out.println("O won");
		}
	}

	@Override
	public void drawCondition() {
		System.out.println("Draw");
	}
}