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
    // private BufferedReader bufferedReader;
    // private BufferedWriter bufferedWriter;
    private String username;
    private OutputStream os;
    private ObjectOutputStream oos;
    private GamePanel panel;
    private InputStream is;
    private ArrayList<GameElement> shapes;
    // private ObjectInputStream ois;

    public Peer(Socket socket, String username, GamePanel panel) throws Exception {
        this.socket = socket;
        this.username = username;
        os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);
        this.panel = panel;
        is = socket.getInputStream();
        
        // this.bufferedReader = new BufferedReader(new
        // InputStreamReader(socket.getInputStream()));
        // this.bufferedWriter= new BufferedWriter(new
        // OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendMessage() throws Exception {

        // bufferedWriter.write(username);
        // bufferedWriter.newLine();
        // bufferedWriter.flush();
        // Scanner scanner = new Scanner(System.in);
        while (socket.isConnected()) {
            oos.reset();
            oos.writeObject(panel.getCanvas().getMarks());
            // String messageToSend = scanner.nextLine();
            // bufferedWriter.write(username + ": " + messageToSend);
            // bufferedWriter.newLine();
            // bufferedWriter.flush();
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    
                    //String msgFromGroupChat; 
                    while(socket.isConnected()){
                    shapes = (ArrayList) ois.readObject();
                    panel.getCanvas().repaint();
                    // msgFromGroupChat = bufferedReader.readLine();
                    //    System.out.println(msgFromGroupChat);
                    }} catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }).start();
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


}