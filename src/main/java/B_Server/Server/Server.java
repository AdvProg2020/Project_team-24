package B_Server.Server;

import A_Client.Client.RequestHandler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Server {

    private ServerSocket mineServer;

    public Server() {
        try {
            mineServer = new ServerSocket(0);
        } catch (IOException e) {
            e.printStackTrace();
            errorInServerSocket();
        }
    }

    private void errorInServerSocket() {
        // errorHandling
    }

    public ServerSocket getMineServer() {
        return mineServer;
    }

    public void listen() {
        Thread listener = new Thread(this::listener);
        listener.start();
    }

    private void listener() {
        while (true) {

            try {

                Socket socket = mineServer.accept();
                RequestHandler requestHandler = new RequestHandler(socket, createToken());
                requestHandler.start();

            } catch (IOException e) {
                e.printStackTrace();
                errorInListeningRequest();
            }
        }
    }

    private void errorInListeningRequest() {
        // errorHandling
    }

    private String createToken() {
        return UUID.randomUUID().toString();
    }
}
