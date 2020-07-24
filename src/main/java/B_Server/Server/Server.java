package B_Server.Server;

import B_Server.Model.ModelUnit;
import B_Server.Server.InstantInfo.InstantInfo;
import B_Server.Server.RequestHandler.RequestHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Server extends Thread {

    private static List<InstantInfo> clients = new ArrayList<>();
    private ServerSocket mineServer = new ServerSocket(0);

    public Server() throws IOException {
    }

    public static synchronized void addClient(InstantInfo instantInfo) {
        clients.add(instantInfo);
    }

    public static synchronized void removeClient(InstantInfo instantInfo) {
        clients.remove(instantInfo);
    }

    public static InstantInfo getInfoByToken(String token) {
        return clients.stream()
                .filter(instantInfo -> token.equals(instantInfo.getMy_Token()))
                .findFirst().orElse(null);
    }

    public ServerSocket getMineServer() {
        return mineServer;
    }

    @Override
    public void run() {

        while (true) try {

            Socket socket = mineServer.accept();
            RequestHandler requestHandler = new RequestHandler(socket);
            requestHandler.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public static String createToken() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws IOException {
        ModelUnit.preprocess_loadLists();
        Server server = new Server();
        server.start();

        ServerSocket mineServer = server.getMineServer();
        System.out.println("Server: Host/" + mineServer.getInetAddress().getHostName()
                + " Post/" + mineServer.getLocalPort());
    }
}
