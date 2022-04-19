package model;

import java.awt.Rectangle;
import java.util.ArrayList;


public class TicTacToe {

	public class TicTacToeSquare {
		private Rectangle boundingBox; 		//boundingBox refers to the area on the canvas that the square covers
		private int entry;					//entry refers to the mark (or lack of) that is in the square
		/*
			0 is empty
			1 is X
			2 is O
		*/
	}

	private TicTacToeSquare[][] grid = new TicTacToeSquare[5][5];

	public TicTacToe() {
		// all values of grid initialized to 0 (empty)
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				grid[row][col].entry = 0;
			}
		}
	}


	public void render() {
		
	}
	
}
