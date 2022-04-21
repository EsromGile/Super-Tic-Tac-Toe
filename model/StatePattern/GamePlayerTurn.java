package model.StatePattern;

import java.util.Timer;
import java.util.TimerTask;

import view.NetworkPanel;
import view.StandAlonePanel;

public class GamePlayerTurn {
	private GamePlayerState state;
	private StandAlonePanel saPanel;
	private NetworkPanel nPanel;
	private int seconds;

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
		/* 
			Player will be allowed 30 seconds per turn.
			AI will be allowed 5 seconds per turn.
		*/


		/*
			I have absolutely no idea why, but the times have to be swapped for it to work???????
			-Vivian
		*/
		if(saPanel.getGamePlayer().xPlayer == true) {		//If the player is X Player
			if(state instanceof GamePlayerX) {				//and it is X Player's turn
				seconds = 5;
			}
			else {											//O Player's turn
				seconds = 30;
			}
		}
		else {												//If the player is the O Player
			if(state instanceof GamePlayerX) {				// and it is X Player's turn
				seconds = 30;
			}
			else {											//O Player's turn
				seconds = 5;
			}
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				saPanel.getCanvas().setSecondsLeft(seconds);
				seconds--;
				saPanel.getCanvas().repaint();
			}
			
		}, 0, 2500);

	}

}
