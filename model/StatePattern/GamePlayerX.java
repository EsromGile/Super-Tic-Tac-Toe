package model.StatePattern;

public class GamePlayerX implements GamePlayerState {

	@Override
	public void goNext(GamePlayerTurn context) {
		context.setState(new GamePlayerO());			//If the current state is the X player, the next state is the O player
	}

	@Override
	public String stateToString() {
		return "X State";
	}
}
