package model.StatePattern;

import java.util.Timer;
import java.util.TimerTask;

import view.GamePanel;

public class GamePlayerTurn {
	private GamePlayerState state;
	private GamePanel gamePanel;
	private boolean canClick;
	private int seconds = 30;			//Player will be allowed 30 seconds per turn

	public GamePlayerTurn(GamePanel gamePanel){ 
		this.gamePanel = gamePanel;
		state = new GamePlayerX(this);			//The X Player starts first
		canClick = gamePanel.getPlayerX();
	}

	public void goNextState() {
		state.goNext(this);
		canClick = !canClick;
	}

	public void setState(GamePlayerState state) {
		this.state = state;
	}
	
	public GamePlayerState getState() {
		return this.state;
	}

	public boolean getCanClick(){
		return canClick;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
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
				gamePanel.getCanvas().setSecondsLeft(seconds);
				seconds--;
				gamePanel.getCanvas().repaint();
			}
	
		}, 0, 1000);
	}
}
