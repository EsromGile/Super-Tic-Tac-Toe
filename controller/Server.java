package controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import view.GamePanel;

public class Server extends Thread {
    private final ServerSocket serverSocket;
    private final GamePanel panel;
    // private String name; 
    private InputStream is; 
    private ObjectInputStream ois; 

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
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                panel.setOis(ois);
                
                addPeerHandler(socket, panel);
                panel.getCanvas().getTextArray()
                        .add("a new client has connected" + socket.getLocalAddress() + " " + socket.getInetAddress());
                panel.getCanvas().repaint();
                // System.out.println("A new client has connected!" + socket.getLocalAddress() +
                // " " + socket.getInetAddress() + " " + serverSocket.getInetAddress());
                
                // if(panel.getPlayerX()){
				// 	name = "X";
				// } else {
				// 	name = "O";
				// }
                // PeerHandler peerHandler = new PeerHandler(socket, panel,name );
                // Thread thread = new Thread(peerHandler);
                // thread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // if not working add try catch and close server
        }
    }

   
    public void addPeerHandler(Socket socket,GamePanel panel) throws Exception {
        System.out.println("socket step 2");    
        String name = "";
        System.out.println("test 1");
        
        if(panel.getOis() == null){
            System.out.println("null ois");
        }
        System.out.println("test 2");
        
        
        if ((name = (String) ois.readObject()) != null) {
            System.out.println("test oos: " + name);
            // System.out.println("peer handler step 1");
            PeerHandler peerHandler;
            // System.out.println("peer handler step 2");
            peerHandler = new PeerHandler(socket, panel, name);
            // System.out.println("peer handler step 3");
            Thread thread = new Thread(peerHandler);
            System.out.println("peer handler added:");
            thread.start();
        } else {
            System.out.println("does not works!");
        }
        System.out.println("test 4");
    }

    

    public static void main(String[] args, GamePanel panel) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4216);
        Server server = new Server(serverSocket, panel);
        System.out.println(serverSocket.getInetAddress().getLocalHost().getHostAddress());
        panel.getCanvas().getTextArray().add(serverSocket.getInetAddress().getLocalHost().getHostAddress());
        panel.getCanvas().repaint();
        server.start();
        //server.startServer();
        System.out.println("Server Created!");
    }

}