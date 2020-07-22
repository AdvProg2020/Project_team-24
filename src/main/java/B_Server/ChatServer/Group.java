package B_Server.ChatServer;

import Toolkit.Connection.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group extends Thread implements AutoCloseable{

    private boolean isActive = true;
    private ServerSocket serverSocket = new ServerSocket(0);
    private String groupName;
    private List<Listener> listenerList = Collections.synchronizedList(new ArrayList<>());

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String getGroupName() {
        return groupName;
    }

    public Group(String groupName) throws IOException {
        this.groupName = groupName;
    }

    @Override
    public void close() throws Exception {
        for (Listener listener : listenerList) {
            listener.close();
        }
        isActive = false;
    }

    @Override
    public void run() {

        while (isActive) try {

            Socket socket = serverSocket.accept();
            Listener listener = new Listener(socket);
            listener.setDaemon(true);
            listener.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Listener extends Connection implements AutoCloseable {

        private boolean isListen = true;

        public Listener(Socket socket) throws IOException {
            super(socket);
        }

        @Override
        public void run() {

            while (isListen) try {

                String read = read();

                for (Listener con : listenerList) if (con != this) con.write(read);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void close() throws Exception {
            super.close();
            isListen = false;
        }
    }
}
