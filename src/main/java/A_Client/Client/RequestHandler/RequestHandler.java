package A_Client.Client.RequestHandler;

import A_Client.Client.MessageInterfaces.MessagePattern;
import A_Client.Client.MessageInterfaces.MessageSupplier;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread implements MessagePattern, MessageSupplier {

    private Blabber blabber;
    private String token;

    public RequestHandler(Socket socket, String token) {
        try {
            blabber = new Blabber(socket);
            blabber.sendMessage("");
        } catch (IOException e) {
            e.printStackTrace();
            errorInSendingMessage();
        }
    }

    private void errorInSendingMessage() {
        // errorHandling
    }

    public RequestHandler(Socket socket) {
        blabber = new Blabber(socket);
    }

    public Blabber getBlabber() {
        return blabber;
    }

    @Override
    public void run() {
        super.run();
    }

    private void handling() {
        Blabber blabber = this.blabber;
    }

    public static class Blabber {

        protected DataInputStream inputStream;
        protected DataOutputStream outputStream;

        public Blabber(Socket socket) {

            try {
                this.inputStream = new DataInputStream(
                        new BufferedInputStream(
                                socket.getInputStream()));

                this.outputStream = new DataOutputStream(
                        new BufferedOutputStream(
                                socket.getOutputStream()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public synchronized void sendMessage(String message) throws IOException {
            outputStream.writeUTF(message);
            outputStream.flush();
        }

        public synchronized String receiveMessage() throws IOException {
            return inputStream.readUTF();
        }
    }
}


