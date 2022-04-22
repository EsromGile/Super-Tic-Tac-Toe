package model.StatePattern;

import java.util.Timer;
import java.util.TimerTask;

import view.NetworkPanel;
import view.StandAlonePanel;

public class GamePlayerTurn {
	private GamePlayerState state;
	private StandAlonePanel saPanel;
	private NetworkPanel nPanel;
	private int seconds = 30;			//Player will be allowed 30 seconds per turn

	public GamePlayerTurn(StandAlonePanel saPanel){ 
		this.saPanel = saPanel;
		state = new GamePlayerX(this);			//The X Player starts first
	}

	public void goNextState() {
		state.goNext(this);
	}

	public void setState(GamePlayerState state) {
		this.state = state;
	}
	
	public GamePlayerState getState() {
		return this.state;
	}

	public StandAlonePanel getSAPanel() {
		return saPanel;
	}

	public NetworkPanel getNPanel() {
		return nPanel;
	}

	public void startTurnCountdown() {
		seconds = 30;

		Timer timer = new Timer();

		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(seconds <= 0) {
					timer.cancel();
					timer.purge();
				}
				saPanel.getCanvas().setSecondsLeft(seconds);
				seconds--;
				saPanel.getCanvas().repaint();
			}
	
		}, 0, 1000);

	}

}
