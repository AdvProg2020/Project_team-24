package Toolkit.Connection;

public class Message {

    private final String accountId;
    private final String username;
    private final String message;

    public String getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Message(String accountId, String username, String message) {
        this.accountId = accountId;
        this.username = username;
        this.message = message;
    }
}
