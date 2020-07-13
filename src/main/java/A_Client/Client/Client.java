package A_Client.Client;

import A_Client.Client.ClientInfo.ClientInfo;
import A_Client.Client.MessageInterfaces.MessageSupplier;
import A_Client.Client.RequestHandler.RequestHandler;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {

    private ClientInfo clientInfo;
    private RequestHandler requestHandler;

    public Client(String host, int port) {

        try {

            Socket socket = new Socket(host, port);
            requestHandler = new RequestHandler(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public String receiveMessage() {
        return requestHandler.getFirstElementAndRemove();
    }

    public void sendMessage(String message) {
        requestHandler.sendMessage(message);
    }

    public String generateMessage(MessageSupplier.RequestType requestType, List<String> inputs) {
        return requestHandler.generateMessage(requestType, inputs);
    }

    public List<String> readMessage(String input) {
        return requestHandler.readMessage(input);
    }

    public List<String> sendAndReceive(MessageSupplier.RequestType requestType, List<String> list) {
        sendMessage(generateMessage(requestType, list));
        String answer = receiveMessage();
        return readMessage(answer);
    }

    @Override
    protected void finalize() {
        requestHandler.close();
    }
}

