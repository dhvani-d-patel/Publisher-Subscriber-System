package Controller;

import Models.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class PublisherController {

    private int portServerSend;
    private int portServerReceive;
    private String address;
    private Socket socket;

    public PublisherController(String address, int portServerSend, int portServerReceive) {
        this.address = address;
        this.portServerSend = portServerSend;
        this.portServerReceive = portServerReceive;
    }

    public List<String> openConnection() {
        Packet packet = null;
        try {
            ServerSocket server = new ServerSocket(portServerReceive);
            System.out.println("Publisher ("+System.currentTimeMillis()+") : Listening…");

            socket = server.accept();
            System.out.println("Publisher: Connection Established on " + socket.getPort());

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            packet = (Packet) input.readObject();

            System.out.println("Publisher: Server is connected with ID " + packet.getGuid());
            System.out.print("Publisher: Current Topics Available: ");

            input.close();
            server.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return packet.getAvailableTopics();
    }



    public boolean connectToServer(Packet packet) {
        try{
            socket = new Socket(address,portServerSend);
            System.out.println("Publisher: Connection established with Server.");
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(packet);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
