package B_Server.Server.RequestHandler;

import B_Server.Server.SendAndReceive.SendAndReceive;
import MessageFormates.MessagePattern;
import MessageFormates.MessageSupplier;
import Toolkit.JsonHandler.JsonHandler;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
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
            close();
        }
    }

    public void receiveByteArray(OutputStream fileOutputStream) {
        try {
            blabber.receiveByteArray(fileOutputStream);
        } catch (IOException e) {
            blabber.close();
        }
    }

    public void writeByteArray(byte[] bytes) {
        try {
            blabber.writeByteArray(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            blabber.close();
        }
    }

    @Override
    public void close() {
        goodBye = true;
    }

    @Override
    public void run() {

        while (true) try {

            if (goodBye) {
                blabber.close();
                return;
            }

            List<String> readMessage = readMessage(blabber.receiveMessage());
            System.out.println(readMessage);
            SendAndReceive.messageAnalyser(
                    readMessage.get(0),
                    readMessage.get(1),
                    new JsonHandler<List>().JsonToObject(readMessage.get(2), List.class),
                    this
            );

        } catch (IOException e) {
            close();
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

        public void close() {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) throws IOException {
            outputStream.writeUTF(message);
            outputStream.flush();
        }

        public String receiveMessage() throws IOException {
            return inputStream.readUTF();
        }

        public void receiveByteArray(OutputStream fileOutputStream) throws IOException {
            byte[] bytes = new byte[500000];
            for (int i = inputStream.read(bytes); i > 0; i = inputStream.read(bytes)) {
                fileOutputStream.write(bytes, 0, i);
            }
            fileOutputStream.close();
        }

        public void writeByteArray(byte[] bytes) throws IOException {
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
        }
    }
}


