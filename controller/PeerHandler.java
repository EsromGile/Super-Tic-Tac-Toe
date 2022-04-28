package controller;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import model.GameElement;
import model.StatePattern.GamePlayerTurn;
import view.GamePanel;
import view.GamePanel.GameState;

public class PeerHandler implements Runnable {
    public static ArrayList<PeerHandler> peerHandlers = new ArrayList<>(); 
    private Socket socket; 
    private String peerUsername; 
    private GamePanel panel; 
    //private ArrayList<GameElement> marks ;
    private InputStream is; 
    private ObjectInputStream ois;

    public PeerHandler(Socket socket, GamePanel panel, String peerName) throws Exception{
        this.socket = socket; 
        this.panel = panel ;
        this.peerUsername = peerName;//pulling wrong user
        peerHandlers.add(this);
        panel.getCanvas().getTextArray().add(peerUsername + " has joined the server!");
        // is = panel.getOis(); 
        ois = panel.getOis();
        checkConnectionNum();
        //is = socket.getInputStream();
        //ois = new ObjectInputStream(is);
    }
    @Override
    public void run() {
        //String messageFromClient;
        
        
        while(!socket.isConnected()){
            try {
                //marks =(ArrayList) ois.readObject();
                
                // messageFromClient = bufferedReader.readLine();
                //broadcastMessage();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }
    public void broadcastMessage() throws Exception{
        for(PeerHandler peerHandler:peerHandlers){
            if(!peerHandler.peerUsername.equals(peerUsername)){

                //panel.getCanvas().getMarks();
                panel.getCanvas().repaint();
                
            }
        }
    }
    public void checkConnectionNum() throws Exception{
        if(peerHandlers.size() > 1){
            panel.setGameState(GameState.PLAYING);
            for(PeerHandler peerHandler:peerHandlers){
                //if(!peerHandler.peerUsername.equals(peerUsername)){
                    panel.getAiPlayer().takeTurn();
                //}
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
    // public ArrayList<GameElement> getMarks() {
    //     return marks;
    // }
}