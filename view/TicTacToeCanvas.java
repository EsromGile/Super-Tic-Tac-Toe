package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import javax.swing.JPanel;

public class TicTacToeCanvas extends JPanel {
    private StandAlonePanel saPanel;
    private NetworkPanel nPanel;

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
        // TODO Auto-generated method stub
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        for (int i = 80; i < 400; i+=80) {
            g2.drawLine(i, 10, i, 390);
            g2.drawLine(10, i, 390, i);
        }
    }

}
