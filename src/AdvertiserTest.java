import Controller.AdvertiserController;
import Models.Packet;

import java.util.Scanner;
import java.util.UUID;

public class AdvertiserTest {
    public static void main(String[] args) {

        AdvertiserController advertiser = new AdvertiserController("localhost",5000);

        Packet packet = new Packet();
        packet.setGuid(UUID.randomUUID().toString());
        packet.setType("Advertiser");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter topic name: ");
        String topicName = sc.nextLine();
        packet.setTopicName(topicName);

        advertiser.connectToServer(packet);

    }
}
