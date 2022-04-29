package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MouseEventListener;
import controller.Peer;
import controller.Server;
import model.AI;
import model.GamePlayer;
import model.Player;
import model.TicTacToe;
import model.ObserverPattern.GameElementObserver;
import model.StatePattern.GamePlayerTurn;

public class GamePanel {

    public enum GameState {
        PLAYING, X_WIN, O_WIN, DRAW, WAITING_FOR_CONNECTION, TIMEOUT
    }

    public static final int E = 0, X = 1, O = 2;
    private int thisPlayer;
    private int currentPlayer;
    private GameState gameState;
    private JFrame window;
    private TicTacToeCanvas canvas;
    private GamePlayerTurn gamePlayerTurn;
    private TicTacToe ticTacToeGame;
    private GamePlayer gamePlayer;
    private boolean playerX;
    MouseEventListener mouseEventListener;
    private AI aiPlayer = new AI(this);
    private Player manPlayer = new Player(this);
    private boolean mouseClick = false;
    private boolean network = false;
    private boolean serverPeer = false; // true means server connection, false means peer connection
    private Server serverObject = null;
    private Peer peerObject = null;

    public GamePanel(JFrame window, boolean playerX) {
        this.window = window;
        this.playerX = playerX;
        window.setPreferredSize(new Dimension(600, 675));
    }

    public void createStandAlonePanel() throws Exception {
        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(600, 650));
        window.setTitle("Super-Tic-Tac-Toe (Man vs. AI)");
        // main tic-tac-toe panel
        JPanel mainPanel = new JPanel();
        canvas = new TicTacToeCanvas(this);
        mouseEventListener = new MouseEventListener(this);
        canvas.addMouseListener(mouseEventListener);
        mainPanel.add(canvas);

        // south panel
        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Back");
        southPanel.add(backButton);

        cp.add(BorderLayout.CENTER, mainPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        // Start Game
        gamePlayerTurn = new GamePlayerTurn(this);
        ticTacToeGame = new TicTacToe();
        gameState = GameState.PLAYING;
        GameElementObserver observer = new GameElementObserver(this);
        ticTacToeGame.subscribe(observer);
        network = false;

        // action listener
        backButton.addActionListener(event -> {
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });
        aiPlayer.takeTurn();
    }

    public void createNetworkPanel() throws Exception {
        network = true;

        Container cp = window.getContentPane();
        cp.setPreferredSize(new Dimension(600, 650));
        window.setTitle("Super-Tic-Tac-Toe (AI vs. AI)");
        playerX = !playerX;

        if (playerX == true) {
            thisPlayer = X;
            aiPlayer.takeTurn();
        } else
            thisPlayer = O;

        // main tic-tac-toe panel
        JPanel mainPanel = new JPanel();
        canvas = new TicTacToeCanvas(this);
        mainPanel.add(canvas);

        // south panel
        JPanel southPanel = new JPanel();
        JButton backButton = new JButton("Back");
        southPanel.add(backButton);

        cp.add(BorderLayout.CENTER, mainPanel);
        cp.add(BorderLayout.SOUTH, southPanel);

        // Start Game
        gamePlayerTurn = new GamePlayerTurn(this);

        ticTacToeGame = new TicTacToe();
        gameState = GameState.WAITING_FOR_CONNECTION;
        GameElementObserver observer = new GameElementObserver(this);
        ticTacToeGame.subscribe(observer);

        backButton.addActionListener(event -> {
            window.getContentPane().removeAll();
            var panel = new MenuPanel(window);
            panel.init();
            window.pack();
            window.setVisible(true);
        });

        window.setVisible(true);

    }

    public boolean isMyTurn() {
        if (thisPlayer == currentPlayer) {
            return true;
        } else
            return false;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getThisPlayer() {
        return thisPlayer;
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

    public boolean getPlayerX() {
        return playerX;
    }

    public AI getAiPlayer() {
        return aiPlayer;
    }

    public Player getManPlayer() {
        return manPlayer;
    }

    public void setMouseClick(boolean mouseClick) {
        this.mouseClick = mouseClick;
    }

    public boolean isMouseClick() {
        return mouseClick;
    }

    public boolean isServerPeer() {
        return serverPeer;
    }

    public void setServerPeer(boolean serverPeer) {
        this.serverPeer = serverPeer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setGamePlayerTurn(GamePlayerTurn gamePlayerTurn) {
        this.gamePlayerTurn = gamePlayerTurn;
    }

    public Server getServerObject() {
        return serverObject;
    }

    public void setServerObject(Server serverObject) {
        this.serverObject = serverObject;
    }

    public Peer getPeerObject() {
        return peerObject;
    }

    public void setPeerObject(Peer peerObject) {
        this.peerObject = peerObject;
    }
}
