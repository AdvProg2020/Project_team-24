package A_Client.Client.RequestHandler;

import MessageInterfaces.MessagePattern;
import MessageInterfaces.MessageSupplier;
import org.codehaus.plexus.util.IOUtil;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RequestHandler extends Thread implements MessagePattern, MessageSupplier {

    private CountDownLatch downLatch = new CountDownLatch(1);
    private List<String> messages = new ArrayList<>();
    private Blabber blabber;
    private boolean goodBye;

    public RequestHandler(Socket socket, String token) {
        blabber = new Blabber(socket);
    }

    public String getFirstElementAndRemove() {
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String mess = messages.get(0);
        messages.remove(mess);

        if (messages.isEmpty()) {
            downLatch = new CountDownLatch(1);
        }
        return mess;
    }

    public synchronized void sendMessage(String message) {
        try {
            blabber.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendFile(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            blabber.sendFile(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {

            try {

                String input = blabber.receiveMessage();
                messages.add(input);
                downLatch.countDown();

                if (goodBye) {
                    blabber.close();
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        goodBye = true;
    }

    private static class Blabber {

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

        public void close() throws IOException {
            inputStream.close();
            outputStream.close();
        }

        public synchronized void sendMessage(String message) throws IOException {
            outputStream.writeUTF(message);
            outputStream.flush();
        }

        public synchronized void sendFile(InputStream inputStream) throws IOException {
            IOUtil.copy(inputStream, outputStream);
        }

        public synchronized String receiveMessage() throws IOException {
            return inputStream.readUTF();
        }
    }
}


