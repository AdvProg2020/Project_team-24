package A_Client.Client;

import A_Client.Client.RequestHandler.RequestHandler;

import java.io.*;
import java.net.Socket;
import java.util.List;

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
        return requestHandler.getFirstElementAndRemove();
    }

    public void sendMessage(String message) {
        requestHandler.sendMessage(message);
    }

    public String generateMessage(List<String> inputs) {
        return requestHandler.generateMessage(inputs);
    }

    public List<String> readMessage(String input) {
        return requestHandler.readMessage(input);
    }

    @Override
    protected void finalize() {
        requestHandler.close();
    }
}

