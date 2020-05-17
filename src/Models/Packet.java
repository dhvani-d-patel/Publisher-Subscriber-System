package Models;

import java.io.Serializable;
import java.util.List;

public class Packet implements Serializable {

    private String guid;
    private String type;
    private String topicName;
    private String articleContent;
    private List<String> availableTopics;

    public List<String> getAvailableTopics() {
        return availableTopics;
    }

    public void setAvailableTopics(List<String> availableTopics) {
        this.availableTopics = availableTopics;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
