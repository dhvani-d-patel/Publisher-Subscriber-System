import java.net.*;
import java.io.*;

public class Server {

    private ServerSocket server = null;
    private Socket socket = null;
    private DataInputStream input = null;


    public Server(int port){

        // Establish Server connection in constructor
        try{
            server = new ServerSocket(port);
            System.out.println("Server activated!");

            System.out.println("Waiting for client...");

            socket = server.accept();
            System.out.println("Client Connected");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String client_msg = "";

            // Print the msg on server
            while (!client_msg.equals("Close")){

                client_msg = input.readUTF();
                System.out.println(client_msg);

            }
            System.out.println("Server closed!!!!");
            // Close the connections
            input.close();
            socket.close();
            server.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
