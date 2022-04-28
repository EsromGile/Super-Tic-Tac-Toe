package model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import model.ObserverPattern.Observer;
import model.ObserverPattern.Subject;


public class TicTacToe implements Subject, Serializable{

	public enum Event {
		WinDetected, DrawDetected
	};

	public enum Line {
		LEFT_DIAGONAL, RIGHT_DIAGONAL, ROW1, ROW2, ROW3, ROW4, ROW5, COL1, COL2, COL3, COL4, COL5
	}
	ArrayList<Observer> observers = new ArrayList<>();
	private Line winningLine;

	public class TicTacToeSquare {
		private Rectangle boundingBox; 		//boundingBox refers to the area on the canvas that the square covers
		int entry;					//entry refers to the mark (or lack of) that is in the square
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

	public boolean spotTaken(int x, int y){
		return isEmpty(x, y);	// not sure why this is??
	}

	public Line getWinningLine() {
		return winningLine;
	}

	// draw only happens when all spots are full
	public void checkDraw() {
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (grid[row][col].entry == 0)
					return;
			}
		}
		notifyObservers(Event.DrawDetected);
	}

	public void checkWin(){

		boolean isWin = false;

		isWin = checkLeftDiagonal() || checkRightDiagonal();

		if(isWin) {
			if(checkLeftDiagonal())
				winningLine = Line.LEFT_DIAGONAL;
			else if(checkRightDiagonal())
				winningLine = Line.RIGHT_DIAGONAL;
		}

		int index = 0;
		while (!isWin && index < 5) {
			isWin = checkColumn(index) || checkRow(index);

			if(isWin) {
				if(checkColumn(index)) {
					switch(index) {
						case 0: winningLine = Line.COL1;
								break;
						case 1: winningLine = Line.COL2;
								break;
						case 2: winningLine = Line.COL3;
								break;
						case 3: winningLine = Line.COL4;
								break;
						case 4: winningLine = Line.COL5;
								break;
					}
				}
				else if(checkRow(index)) {
					switch(index) {
						case 0: winningLine = Line.ROW1;
								break;
						case 1: winningLine = Line.ROW2;
								break;
						case 2: winningLine = Line.ROW3;
								break;
						case 3: winningLine = Line.ROW4;
								break;
						case 4: winningLine = Line.ROW5;
								break;
					}
				}
			}
			index++;
		}

		if(isWin)
			notifyObservers(Event.WinDetected);
	}

	@Override
	public void subscribe(Observer o) {
		observers.add(o);
	}

	@Override
	public void unsubscribe(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers(Event event) {
		switch(event) {
			case WinDetected:
				for (var o: observers) 
					o.winCondition();
				break;
			case DrawDetected:
				for (var o: observers)
					o.drawCondition();
				break;
		}
	}

	// ------------------
    //  Auxilary Methods
    // ------------------

	private boolean checkLeftDiagonal() {

		int countX = 0;
		int countO = 0;

        for (int index = 0; index < 5; index++) {
			int entry = grid[index][index].entry; 
			if (entry == 0)
				return false;
			else if (entry == 1)
				countX++;
			else
				countO++;
        } 
		if (countO == 5 || countX == 5) 
			return true;
		else 
			return false;
    }

    private boolean checkRightDiagonal() {

		int countX = 0;
		int countO = 0;

        for (int index = 0; index < 5; index++) {
            int entry = grid[index][4 - index].entry; 
			if (entry == 0)
				return false;
			else if (entry == 1)
				countX++;
			else
				countO++;
        } 
        if (countO == 5 || countX == 5) 
			return true;
		else 
			return false;
    }

    private boolean checkRow(int rowNumber) {

		int countX = 0;
		int countO = 0;

        for (int index = 0; index < 5; index++) {
			int entry = grid[rowNumber][index].entry;
            if (entry == 0)
				return false;
			else if (entry == 1)
				countX++;
			else
				countO++;
        }
		if (countX == 5 || countO == 5) 
			return true;
		else 
			return false;
    }

    private boolean checkColumn(int columnNumber) {

		int countX = 0;
		int countO = 0;

        for (int index = 0; index < 5; index++) {
			int entry = grid[index][columnNumber].entry;
			if (entry == 0)
			return false;
		else if (entry == 1)
			countX++;
		else
			countO++;
		}
		if (countX == 5 || countO == 5) 
			return true;
		else 
			return false;
    }
}

