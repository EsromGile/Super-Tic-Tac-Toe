package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.BasicStroke;

import javax.swing.JPanel;

import model.GameElement;

public class TicTacToeCanvas extends JPanel {
    private StandAlonePanel saPanel;
    private NetworkPanel nPanel;
    private ArrayList<GameElement> marks = new ArrayList<>();

    public TicTacToeCanvas(StandAlonePanel saPanel) {
        this.saPanel = saPanel;
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.black);
    }

    public TicTacToeCanvas(NetworkPanel nPanel) {
        this.nPanel = nPanel;
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Tic Tac Toe Board
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        for (int i = 80; i < 400; i+=80) {
            g2.drawLine(i, 10, i, 390);
            g2.drawLine(10, i, 390, i);
        }

        //Render Marks
        for(var m: marks) {
            m.render(g2);
        }

    }

    public ArrayList<GameElement> getMarks() {
        return marks;
    }

}
