package A_Client.Client;

import A_Client.Client.ClientInfo.ClientInfo;
import MessageFormates.MessageSupplier;
import A_Client.Client.RequestHandler.RequestHandler;

import java.io.File;
import java.io.IOException;
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

    public List<String> sendAndReceive(MessageSupplier.RequestType requestType, File file, List<String> list) {
        sendMessage(generateMessage(requestType, list));
        requestHandler.sendFile(file);
        String answer = receiveMessage();
        return readMessage(answer);
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

