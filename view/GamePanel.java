package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MouseEventListener;
import controller.EventListener;
import model.AI;
import model.GamePlayer;
import model.Player;
import model.TicTacToe;
import model.ObserverPattern.GameElementObserver;
import model.StatePattern.GamePlayerTurn;

public class GamePanel {

    private JFrame window; 
    private TicTacToeCanvas canvas; 
    private GamePlayerTurn gamePlayerTurn;
    private TicTacToe ticTacToeGame;
    private GamePlayer gamePlayer;
    private boolean playerX;
    private AI aiPlayer = new AI(this);
    private Player manPlayer = new Player(this); 
    private ObjectOutputStream oos; 
    private ObjectInputStream ois; 
    private boolean mouseClick = false;
    private boolean network = false; 

    public GamePanel(JFrame window, boolean playerX){
        this.window = window;
        window.setTitle("Super-Tic-Tac-Toe (Man vs. AI)");
        this.playerX = playerX;
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
        // gamePlayer = new GamePlayer(); 
        gamePlayerTurn = new GamePlayerTurn(this); 
        ticTacToeGame = new TicTacToe();      
        GameElementObserver observer = new GameElementObserver(this);
        ticTacToeGame.subscribe(observer);
        EventListener timerListener = new EventListener(this);   

        //action listener
        backButton.addActionListener(event->{
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });
        aiPlayer.takeTurn();

    }
    
    public void createNetworkPanel(){
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(600,650));
        
        //main tic-tac-toe panel
        JPanel mainPanel = new JPanel();
        canvas = new TicTacToeCanvas(this);
        mainPanel.add(canvas);

        //south panel 
        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Back"); 
        southPanel.add(backButton);
 
        cp.add(BorderLayout.CENTER, mainPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        //Start Game
        gamePlayerTurn = new GamePlayerTurn(this); 
        ticTacToeGame = new TicTacToe();      
        GameElementObserver observer = new GameElementObserver(this);
        ticTacToeGame.subscribe(observer);

        backButton.addActionListener(event->{
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });
        network = true; 

        aiPlayer.takeTurn();
    }
    public boolean isNetwork() {
        return network;
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

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public boolean getPlayerX(){
        return playerX;
    }

	public AI getAiPlayer() {
		return aiPlayer;
	}

    public Player getManPlayer() {
        return manPlayer;
    }
    public void setOos(ObjectOutputStream oos){
        // System.out.println("oos set");
        this.oos = oos; 
    }
    public ObjectOutputStream getOos() {
        return oos;
    }
    public void setOis(ObjectInputStream ois) {
        // System.out.println("set ois method");
        this.ois = ois;
    }
    public ObjectInputStream getOis() {
        return ois;
    }
    public void setMouseClick(boolean mouseClick) {
        this.mouseClick = mouseClick;
    }
    public boolean isMouseClick() {
        return mouseClick;
    }
}
