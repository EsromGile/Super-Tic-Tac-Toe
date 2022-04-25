package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.BasicStroke;

import javax.swing.JPanel;

import model.GameElement;
import model.StatePattern.GamePlayerO;
import model.StatePattern.GamePlayerX;

public class TicTacToeCanvas extends JPanel {
    private GamePanel gamePanel;
    private ArrayList<GameElement> marks = new ArrayList<>();
    private int secondsLeft;
    private ArrayList<String> textArray = new ArrayList<>(); 

    public TicTacToeCanvas(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setPreferredSize(new Dimension(600, 600));
        setBackground(new Color(147, 204, 133));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Turn Indicator
        g2.setColor(new Color(48, 99, 35));
        g2.setFont(new Font("Courier", Font.BOLD, 40));
        if(gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX)
            g2.drawString("X's Turn...", 170, 60);
        else
            g2.drawString("O's Turn...", 170, 60);

        //Tic Tac Toe Board
        g2.setColor(new Color(48, 99, 35));
        g2.setStroke(new BasicStroke(3));
        for (int i = 180; i < 500; i+=80) {
            g2.drawLine(i, 110, i, 490);
            g2.drawLine(110, i, 490, i);
        }

        //Render Marks
        for(var m: marks) {
            m.render(g2);
        }

        //Timer only displays when it is the player's turn
        if((gamePanel.getPlayerX() && gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerX) ||
           (!gamePanel.getPlayerX() && gamePanel.getGamePlayerTurn().getState() instanceof GamePlayerO)) {

            g2.setColor(new Color(48, 99, 35));
            g2.setFont(new Font("Courier", Font.BOLD, 30));

            if(secondsLeft >= 0) {
                g2.drawString("Time Remaining: " + secondsLeft, 140, 550);
            }
            else {
                g2.drawString("Time Remaining: 0", 140, 550);
            }
        }
        g2.setColor(Color.yellow);
        g2.setFont(new Font("Courier New", Font.BOLD, 10));
        int l = 20; 
        int y = 0;
        for (String t:textArray){
            y += 20; 
            g2.drawString(t, 15, y);
        }

    }
    public ArrayList<String> getTextArray() {
        return textArray;
    }
    
    public ArrayList<GameElement> getMarks() {
        return marks;
    }

    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }
    

}
