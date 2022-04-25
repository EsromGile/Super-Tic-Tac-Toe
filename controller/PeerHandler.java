package controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.GameElement;
import view.GamePanel;

public class PeerHandler implements Runnable {
    public static ArrayList<PeerHandler> peerHandlers = new ArrayList<>(); 
    private Socket socket; 
    //private BufferedReader bufferedReader;
    //private BufferedWriter bufferedWriter; 
    private String peerUsername; 
    private GamePanel panel; 
    private ArrayList<GameElement> marks ;
    private InputStream is; 
    private ObjectInputStream ois;

    public PeerHandler(Socket socket, GamePanel panel, String peerName) throws Exception{
        this.socket = socket; 
        //this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.panel = panel ;
        this.peerUsername = peerName;//pulling wrong user
        peerHandlers.add(this);
        panel.getCanvas().getTextArray().add("SERVER: " + peerUsername + " has entered the chat!");
        is = socket.getInputStream();
        ois = new ObjectInputStream(is);
    }
    @Override
    public void run() {
        //String messageFromClient;
        
        
        while(!socket.isConnected()){
            try {
                marks =(ArrayList) ois.readObject();
                
                // messageFromClient = bufferedReader.readLine();
                broadcastMessage();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
    public void broadcastMessage() throws Exception{
        for(PeerHandler peerHandler:peerHandlers){
            if(!peerHandler.peerUsername.equals(peerUsername)){
                panel.getCanvas().repaint();
                //peerHandler.
                // peerHandler.bufferedWriter.write(messageToSend);
                // peerHandler.bufferedWriter.newLine();
                // peerHandler.bufferedWriter.flush();
            }
        }
    }
    public void removePeerHandler() throws Exception{
        peerHandlers.remove(this);
        //broadcastMessage("SERVER: " + peerUsername + " has left the chat!");
    }
    public void closeEverything(Socket socket) throws Exception{
        removePeerHandler();
        try {
           
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<GameElement> getMarks() {
        return marks;
    }
}