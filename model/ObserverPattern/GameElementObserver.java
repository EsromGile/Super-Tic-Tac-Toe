/*
 *	Last Updated By: Eli
 * 	NOTES: this is just a rough draft for the logic of determining a win or draw 
 * 	P.S. If it's bad please don't yell at me... 
 */

package model.ObserverPattern;
import model.StatePattern.GamePlayerX;
import view.GamePanel;

public class GameElementObserver implements Observer {

	private GamePanel gamePanel;

	public GameElementObserver(GamePanel panel) {
		this.gamePanel = panel;
	}

	@Override
	public void winCondition() {
		if (gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
			gamePanel.setGameState(GamePanel.GameState.X_WIN);
		} else {
			gamePanel.setGameState(GamePanel.GameState.O_WIN);
		}
		gamePanel.getCanvas().repaint();
	}

	@Override
	public void drawCondition() {
		gamePanel.setGameState(GamePanel.GameState.DRAW);
		gamePanel.getCanvas().repaint();
	}
}