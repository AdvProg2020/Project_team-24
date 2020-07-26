package B_Server.Bank;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class handles initiating connection to bankAPI ,sending requests to Bank server
 * and also responses from Bank server.
 */
public class BankAPI {
    private static final Scanner scanner = new Scanner(System.in);
    public static int PORT = 2222;
    public static String IP = "192.168.1.4";

    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;


    List<String> exceptionList = Arrays.asList("invalid username or password","invalid receipt type","invalid money",
            "”invalid parameters passed", "token is invalid","token expired","source account id is invalid",
            "dest account id is invalid","equal source and dest account", "invalid account id",
            "your input contains invalid characters","invalid receipt id","receipt is paid before",
            "source account does not have enough money","invalid account id","passwords do not match",
            "username is not available","token is invalid","token expired","invalid input","database error");

    /**
     * This method is used to add initiating socket and IN/OUT data stream .
     *
     * @throws IOException when IP/PORT hasn't been set up properly.
     */
    public static void ConnectToBankServer() throws IOException {
        try {
            String[] inputs = scanner.nextLine().split(" ");
            Socket socket = new Socket(inputs[0], Integer.parseInt(inputs[1]));
            outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }

    /**
     * This method is used to start a Thread ,listening on IN data stream.
     */
    public static void StartListeningOnInput() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(inputStream.readUTF());
                } catch (IOException e) {
                    System.out.println("disconnected");
                    System.exit(0);
                }
            }
        }).start();
    }

    /**
     * This method is used to send message with value
     *
     * @param msg to Bank server.
     * @throws IOException when OUT data stream been interrupted.
     */
    public static void SendMessage(String msg) throws IOException {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            throw new IOException("Exception while sending message:");
        }
    }

    /**
     * This method is used to illustrate an example of using methods of this class.
     */
    public static void main(String[] args) {
        try {
            ConnectToBankServer();
            StartListeningOnInput();
            while (true) {
                SendMessage(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


    private String sendAndReceive(String command, List<String> list) throws IOException {
        String message = list.stream()
                .reduce(command, (s1, s2) -> s1 + " " + s2);
        System.out.println(message);

        outputStream.writeUTF(message);
        outputStream.flush();
        return inputStream.readUTF();
    }

    public String pay(List<String> list) throws IOException {


        String tokenOutput = sendAndReceive("get_token", list.subList(0, 2));
        if (exceptionList.contains(tokenOutput)) return tokenOutput;

        List<String> rec = Stream.concat(Stream.of(tokenOutput),
                list.subList(2, 7).stream()).collect(Collectors.toList());
        String createReceipt = sendAndReceive("create_receipt", rec);

        if (exceptionList.contains(createReceipt)) return createReceipt;
        String pay = sendAndReceive("pay", Collections.singletonList(createReceipt));
        return pay;
    }

    public String getBalance(List<String> list) throws IOException {
        String getToken  = sendAndReceive("get_token",list.subList(0, 2));
        if (exceptionList.contains(getToken)) return getToken;
        List<String> rec = Collections.singletonList(getToken);
        String getBalance= sendAndReceive("get_balance", rec);
        return getBalance;
    }

}
