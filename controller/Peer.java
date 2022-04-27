package controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.GameElement;
import view.GamePanel;

public class Peer {
	private Socket socket;
    private String username;
    private OutputStream os;
    private ObjectOutputStream oos;
    private GamePanel panel;
    // private InputStream is;
    private static InputStream is;
    private static ObjectInputStream ois;
    //private ArrayList<GameElement> marks;
    //private GameElement playerPiece;
    

    public Peer(Socket socket, String username, GamePanel panel) throws Exception {
        // System.out.println("peer step 1");
        this.socket = socket;
        // System.out.println("peer step 2");
        this.username = username;
        // System.out.println("peer step 3");
        this.panel = panel;
        // System.out.println("peer step 4");
        os = socket.getOutputStream();
        // System.out.println("peer step 5");
        oos = new ObjectOutputStream(os);
        // System.out.println("peer step 6");
        ois = panel.getOis();
        System.out.println("peer obj created");
    }

    public void sendCoordinates( int x, int y) throws Exception {
        // new Thread(new Runnable() {

        // @Override
        // public void run() {
        // try {
        // System.out.println("send coord");&& panel.isMouseClick() == true
        while (socket.isConnected() ) {
            System.out.println("mouseclick: " + panel.isMouseClick());
            // oos.writeObject(piece);
            oos.writeObject(x);
            oos.writeObject(y);
            oos.reset();
            oos.flush();
            //panel.setMouseClick(false);
        }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }).start();
    }
    public void getCoordinates() throws Exception {
        System.out.println("get coord");
        // new Thread(new Runnable() {
        //     ObjectInputStream ois = panel.getOis();

        //     @Override
        //     public void run() {
                try {
                    if (panel.getOis() == null) {
                        System.out.println("null ois");
                    }
                    while (socket.isConnected()) {
                        // IShapeDraw shape = (IShapeDraw) ois.readObject();
                        int x = (Integer) ois.readObject();
                        int y = (Integer) ois.readObject();
                        System.out.println("x:" + x + " y: " + y);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            // }
        // }).start();
    }

    public void closeEverything(Socket socket) {
        try {
            
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOos() {
        return oos;
    }
    public static ObjectInputStream getOis() {
        return ois;
    }
    public static void setOis(ObjectInputStream ois) {
        Peer.ois = ois;
    }
    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

}