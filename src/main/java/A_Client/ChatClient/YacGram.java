package A_Client.ChatClient;

import Structs.MiniAccount;
import Toolkit.Connection.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class YacGram extends Connection implements AutoCloseable {

    private CountDownLatch RunLatch = new CountDownLatch(1);
    private boolean _online = false;
    private final MiniAccount miniAccount;
    private final ServerSocket serverSocket = new ServerSocket(0);
    private final List<ChatRoom> chatRoomList = Collections.synchronizedList(new ArrayList<>());

    public YacGram(MiniAccount miniAccount, Socket mainServer) throws IOException {
        super(mainServer);
        this.miniAccount = miniAccount;
    }

    public List<ChatRoom> getChatRoomList() {
        return chatRoomList;
    }

    public MiniAccount getMiniAccount() {
        return miniAccount;
    }

    public void online() {

        try {

            write(MessageEnum.Online + "#" + miniAccount.getAccountId() + "#" + serverSocket.getLocalPort());
            waitRead();
            _online = true;

            start();

            RunLatch.countDown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void offline() {

        try {

            write(MessageEnum.Offline + "#" + miniAccount.getAccountId());
            waitRead();
            _online = false;

            RunLatch = new CountDownLatch(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newGroup(String groupName) {

        try {

            RunLatch.await();

            write(MessageEnum.NewGroup + "#" + groupName);

            openSocket();

            waitRead();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void join(String groupName) {

        try {

            RunLatch.await();

            write(MessageEnum.Join + "#" + groupName);

            openSocket();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void connect(String accountId) {

        try {

            RunLatch.await();

            write(MessageEnum.Connect + "#" + accountId);

            openSocket();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void openSocket() throws IOException {

        String read = read();

        if (read.equals("Sag")) return;

        Socket socket = new Socket("localhost", Integer.parseInt(read));

        openChatRoom(socket);
    }

    private void openChatRoom(Socket socket) throws IOException {

        Connection connection = new Connection(socket);
        ChatRoom chatRoom = new ChatRoom(connection, miniAccount);
        chatRoom.setDaemon(true);
        chatRoom.start();

        chatRoomList.add(chatRoom);
    }

    public void waitRead() throws IOException {
        read();
    }

    @Override
    public void close() {
        offline();
    }

    @Override
    public void run() {

        while (_online) try {

            Socket socket = serverSocket.accept();
            System.out.println("123456");
            openChatRoom(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    enum MessageEnum {
        Online, Offline, Connect, NewGroup, Join
    }
}
