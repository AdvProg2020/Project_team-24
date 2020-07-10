package A_Client.Client;

import A_Client.Client.RequestHandler.RequestHandler;

import java.io.*;
import java.net.Socket;

public class Client {

    private RequestHandler requestHandler;

    public Client(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            requestHandler = new RequestHandler(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            return requestHandler.getBlabber().receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void sendMessage(String message) {
        try {
            requestHandler.getBlabber().sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

