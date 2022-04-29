package controller;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import view.GamePanel;

public class PeerHandler extends Network implements Runnable {
    public static ArrayList<PeerHandler> peerHandlers = new ArrayList<>();
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private GamePanel gamePanel;

    private boolean live;

    public PeerHandler(GamePanel gamePanel, Socket socket) {
        try {

            this.gamePanel = gamePanel;
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    public void sendMark(Object object) {
        try {
            oos.reset();
            oos.writeObject(object);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        live = true;
        while (live) {
            try {
                Object object = ois.readObject();
                gamePanel.getAiPlayer().markReceived(object);

            } catch (EOFException | SocketException e) {
                live = false;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void coordSent(int x, int y) {
    }

    @Override
    public void markReceived(Object object) {
    }

    @Override
    public void close() {
        try {
            live = false;
            ois.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
