package B_Server.Bank;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class handles initiating connection to bankAPI ,sending requests to Bank server
 * and also responses from Bank server.
 */
public class BankAPI {

    private static final Scanner scanner = new Scanner(System.in);
    private static DataOutputStream outputStream;
    private static DataInputStream inputStream;

    private static List<String> exceptionList = Arrays.asList("invalid username or password", "invalid receipt type", "invalid money",
            "invalid parameters passed", "token is invalid", "token expired", "source account id is invalid",
            "dest account id is invalid", "equal source and dest account", "invalid account id",
            "your input contains invalid characters", "invalid receipt id", "receipt is paid before",
            "source account does not have enough money", "invalid account id", "passwords do not match",
            "username is not available", "token is invalid", "token expired", "invalid input", "database error");

    private static void SendMessage(String msg) throws IOException {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            throw new IOException("Exception while sending message:");
        }
    }

    public static void ConnectToBankServer(String host, int usePort) throws IOException {
        try {
            Socket socket = new Socket(host, usePort);
            outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
            throw new IOException("Exception while initiating connection:");
        }
    }

    @NotNull
    public static String sendAndReceive(String command, @NotNull List<String> list) throws IOException {
        String message = list.stream().reduce(command, (s1, s2) -> s1 + " " + s2);
        SendMessage(message);
        return inputStream.readUTF();
    }

    public static String pay(@NotNull List<String> list) throws IOException {

        String tokenOutput = sendAndReceive("get_token", list.subList(0, 2));
        if (exceptionList.contains(tokenOutput)) return tokenOutput;

        List<String> rec = Stream.concat(Stream.of(tokenOutput),
                list.subList(2, 7).stream()).collect(Collectors.toList());

        String createReceipt = sendAndReceive("create_receipt", rec);
        if (exceptionList.contains(createReceipt)) return createReceipt;

        List<String> list2 = Collections.singletonList(createReceipt);
        return sendAndReceive("pay", list2);
    }

    public static String getBalance(@NotNull List<String> list) throws IOException {
        String getToken = sendAndReceive("get_token", list.subList(0, 2));
        if (exceptionList.contains(getToken)) return getToken;
        List<String> rec = Collections.singletonList(getToken);
        return sendAndReceive("get_balance", rec);
    }

    public static void main(String[] args) {

//        while (true) try {
//            System.out.println("Bank Server: Enter 'host' and 'usePort' ...");
//            String[] inputs = scanner.nextLine().split(" ");
//            BankAPI.ConnectToBankServer(inputs[0], Integer.parseInt(inputs[1]));
//            break;
//        } catch (IOException | NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            String s = sendAndReceive("create_account", Arrays.asList("Yac", "Yac", "Yac", "Yac", "Yac"));
//            System.out.println(s);
//            String pay = pay(Arrays.asList("Yac", "Yac", "deposit", "78888", "-1","10001", "info"));
//            System.out.println(pay);
//            getBalance(Arrays.asList("Yac","Yac"));
//            System.out.println(getBalance(Arrays.asList("Yac","Yac")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
