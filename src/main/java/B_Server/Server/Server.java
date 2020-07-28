package B_Server.Server;

import B_Server.Bank.BankAPI;
import B_Server.ChatServer.QreGram;
import B_Server.Model.ModelUnit;
import B_Server.Server.InstantInfo.InstantInfo;
import B_Server.Server.RequestHandler.RequestHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Server extends Thread {

    private static final List<InstantInfo> clients = new ArrayList<>();
    private final ServerSocket mineServer = new ServerSocket(0);
    private static final Scanner scanner = new Scanner(System.in);
    private static int qreGramPort;

    static {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(Server::checkClient,0,1, TimeUnit.MINUTES);
    }

    private static void checkClient() {
        clients.removeIf(instantInfo ->
                ChronoUnit.MINUTES.between(instantInfo.getTmo(), LocalTime.now()) > 5);
    }

    public Server() throws IOException {
    }

    public static synchronized void addClient(InstantInfo instantInfo) {
        clients.add(instantInfo);
    }

    public static synchronized void removeClient(InstantInfo instantInfo) {
        clients.remove(instantInfo);
    }

    public static List<InstantInfo> getClients() {
        return new ArrayList<>(clients);
    }

    public static InstantInfo getInfoByToken(String token) {
        return clients.stream()
                .filter(instantInfo -> token.equals(instantInfo.getMy_Token()))
                .findFirst().orElse(null);
    }

    public ServerSocket getMineServer() {
        return mineServer;
    }

    public static int getQreGramPort() {
        return qreGramPort;
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

    public static void main(String[] args) {
        ModelUnit.preprocess_loadLists();

        while (true) try {
            System.out.println("Bank Server: Enter 'host' and 'usePort' ...");
            String[] inputs = scanner.nextLine().split(" ");
            BankAPI.ConnectToBankServer(inputs[0], Integer.parseInt(inputs[1]));
            break;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        while (true) try {
            QreGram qreGram = new QreGram();
            qreGram.start();
            ServerSocket serverSocket = qreGram.getServerSocket();
            qreGramPort = serverSocket.getLocalPort();
            System.out.println("QreGram: Host/" + serverSocket.getInetAddress().getHostName()
                    + " Port/" + serverSocket.getLocalPort());
            break;
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) try {
            Server server = new Server();
            server.start();
            ServerSocket mineServer = server.getMineServer();
            System.out.println("Server: Host/" + mineServer.getInetAddress().getHostName()
                    + " Port/" + mineServer.getLocalPort());
            break;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("***************************START****************************");
    }
}
