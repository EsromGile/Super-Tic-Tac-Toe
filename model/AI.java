package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import controller.Peer;
import controller.PeerHandler;
import model.StatePattern.GamePlayerX;
import view.GamePanel;
import view.GamePanel.GameState;

public class AI implements Serializable {

	private class Coordinate {
		public int row;
		public int col;
		public int value;
		public int multiplicity;

		public Coordinate() {
			this.row = -1;
			this.col = -1;
			this.value = 0;
			this.multiplicity = 1;
		}
	}

	public class Weight {
		public int ai;
		public int player;

		public Weight() {
			this.ai = 0;
			this.player = 0;
		}

		public Weight(int ai, int player) {
			this.ai = ai;
			this.player = player;
		}
	}

	private GamePanel gamePanel;
	private int x;
	private int y;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Peer peer;

	public AI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		oos = gamePanel.getOos();
		ois = gamePanel.getOis();

	}

	public void takeTurn() throws Exception {
		if(gamePanel.getGameState() != GameState.PLAYING) return;
		if (gamePanel.isNetwork() && PeerHandler.peerHandlers.size() <= 1) {
			System.out.println("not enough players yet");
			return;
		}

		if ((gamePanel.getPlayerX() ^ gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX)) {

			Coordinate bestMove = new Coordinate();
			bestMove = lookForWinConditions(gamePanel.getTicTacToeGame().getGrid(), 2, 4, bestMove);
			x = bestMove.row;
			y = bestMove.col;

			if(gamePanel.isNetwork() ){
				//do stuff
				peer = gamePanel.getPeer();
				//System.out.println("send peer info");
				if(peer != null){
					System.out.println("actually send peer info");
					peer.sendCoordinates(x, y);
					
					//peer.getCoordinates();
				}
			}

			ArrayList<GameElement> marks = gamePanel.getCanvas().getMarks();

			// Adds a 1 second delay before the AI places its mark
			Timer timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					marks.add(new GameElement(gamePanel.getTicTacToeGame().getBoundingBox(x, y).x + 5,
							gamePanel.getTicTacToeGame().getBoundingBox(x, y).y + 5,
							gamePanel.getGamePlayerTurn().getState()));
					if (gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX) {
						gamePanel.getTicTacToeGame().setEntry(x, y, 1); // Set to X
					} else {
						gamePanel.getTicTacToeGame().setEntry(x, y, 2); // Set to O
					}
					gamePanel.getCanvas().repaint();
					// gamePanel.getTicTacToeGame().printGameBoard();

					gamePanel.getTicTacToeGame().checkWin();
					gamePanel.getTicTacToeGame().checkDraw();
					timer1.cancel();
					timer1.purge();
				}
			}, 1000);

			// After the AI places mark, delay for 5 seconds, then go to next state
			Timer timer2 = new Timer();

			timer2.schedule(new TimerTask() {
				@Override
				public void run() {
					gamePanel.getGamePlayerTurn().goNextState();
					//System.out.print("Timer Ended");
					timer2.cancel();
					timer2.purge();
				}
			}, 5000L);
			gamePanel.getCanvas().repaint();
		}
		else if(gamePanel.isNetwork()){
			peer = gamePanel.getPeer();
			peer.getCoordinates();
		}

	}

	public Coordinate lookForWinConditions(TicTacToe.TicTacToeSquare[][] grid, int ai, int weight,
			Coordinate bestMove) {

		// base case
		if ((weight < 0) && (bestMove.value == 0)) {
			Coordinate move = new Coordinate();
			Random r = new Random();
			move.row = r.nextInt(5);
			move.col = r.nextInt(5);

			while (!gamePanel.getTicTacToeGame().spotTaken(move.row, move.col)) {
				move.row = r.nextInt(5);
				move.col = r.nextInt(5);
			}
			return move;
		} else if (weight < 0)
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
				moves.add(getConditionLocation(grid, row, "row", condition));
			}
			for (int col = 0; col < 5; col++) {
				condition = getWinCondition(cols.get(col), weight);
				if (condition == -1 || condition == 1) {
					moves.add(getConditionLocation(grid, col, "col", condition));
				}
			}
		}
		condition = getWinCondition(diags.get(0), weight);
		if (condition == -1 || condition == 1) {
			moves.add(getConditionLocation(grid, -1, "leftDiag", condition));
		}
		condition = getWinCondition(diags.get(1), weight);
		if (condition == -1 || condition == 1) {
			moves.add(getConditionLocation(grid, -1, "rightDiag", condition));
		}

		bestMove = getBestMove(moves, weight);

		if (bestMove.col == -1 || bestMove.row == -1) {
			bestMove = lookForWinConditions(grid, ai, weight - 1, bestMove);
		}

		return bestMove;
	}

	private Coordinate getBestMove(ArrayList<Coordinate> moves, int weight) {

		// if win/lose condition -- make that move or block
		if (weight == 4) {
			for (Coordinate coordinate : moves) {
				if (coordinate.value == 1 || coordinate.value == -1)
					return coordinate;
			}
		}

		int[][] multiplicity = new int[5][5];
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				multiplicity[row][col] = 0;
			}
		}

		for (Coordinate coordinate : moves) {
			int row = coordinate.row;
			int col = coordinate.col;
			multiplicity[row][col]++;
		}

		Coordinate bestMove = new Coordinate();
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (bestMove.multiplicity < multiplicity[row][col]) {
					bestMove.col = col;
					bestMove.row = row;
					bestMove.multiplicity = multiplicity[row][col];
				}
			}
		}
		return bestMove;
	}

	private Coordinate getConditionLocation(TicTacToe.TicTacToeSquare[][] grid, int line, String lineType, int condition) {
		Coordinate coordinate = new Coordinate();
		switch (lineType) {
			case "row":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[line][idx].entry;
					if (entry != 0)
						continue;
					else {
						coordinate.row = line;
						coordinate.col = idx;
					}
				}
				break;

			case "col":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][line].entry;
					if (entry != 0)
						continue;
					else {
						coordinate.row = idx;
						coordinate.col = line;
					}
				}
				break;

			case "leftDiag":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][idx].entry;
					if (entry != 0)
						continue;
					else {
						coordinate.row = idx;
						coordinate.col = idx;
					}
				}
				break;

			case "rightDiag":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[4 - idx][idx].entry;
					if (entry != 0)
						continue;
					else {
						coordinate.row = 4 - idx;
						coordinate.col = idx;
					}
				}
				break;
		}
		coordinate.value = condition;
		return coordinate;
	}

	private int getWinCondition(Weight line, int weight) {
		int placeValue = 0;
		if (line.player == 0 && line.ai == weight) {
			placeValue = 1; // win
		} else if (line.ai == 0 && line.player == weight) {
			placeValue = -1; // loss
		}
		return placeValue;
	}

	private Weight checkWeight(TicTacToe.TicTacToeSquare[][] grid, Weight line, String lineType, int lineNum, int ai) {
		switch (lineType) {
			case "row":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[lineNum][idx].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				}
				break;

			case "col":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][lineNum].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				}
				break;

			case "leftDiag":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[idx][idx].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				}
				break;

			case "rightDiag":
				for (int idx = 0; idx < 5; idx++) {
					int entry = grid[4 - idx][idx].entry;
					if (entry == 0) {
						continue;
					} else if (entry == ai) {
						line.ai++;
					} else {
						line.player++;
					}
				}
				break;
		}
		return line;
	}

	public void setPeer(Peer peer) {
		this.peer = peer;
	}

}
