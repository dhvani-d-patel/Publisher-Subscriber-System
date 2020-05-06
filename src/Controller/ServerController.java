package Controller;

import Models.Packet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private int port;
    private Socket socket = null;
    private List<String> listOfTopics = new ArrayList<>();

    public ServerController(int port){
        this.port = port;
    }

    public boolean connectToClient(){

        try{
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server: Activated");

            System.out.println("Server: Waiting for client...");

            while(true){
                socket = server.accept();
                System.out.println("Server: Connection Established on "+socket.getPort());

                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Packet packet = (Packet) input.readObject();

                System.out.println("Server: Client-type is "+packet.getType()+" with ID "+ packet.getGuid());
                if (packet.getType().equals("Advertiser")){
                    registerTopic(packet.getTopicName());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void registerTopic(String topicName){
        topicName = topicName.toLowerCase();
        if (!listOfTopics.contains(topicName)){
            listOfTopics.add(topicName);
            System.out.println("Server: Topic "+ topicName + " created.");
        }
        else {
            System.out.println("Server: Topic "+ topicName +" already exists.");
        }

        printAllTopics();
    }

    private void printAllTopics() {
        System.out.print("Server: Current Topics Available: ");
        for(String topicName : listOfTopics){
            System.out.print(topicName+"\t");
        }
        System.out.println();
    }
}
