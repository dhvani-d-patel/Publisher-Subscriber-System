import java.net.*;
import java.io.*;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;


    public Client(String address, int port){

        // Establish a connection in constructor
        try{

            socket = new Socket(address, port);
            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connection Established!");

        } catch(UnknownHostException u){
            System.out.println(u);
        } catch(IOException i){
            System.out.println(i);
        }

        String msg = "";

        // Read the msg and give it to socket
        while (!msg.equals("Close")){

            try{
                msg = input.readLine();
                output.writeUTF(msg);

            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // Close connections
        try{
            input.close();
            output.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
    }
}
