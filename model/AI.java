package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

// not the right import
import org.w3c.dom.events.MouseEvent;

import model.ObserverPattern.Observer;
import model.StatePattern.GamePlayerX;
import view.StandAlonePanel;

public class AI extends GamePlayer {

	private class Coordinate {
		public int row;
		public int col;
		public int value;
	}

	public class Weight {
		public int ai = 0;
		public int player = 0;
	}


	private ArrayList<Observer> observers;
	private StandAlonePanel saPanel;
	private int seconds;
	private int x;
	private int y;

	public AI(StandAlonePanel saPanel){
		this.saPanel = saPanel;
	}

	public void addGameLIstener(Observer o) {

	}

	public void removeGameLIstener(Observer o) {

	}

	public void notifyObservers(MouseEvent e) {

	}

	public void takeTurn() {
		// Sample "AI" Turn
		Random r = new Random();
		x = r.nextInt(5);
		y = r.nextInt(5);

		while(!saPanel.getTicTacToeGame().spotTaken(x, y)){
			x = r.nextInt(5);
			y = r.nextInt(5);
		}


		// // broken :(
		// Coordinate bestMove = new Coordinate();
		// bestMove = lookForWinConditions(saPanel.getTicTacToeGame().getGrid(), 2, 5, bestMove);
		// x = bestMove.row;
		// y = bestMove.col;

		if((saPanel.getPlayerX() ^ saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX)) {

			ArrayList<GameElement> marks = saPanel.getCanvas().getMarks();

			//Adds a 1.5 second delay before the AI places its mark
			Timer timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					marks.add(new GameElement(saPanel.getTicTacToeGame().getBoundingBox(x, y).x + 5,
										saPanel.getTicTacToeGame().getBoundingBox(x, y).y + 5, 
										saPanel.getGamePlayerTurn().getState()));
					if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
						saPanel.getTicTacToeGame().setEntry(x, y, 1);	//Set to X
					} else {
						saPanel.getTicTacToeGame().setEntry(x, y, 2);	//Set to O
					}
					timer1.cancel();
					timer1.purge();
				}
			}, 1500);
	
			saPanel.getCanvas().repaint();
	
			//Add code here for checking for a win/draw
			saPanel.getTicTacToeGame().printGameBoard();

			saPanel.getTicTacToeGame().checkWin();
			saPanel.getTicTacToeGame().checkDraw();
			
			//After the AI places mark, delay for 5 seconds, then go to next state
			Timer timer2 = new Timer();

			timer2.schedule(new TimerTask() {
				@Override
				public void run() {
					saPanel.getGamePlayerTurn().goNextState();
					System.out.print("Timer Ended");
					timer2.cancel();
					timer2.purge();
				}
			}, 5000L);

			saPanel.getCanvas().repaint();
			// saPanel.getGamePlayerTurn().goNextState();		// this seems to be broken 
			
		}
	}

	public void render() {
		
	}


	public Coordinate lookForWinConditions(TicTacToe.TicTacToeSquare[][] grid, int ai, int weight, Coordinate bestMove) {
		
		// base case
		if (weight < 0) 
			return bestMove;

		// intialize weights
		ArrayList<Weight> rows = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			rows.add(new Weight());
		}
		ArrayList<Weight> cols = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			cols.add(new Weight());
		}
		ArrayList<Weight> diags = new ArrayList<>(2);
		for (int i = 0; i < 2; i++) {
			diags.add(new Weight());
		}
		
		// compute weights
		for (int idx = 0; idx < 5; idx++) {
			rows.set(idx, checkWeight(grid, rows.get(idx), "row", idx, ai));
			cols.set(idx, checkWeight(grid, cols.get(idx), "col", idx, ai));
		}
		diags.set(0, checkWeight(grid, diags.get(0), "leftDiag", -1, ai));
		diags.set(1, checkWeight(grid, diags.get(1), "rightDiag", -1, ai));
		
		ArrayList<Coordinate> moves = new ArrayList<>();

		// get win/loss conditions
		int condition;
		for (int row = 0; row < 5; row++) {
			condition = getWinCondition(rows.get(row), weight);
			if (condition == -1 || condition == 1) {
			    moves.add(getConditionLocation(grid, row, "row"));
			}
			for (int col = 0; col < 5; col++) {
				condition = getWinCondition(cols.get(col), weight);
				if (condition == -1 || condition == 1) {
					moves.add(getConditionLocation(grid, col, "col"));
				}
			}
		}
		condition = getWinCondition(diags.get(0), weight);
		if (condition == -1 || condition == 1) {
			moves.add(getConditionLocation(grid, -1, "leftDiag"));
		}
		condition = getWinCondition(diags.get(1), weight);
		if (condition == -1 || condition == 1) {
			moves.add(getConditionLocation(grid, -1, "rightDiag"));
		}

		// get best move if there is win condition
		for (Coordinate coordinate : moves) {
			if (coordinate.value == 1) {
				bestMove = coordinate;
			} else if (coordinate.value == -1 && bestMove.value != 1) {
				bestMove = coordinate;
			} else continue;
		}

		if (bestMove.value == 0) {
			bestMove = lookForWinConditions(grid, ai, weight - 1, bestMove);
		}

		return bestMove;
	}

	private Coordinate getConditionLocation(TicTacToe.TicTacToeSquare[][] grid, int line, String lineType) {
		Coordinate coordinate = new Coordinate();
		switch(lineType) {
			case "row":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[line][idx].entry;
					if (entry != 0) continue;
					else {
						coordinate.row = line;
						coordinate.col = idx;
					}
				} break;

			case "col":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][line].entry;
					if (entry != 0) continue;
					else {
						coordinate.row = idx;
						coordinate.col = line;
					}
				} break;

			case "leftDiag":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][idx].entry;
					if (entry != 0) continue;
					else {
						coordinate.row = idx;
						coordinate.col = idx;
					}
				} break;

			case "rightDiag":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[4 - idx][idx].entry;
					if (entry != 0) continue;
					else {
						coordinate.row = idx;
						coordinate.col = line;
					}
				} break;	
		}
		return coordinate;
	}	

	private int getWinCondition(Weight line, int weight) {
		int placeValue = 0;
		if (line.player == 0 && line.ai == weight) {
			placeValue = 1;		//win
		} else if (line.ai == 0 && line.player == weight) {
			placeValue = -1;	//loss
		}
		return placeValue;
	}

	
	private Weight checkWeight(TicTacToe.TicTacToeSquare[][] grid, Weight line, String lineType, int lineNum, int ai) {
		switch(lineType) {
			case "row":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[lineNum][idx].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				} break;

			case "col":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][lineNum].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				} break;

			case "leftDiag":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][idx].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				} break;

			case "rightDiag":
				for(int idx = 0; idx < 5; idx++) {
					int entry = grid[4 - idx][idx].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				} break;	
		}
		return line;
	}
}
