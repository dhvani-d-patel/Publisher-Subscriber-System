package Controller;

import Models.Packet;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AdvertiserController {

    private int port;
    private String address;
    private Socket socket;



    public AdvertiserController(String address, int port){
        this.address = address;
        this.port = port;
    }

    public boolean connectToServer(Packet packet){
        try{

            socket = new Socket(address, port);
            System.out.println("Advertiser: Connection established with Server.");
            createTopic(packet);

        } catch(UnknownHostException u){
            System.out.println(u);
        } catch(IOException i){
            System.out.println(i);
        }
        return true;
    }

    public void createTopic(Packet packet) {
        try{
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
