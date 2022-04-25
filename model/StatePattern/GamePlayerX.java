package model.StatePattern;

public class GamePlayerX implements GamePlayerState {

	public GamePlayerX(GamePlayerTurn context) {
		if(context.getGamePanel().getPlayerX())				//Start countdown if player is X
			context.startTurnCountdown();
	}

	@Override
	public void goNext(GamePlayerTurn context) {
		context.setState(new GamePlayerO(context));			//If the current state is the X player, the next state is the O player
	}

	@Override
	public String stateToString() {
		return "X State";
	}
}
