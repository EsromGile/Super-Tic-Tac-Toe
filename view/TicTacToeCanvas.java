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
import model.StatePattern.GamePlayerX;

public class TicTacToeCanvas extends JPanel {
    private StandAlonePanel saPanel;
    private NetworkPanel nPanel;
    private ArrayList<GameElement> marks = new ArrayList<>();

    public TicTacToeCanvas(StandAlonePanel saPanel) {
        this.saPanel = saPanel;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
    }

    public TicTacToeCanvas(NetworkPanel nPanel) {
        this.nPanel = nPanel;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Turn Indicator
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Courier", Font.BOLD, 40));
        if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX)
            g2.drawString("X's Turn...", 170, 60);
        else
            g2.drawString("O's Turn...", 170, 60);

        //Tic Tac Toe Board
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        for (int i = 180; i < 500; i+=80) {
            g2.drawLine(i, 110, i, 490);
            g2.drawLine(110, i, 490, i);
        }

        //Testing out how to initialize bounding boxes
        g2.setColor(Color.RED);
        int x = 100;
        int y = 100;
        for(int row = 0; row < 5; row++) {
            for(int column = 0; column < 5; column++) {
                g2.drawRect(x, y, 80, 80);
                x += 80;
            }
            x = 100;
            y += 80;
        }


        //Render Marks
        for(var m: marks) {
            m.render(g2);
        }

        //Timer
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Courier", Font.BOLD, 30));
        if(saPanel.getGamePlayerTurn().getState() instanceof GamePlayerX)
            g2.drawString("Time Remaining: ", 140, 550);


    }

    public ArrayList<GameElement> getMarks() {
        return marks;
    }

}
