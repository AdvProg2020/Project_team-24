package A_Client.Client.RequestHandler;

import MessageFormates.MessagePattern;
import MessageFormates.MessageSupplier;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread implements MessagePattern, MessageSupplier {

    private Blabber blabber;

    public RequestHandler(Socket socket) {
        blabber = new Blabber(socket);
    }

    public void sendMessage(String message) {
        try {
            blabber.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            return blabber.receiveMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class Blabber {

        protected DataInputStream inputStream;
        protected DataOutputStream outputStream;

        public Blabber(@NotNull Socket socket) {

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

        public void close() throws IOException {
            inputStream.close();
            outputStream.close();
        }

        public void sendMessage(String message) throws IOException {
            outputStream.writeUTF(message);
            outputStream.flush();
        }

        public String receiveMessage() throws IOException {
            return inputStream.readUTF();
        }
    }
}


