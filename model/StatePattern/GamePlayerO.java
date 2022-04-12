package model.StatePattern;

public class GamePlayerO implements GamePlayerState {

	@Override
	public void goNext(GamePlayerTurn context) {
		context.setState(new GamePlayerX());			//If the current state is the O player, the next state is the X player
	}

	@Override
	public String stateToString() {
		return "O State";
	}

}
