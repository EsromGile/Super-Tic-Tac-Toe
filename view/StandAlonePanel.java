package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MouseEventListener;
import model.TicTacToe;
import model.StatePattern.GamePlayerTurn;

public class StandAlonePanel {

    private JFrame window; 
    private TicTacToeCanvas canvas; 
    GamePlayerTurn gamePlayerTurn;
    TicTacToe ticTacToeGame;

    public StandAlonePanel(JFrame window){
        this.window = window;
        window.setTitle("Super-Tic-Tac-Toe (Man vs. AI)");
    }

    public void createStandAlonePanel(){
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(600,650));
        
        //main tic-tac-toe panel
        JPanel mainPanel = new JPanel();
        canvas = new TicTacToeCanvas(this);
        MouseEventListener mouseEventListener = new MouseEventListener(this);
        canvas.addMouseListener(mouseEventListener);
        mainPanel.add(canvas);

        //south panel 
        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Back"); 
        southPanel.add(backButton);

        cp.add(BorderLayout.CENTER, mainPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        //Start Game
        //I'm not exactly sure if this is how you're supposed to start the game but it made sense to me -Vivian
        gamePlayerTurn = new GamePlayerTurn();   
        ticTacToeGame = new TicTacToe();          

        //action listener
        backButton.addActionListener(event->{
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });

    }

    public TicTacToeCanvas getCanvas() {
        return canvas;
    }

    public GamePlayerTurn getGamePlayerTurn() {
        return gamePlayerTurn;
    }

    public TicTacToe getTicTacToeGame() {
        return ticTacToeGame;
    }
}
