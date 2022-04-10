package model;

import java.util.ArrayList;

public class TicTacToe {
	private int[][] grid = new int[5][5];
	/*
		0 is empty
		1 is X
		2 is O
	*/

	public TicTacToe() {
		// all values of grid initialized to 0 (empty)
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				grid[row][col] = 0;
			}
		}
	}

	public void render() {
		
	}
	
}
