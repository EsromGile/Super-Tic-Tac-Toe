package model.StatePattern;

public class GamePlayerO implements GamePlayerState {

	public GamePlayerO(GamePlayerTurn context) {
		context.startTurnCountdown();
	}

	@Override
	public void goNext(GamePlayerTurn context) {
		context.setState(new GamePlayerX(context));			//If the current state is the O player, the next state is the X player
	}

	@Override
	public String stateToString() {
		return "O State";
	}

}
