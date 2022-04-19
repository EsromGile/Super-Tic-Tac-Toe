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

		// initial values of x and y
		int x = 100;
		int y = 100;

		// all values of grid initialized to 0 (empty)
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				grid[row][col] = new TicTacToeSquare();
				grid[row][col].entry = 0;
				grid[row][col].boundingBox = new Rectangle(x, y, 80, 80);
				x += 80;	// move to next column
			}

			x = 100;		// reset x to first column when we move to the next row
			y += 80;		// move to next row
		}
	}


	public Rectangle getBoundingBox(int row, int column) {
		return grid[row][column].boundingBox;
	}

	public boolean isEmpty(int row, int column) {
		if(grid[row][column].entry == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setEntry(int row, int column, int newEntry) {
		grid[row][column].entry = newEntry;
	}

	public void printGameBoard() {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				System.out.print("[" + grid[row][col].entry + "]");
			}
			System.out.println();
		}
	}
	
	public TicTacToeSquare[][] getGrid() {
		return grid;
	}
}
