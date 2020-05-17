package Controller;

import Models.Packet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
                } else if (packet.getType().equals("Publisher")){
                    if (packet.getArticleContent() == null) {
                        sendAvailableTopics();
                    } else {
                        publishArticleContent(packet);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void registerTopic(String topicName){
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

    private void sendAvailableTopics(){
        try {

            System.out.println("Server ("+System.currentTimeMillis()+") : Trying to connect now.");
            socket = new Socket("localhost", 4000);
            System.out.println("Server: Connection established with Publisher.");
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Packet packet = new Packet();
            packet.setType("Server");
            packet.setAvailableTopics(listOfTopics);
            output.writeObject(packet);

            output.close();
            socket.close();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void publishArticleContent(Packet packet){
        System.out.print(packet.getTopicName() + " ");
        System.out.println(packet.getArticleContent());

    }
}
