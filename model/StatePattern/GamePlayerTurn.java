package model.StatePattern;

import java.util.Timer;
import java.util.TimerTask;

import view.GamePanel;
import view.GamePanel.GameState;

public class GamePlayerTurn {
	private GamePlayerState state;
	private GamePanel gamePanel;
	private boolean canClick;
	private int seconds = 30;			//Player will be allowed 30 seconds per turn
	Timer timer = new Timer();
	TimerTask task;

	public GamePlayerTurn(GamePanel gamePanel){ 
		this.gamePanel = gamePanel;
		state = new GamePlayerX(this);			//The X Player starts first
		canClick = gamePanel.getPlayerX();
		timer = new Timer();
	}

	public void goNextState() {
		state.goNext(this);
		canClick = !canClick;
		if(!canClick && !gamePanel.isNetwork()){
			timer.cancel();
			task.cancel();
		}
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
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				
				if(seconds <= 0) {
					if(gamePanel.getGameState() == GameState.PLAYING)
						gamePanel.setGameState(GameState.TIMEOUT);
					timer.cancel();
					timer.purge();
				}
				gamePanel.getCanvas().setSecondsLeft(seconds);
				seconds--;
				gamePanel.getCanvas().repaint();
			}
		};
		timer.schedule(task, 0, 1000);

	}
}
