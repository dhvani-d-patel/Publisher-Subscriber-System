import Controller.ServerController;

public class ServerTest {
    public static void main(String[] args) {
        ServerController server = new ServerController(5000);
        server.connectToClient();
    }
}
