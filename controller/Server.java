package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import view.GamePanel;
import view.GamePanel.GameState;

public class Server extends Network implements Runnable {

    private ServerSocket serverSocket;
    private GamePanel gamePanel;
    private PeerHandler peerHandler;

    public Server(ServerSocket serverSocket, GamePanel gamePanel) {
        try {
            this.gamePanel = gamePanel;
            this.serverSocket = serverSocket;
            gamePanel.setServerObject(this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            Socket socket;
            socket = serverSocket.accept();
            peerHandler = new PeerHandler(gamePanel, socket);
            gamePanel.setGameState(GameState.PLAYING);
            gamePanel.getAiPlayer().takeTurn();
            
            gamePanel.getCanvas().repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }
    }

    @Override
    public void coordSent(int x, int y) {
        if (gamePanel.isMyTurn()) {
            updateMarks(x, y);
        }
        gamePanel.getCanvas().repaint();

    }

    private void updateMarks(int x, int y) {
        if (!gamePanel.getTicTacToeGame().spotTaken(x, y)) {
            gamePanel.getTicTacToeGame().setGrid(UpdateMarks.getGrid());
            gamePanel.getTicTacToeGame().setEntry(x, y, gamePanel.getCurrentPlayer());
        }
        peerHandler.sendMark(new UpdateMarks(gamePanel.getTicTacToeGame().getGrid(), gamePanel.getCurrentPlayer()));
    }

    @Override
    public void markReceived(Object object) {
        try {
            if (object instanceof PeerPlayMark) {
                PeerPlayMark mark = (PeerPlayMark) object;
                updateMarks(mark.getX(), mark.getY());
                gamePanel.getAiPlayer().takeTurn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        gamePanel.getCanvas().repaint();
    }

    @Override
    public void close() {
        try {
            peerHandler.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args, GamePanel gamePanel) {
        try {
            ServerSocket serverSocket = new ServerSocket(4216);
            Server server = new Server(serverSocket, gamePanel);
            Thread t = new Thread(server);
            t.start();
            gamePanel.getCanvas().getTextArray().add(serverSocket.getInetAddress().getLocalHost().getHostAddress());
            gamePanel.getCanvas().repaint();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

}