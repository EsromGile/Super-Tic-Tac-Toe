package model.StatePattern;

import view.NetworkPanel;
import view.StandAlonePanel;

public class GamePlayerTurn {
	private GamePlayerState state;
	private StandAlonePanel saPanel;
	private NetworkPanel nPanel;

	public void goNextState() {

	}

	public void setState(GamePlayerState state) {
		this.state = state;
	}
	
	public GamePlayerState getState() {
		return this.state;
	}

	public StandAlonePanel getSaPanel() {
		return saPanel;
	}

	public NetworkPanel getnPanel() {
		return nPanel;
	}

}
