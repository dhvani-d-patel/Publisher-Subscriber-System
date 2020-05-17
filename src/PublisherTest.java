import Controller.PublisherController;
import Models.Packet;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PublisherTest {
    public static void main(String[] args) {

        PublisherController publisher = new PublisherController("localhost", 5000, 4000);

        Packet packet = new Packet();
        packet.setGuid(UUID.randomUUID().toString());
        packet.setType("Publisher");

        publisher.connectToServer(packet);

        List<String> topicList = publisher.openConnection();
        for (String topicName : topicList) {
            System.out.print(topicName + "\t");
        }
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter topic name from the given above choices: ");
        String topicName = sc.nextLine();
        packet.setTopicName(topicName);

        System.out.print("Enter the article to publish for "+ topicName + " topic: ");
        String articleContent = sc.nextLine();
        packet.setArticleContent(articleContent);

        publisher.connectToServer(packet);
    }
}
