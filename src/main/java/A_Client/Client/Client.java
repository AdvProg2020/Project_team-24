package A_Client.Client;

import A_Client.Client.ClientInfo.ClientInfo;
import A_Client.Client.RequestHandler.RequestHandler;
import MessageFormates.MessageSupplier;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Client {

    private Socket socket;
    private ClientInfo clientInfo = new ClientInfo();
    private RequestHandler requestHandler;

    public Client(String host, int port) {

        try {

            socket = new Socket(host, port);
            requestHandler = new RequestHandler(socket);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public String receiveMessage() {
        return requestHandler.receiveMessage();
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
        List<String> readMessage = readMessage(receiveMessage());
        clientInfo.setToken(readMessage.get(0));
        return readMessage;
    }
}

