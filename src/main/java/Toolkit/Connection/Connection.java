package Toolkit.Connection;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread implements AutoCloseable {

    protected DataInputStream inputStream;
    protected DataOutputStream outputStream;

    public Connection(Socket socket) throws IOException {
        this.inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void write(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
    }

    public String read() throws IOException {
        return inputStream.readUTF();
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
        outputStream.close();
    }
}
