package controller;

import java.net.ServerSocket;
import java.net.Socket;

import view.GamePanel;

public class Server extends Thread {
    private final ServerSocket serverSocket;
    private final GamePanel panel;

    public Server(ServerSocket serverSocket, GamePanel panel) {
        this.serverSocket = serverSocket;
        this.panel = panel;
    }

    @Override
    public void run() {
        // listen for connection on port 4216
        while (!serverSocket.isClosed()) {
            // closed in peer handler
            Socket socket;
            try {
                socket = serverSocket.accept();
                panel.getCanvas().getTextArray()
                        .add("a new client has connected" + socket.getLocalAddress() + " " + socket.getInetAddress());
                panel.getCanvas().repaint();
                // System.out.println("A new client has connected!" + socket.getLocalAddress() +
                // " " + socket.getInetAddress() + " " + serverSocket.getInetAddress());
                //PeerHandler peerHandler = new PeerHandler(socket, panel, panel.getPeerName().getText());
                //Thread thread = new Thread(peerHandler);
                //thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // if not working add try catch and close server
        }
    }

    

    public static void main(String[] args, GamePanel panel) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4216);
        Server server = new Server(serverSocket, panel);
        panel.getCanvas().getTextArray().add(serverSocket.getInetAddress().getLocalHost().getHostAddress());
        panel.getCanvas().repaint();
        server.start();
        //server.startServer();
        System.out.println("Server Created!");
    }

}