package model.ObserverPattern;

import model.TicTacToe;
import model.TicTacToe.TicTacToeSquare;

public class GameElementObserver {

	// auxilary attributes
	enum WinningLine {
		leftDiag, rightDiag, row, col, none
	};
	int winningColOrRow = 0;

	// just to test logic -- import real grid later
	private int[][] grid = new int[5][5];

	public boolean WinDetected() {

		if (ConnectionDetected() != WinningLine.none) {
			// mark on board acording to row and col recorded
			return true;
		} else
			return false;
	}

	public WinningLine ConnectionDetected() {

		int leftDiag, rightDiag, row, col;

		// check diagonals
		leftDiag = checkLeftDiagonal();
		rightDiag = checkRightDiagonal();

		if (leftDiag > 1) {
			return WinningLine.leftDiag;
		} else if (rightDiag > 1) {
			return WinningLine.rightDiag;

		// check rows and cols
		} else {
			for (int index = 0; index < 5; index++) {
				row = checkRow(index);
				col = checkColumn(index);
				if (row > 0) {
					this.winningColOrRow = row;
					return WinningLine.row;
				}
				if (col > 0) {
					this.winningColOrRow = col;
					return WinningLine.col;
				}
			}	
		}
		// no winning lines
		return WinningLine.none;
	}

	public boolean DrawDetected() {
		// run after checking for winning lines -- draw can only happen if the board is full
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (grid[row][col] == 0) 
					return false;
			}
		}
		return true;
	}


	/*---------------------
		auxilary methods
	----------------------*/

	private int checkLeftDiagonal() {

		//1 = x wins, 2 = o wins, 0 = no winner
		int countX = 0;		//1
		int countO = 0;		//2

		for (int index = 0; index < 5; index++) {
			if (grid[index][index] == 1) {
				countX++;
			} else if (grid[index][index] == 2) {
				countO++;
			} else {
				return 0;	// need full line -- no line here
			}
		}

		return countO > countX ? countO : countX;	// return O or X
	
	}

	private int checkRightDiagonal() {

		//1 = x wins, 2 = o wins, 0 = no winner
		int countX = 0;		//1
		int countO = 0;		//2

		for (int index = 0; index < 5; index++) {
			if (grid[index][5 - index] == 1) {
				countX++;
			} else if (grid[index][5 - index] == 2) {
				countO++;
			} else {
				return 0; // need full line -- no line here
			}
		}

		return countO > countX ? countO : countX;	// return O or X
	}

	private int checkRow(int rowNumber) {

		int countX = 0;		//1
		int countO = 0;		//2

		for (int index = 0; index < 5; index++) {
			if (grid[rowNumber][index] == 1) {
				countX++;
			} else if (grid[index][5 - index] == 2) {
				countO++;
			} else {
				return 0; // need full line -- no line here
			}
		}

		return countO > countX ? countO : countX;

	}

	private int checkColumn(int columnNumber) {

		int countX = 0;		//1
		int countO = 0;		//2

		for (int index = 0; index < 5; index++) {
			if (grid[index][columnNumber] == 1) {
				countX++;
			} else if (grid[index][5 - index] == 2) {
				countO++;
			} else {
				return 0; // need full line -- no line here
			}
		}

		return countO > countX ? countO : countX;
	}
}