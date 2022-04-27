package model;

import java.io.Serializable;

public abstract class GamePlayer implements Serializable {
	public boolean xPlayer;

	public abstract void takeTurn();
}
