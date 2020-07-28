package B_Server.ChatServer;

import Toolkit.Connection.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QreGram extends Thread {

    private final ServerSocket serverSocket = new ServerSocket(0);
    private final List<Group> groupList = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, String> onlineAccounts = new ConcurrentHashMap<>();

    public QreGram() throws IOException {
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Map<String, String> getOnlineAccounts() {
        return onlineAccounts;
    }

    @Override
    public void run() {

        while (true) try {

            Socket socket = serverSocket.accept();
            new MessageHandling(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MessageHandling extends Connection {

        public MessageHandling(Socket socket) throws IOException {
            super(socket);
        }

        @Override
        public void run() {

            while (true) try {

                String message = read();
                System.out.println(message);
                String[] messages = message.split("#");

                switch (messages[0]) {
                    case "Online":
                        onlineAccounts.put(messages[1], messages[2]);
                        write("Success");
                        break;
                    case "Offline":
                        onlineAccounts.remove(messages[1]);
                        write("Success");
                        break;
                    case "Connect":
                        write(onlineAccounts.getOrDefault(messages[1], "Sag"));
                        break;
                    case "NewGroup":
                        Group e = new Group(messages[1]);
                        groupList.add(e);
                        e.start();
                    case "Join":
                        Group g = groupList.stream().filter(group -> group.getGroupName().equals(messages[1]))
                                .findFirst().orElse(null);
                        if (g != null) write(g.getServerSocket().getLocalPort() + "");
                        else write("Sag");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
