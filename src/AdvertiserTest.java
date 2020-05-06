import Controller.AdvertiserController;
import Models.Packet;

import java.util.UUID;

public class AdvertiserTest {
    public static void main(String[] args) {

        AdvertiserController advertiser = new AdvertiserController("localhost",5000);

        Packet packet = new Packet();
        packet.setGuid(UUID.randomUUID().toString());
        packet.setType("Advertiser");
        packet.setTopicName("Sports");
        advertiser.connectToServer(packet);

    }
}
