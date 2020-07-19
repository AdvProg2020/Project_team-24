package Structs;

public class MiniComment {

    private final String id;
    private final String sender;
    private final String title;
    private final String content;

    public String getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public MiniComment(String id, String sender, String title, String content) {
        this.id = id;
        this.sender = sender;
        this.title = title;
        this.content = content;
    }
}
