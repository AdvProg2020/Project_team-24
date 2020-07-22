package A_Client.ChatClient;

import Toolkit.JsonHandler.JsonHandler;
import Structs.MiniAccount;
import Toolkit.Connection.Connection;
import Toolkit.Connection.Message;
import com.gilecode.yagson.YaGson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatRoom extends Thread {

    private JsonHandler<Message> jsonHandler = new JsonHandler<>();
    private YaGson yaGson = new YaGson();
    private Connection connection;
    private MiniAccount me;
    private List<Message> history = Collections.synchronizedList(new ArrayList<>());

    public ChatRoom(Connection connection, MiniAccount me) {
        this.connection = connection;
        this.me = me;
    }

    public List<Message> getHistory() {
        return history;
    }

    @Override
    public void run() {

        while (true) try {

            Message message = jsonHandler.JsonToObject(connection.read(), Message.class);
            history.add(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String message) {
        try {
            Message mess = new Message(me.getAccountId(), me.getUsername(), message);
            String toJson = yaGson.toJson(mess);
            connection.write(toJson);
            history.add(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
