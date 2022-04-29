package controller;

import java.io.Serializable;

import model.TicTacToe.TicTacToeSquare;

public class UpdateMarks implements Serializable{
    private static TicTacToeSquare[][] grid = new TicTacToeSquare[5][5];
	private int currentPlayer; 

	public UpdateMarks(TicTacToeSquare[][] grid, int currentPlayer){
		this.grid = grid; 
		this.currentPlayer = currentPlayer; 
	}
    public void setGrid(TicTacToeSquare[][] grid) {
        this.grid = grid;
    }
    public static TicTacToeSquare[][] getGrid() {
        return grid;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
