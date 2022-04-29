package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import view.GamePanel;

public class Peer extends Network {
    private Socket socket;
    private PeerHandler peerHandler;
    private GamePanel gamePanel;

    public Peer(Socket socket, GamePanel gamePanel) throws UnknownHostException, IOException {
        try {
            this.gamePanel = gamePanel;
            this.socket = socket;

            peerHandler = new PeerHandler(gamePanel, socket);
            gamePanel.setGameState(gamePanel.getGameState().PLAYING);
            gamePanel.getCanvas().repaint();
            gamePanel.setPeerObject(this);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void coordSent(int x, int y) {
        if (gamePanel.isMyTurn()) {
            peerHandler.sendMark(new PeerPlayMark(x, y));
        }
    }

    @Override
    public void markReceived(Object object) {
        try {
            if (object instanceof UpdateMarks) {
                UpdateMarks mark = (UpdateMarks) object;
                gamePanel.getTicTacToeGame().setGrid(mark.getGrid());
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
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

}