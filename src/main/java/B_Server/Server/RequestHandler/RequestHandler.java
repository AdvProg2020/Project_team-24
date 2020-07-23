package B_Server.Server.RequestHandler;

import B_Server.Server.SendAndReceive.SendAndReceive;
import MessageFormates.MessagePattern;
import MessageFormates.MessageSupplier;
import Toolkit.JsonHandler.JsonHandler;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class RequestHandler extends Thread implements MessagePattern, MessageSupplier, AutoCloseable {

    private Blabber blabber;
    private boolean goodBye;

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

    @Override
    public void close() {
        goodBye = true;
    }

    @Override
    public void run() {

        while (true) try {

            List<String> readMessage = readMessage(blabber.receiveMessage());
            SendAndReceive.messageAnalyser(
                    readMessage.get(0),
                    readMessage.get(1),
                    new JsonHandler<List>().JsonToObject(readMessage.get(2), List.class),
                    this
            );

            if (goodBye) {
                blabber.close();
                break;
            }

        } catch (IOException e) {
            e.printStackTrace();
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


