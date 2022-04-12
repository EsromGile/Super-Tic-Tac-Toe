package model.StatePattern;

public interface GamePlayerState {
	void goNext(GamePlayerTurn context);
    String stateToString();  //For testing purposes
}
