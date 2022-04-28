package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.BasicStroke;

import javax.swing.JPanel;

import model.GameElement;
import model.TicTacToe;
import model.StatePattern.GamePlayerO;
import model.StatePattern.GamePlayerState;
import model.StatePattern.GamePlayerX;
import model.TicTacToe.Line;
import view.GamePanel.GameState;

public class TicTacToeCanvas extends JPanel {
    private GamePanel gamePanel;
    private ArrayList<GameElement> marks = new ArrayList<>();
    private int secondsLeft;
    private CopyOnWriteArrayList<String> textArray = new CopyOnWriteArrayList<>();
    private TicTacToe ticTacToe;

    public TicTacToeCanvas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setPreferredSize(new Dimension(600, 600));
        setBackground(new Color(147, 204, 133));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gamePanel.getGameState() != GameState.WAITING_FOR_CONNECTION && gamePanel.getGameState() != GameState.TIMEOUT) {

            // Tic Tac Toe Board
            g2.setColor(new Color(48, 99, 35));
            g2.setStroke(new BasicStroke(3));
            for (int i = 180; i < 500; i += 80) {
                g2.drawLine(i, 110, i, 490);
                g2.drawLine(110, i, 490, i);
            }

            // Render Marks
            for (var m : marks) {
                m.render(g2);
            }
        }

        if (gamePanel.getGameState() == GamePanel.GameState.PLAYING) {

            // Turn Indicator
            g2.setColor(new Color(48, 99, 35));
            g2.setFont(new Font("Courier", Font.BOLD, 40));
            if (gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX)
                g2.drawString("X's Turn...", 170, 60);
            else
                g2.drawString("O's Turn...", 170, 60);

            // Timer only displays when it is the player's turn
            if ((gamePanel.getPlayerX() && gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX) ||
                    (!gamePanel.getPlayerX() && gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerO)) {

                g2.setColor(new Color(48, 99, 35));
                g2.setFont(new Font("Courier", Font.BOLD, 30));

                if (secondsLeft >= 0) {
                    g2.drawString("Time Remaining: " + secondsLeft, 140, 550);
                } else {
                    g2.drawString("Time Remaining: 0", 140, 550);
                }
            }
        } 
        
        else if (gamePanel.getGameState() == GamePanel.GameState.WAITING_FOR_CONNECTION) {
            // Network Connections
            g2.setColor(Color.black);
            g2.setFont(new Font("Courier New", Font.BOLD, 25));
            int y = 150;
            g2.drawString("Waiting for network connection...", 65, y);
            g2.setFont(new Font("Courier New", Font.PLAIN, 12));
            for (String t : textArray) {
                y += 30;
                g2.drawString(t, 70, y);
            }
        } 
        
        else if (gamePanel.getGameState() == GamePanel.GameState.DRAW) {
            // Draw
            g2.setColor(Color.red);
            g2.setFont(new Font("Courier", Font.BOLD, 40));
            g2.drawString("Draw! No one wins!", 85, 60);
        } 
        
        else if (gamePanel.getGameState() == GamePanel.GameState.X_WIN) {
            // Winning Statement
            g2.setColor(Color.red);
            g2.setFont(new Font("Courier", Font.BOLD, 40));
            g2.drawString("Player X Won!", 150, 60);

            // Line
            drawWinningLine(g2);
        }

        else if (gamePanel.getGameState() == GamePanel.GameState.O_WIN) {
            // Winning Statement
            g2.setColor(Color.red);
            g2.setFont(new Font("Courier", Font.BOLD, 40));
            g2.drawString("Player O Won!", 150, 60);

            // Line
            drawWinningLine(g2);
        } 
        
        else if (gamePanel.getGameState() == GameState.TIMEOUT) {
            g2.setColor(Color.red);
            g2.setFont(new Font("Courier", Font.BOLD, 40));
            g2.drawString("You ran out of time!", 75, 250);
            g2.drawString("You lose!", 200, 300);
        }

    }

    public CopyOnWriteArrayList<String> getTextArray() {
        return textArray;
    }

    public ArrayList<GameElement> getMarks() {
        return marks;
    }

    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    private void drawWinningLine(Graphics2D g2) {
        TicTacToe ticTacToe = gamePanel.getTicTacToeGame();

        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(3));

        Line winningLine = ticTacToe.getWinningLine();
        if (winningLine == Line.LEFT_DIAGONAL) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 0).x, ticTacToe.getBoundingBox(0, 0).y,
                    ticTacToe.getBoundingBox(4, 4).x + 80, ticTacToe.getBoundingBox(4, 4).y + 80);
        } else if (winningLine == Line.RIGHT_DIAGONAL) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 4).x + 80, ticTacToe.getBoundingBox(0, 4).y,
                    ticTacToe.getBoundingBox(4, 0).x, ticTacToe.getBoundingBox(4, 0).y + 80);
        } else if (winningLine == Line.COL1) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 0).x + 40, ticTacToe.getBoundingBox(0, 0).y,
                    ticTacToe.getBoundingBox(4, 0).x + 40, ticTacToe.getBoundingBox(4, 0).y + 80);
        } else if (winningLine == Line.COL2) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 1).x + 40, ticTacToe.getBoundingBox(0, 1).y,
                    ticTacToe.getBoundingBox(4, 1).x + 40, ticTacToe.getBoundingBox(4, 1).y + 80);
        } else if (winningLine == Line.COL3) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 2).x + 40, ticTacToe.getBoundingBox(0, 2).y,
                    ticTacToe.getBoundingBox(4, 2).x + 40, ticTacToe.getBoundingBox(4, 2).y + 80);
        } else if (winningLine == Line.COL4) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 3).x + 40, ticTacToe.getBoundingBox(0, 3).y,
                    ticTacToe.getBoundingBox(4, 3).x + 40, ticTacToe.getBoundingBox(4, 3).y + 80);
        } else if (winningLine == Line.COL5) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 4).x + 40, ticTacToe.getBoundingBox(0, 4).y,
                    ticTacToe.getBoundingBox(4, 4).x + 40, ticTacToe.getBoundingBox(4, 4).y + 80);
        } else if (winningLine == Line.ROW1) {
            g2.drawLine(ticTacToe.getBoundingBox(0, 0).x, ticTacToe.getBoundingBox(0, 0).y + 40,
                    ticTacToe.getBoundingBox(0, 4).x + 80, ticTacToe.getBoundingBox(0, 4).y + 40);
        } else if (winningLine == Line.ROW2) {
            g2.drawLine(ticTacToe.getBoundingBox(1, 0).x, ticTacToe.getBoundingBox(1, 0).y + 40,
                    ticTacToe.getBoundingBox(1, 4).x + 80, ticTacToe.getBoundingBox(1, 4).y + 40);
        } else if (winningLine == Line.ROW3) {
            g2.drawLine(ticTacToe.getBoundingBox(2, 0).x, ticTacToe.getBoundingBox(2, 0).y + 40,
                    ticTacToe.getBoundingBox(2, 4).x + 80, ticTacToe.getBoundingBox(2, 4).y + 40);
        } else if (winningLine == Line.ROW4) {
            g2.drawLine(ticTacToe.getBoundingBox(3, 0).x, ticTacToe.getBoundingBox(3, 0).y + 40,
                    ticTacToe.getBoundingBox(3, 4).x + 80, ticTacToe.getBoundingBox(3, 4).y + 40);
        } else if (winningLine == Line.ROW5) {
            g2.drawLine(ticTacToe.getBoundingBox(4, 0).x, ticTacToe.getBoundingBox(4, 0).y + 40,
                    ticTacToe.getBoundingBox(4, 4).x + 80, ticTacToe.getBoundingBox(4, 4).y + 40);
        }

    }
}
