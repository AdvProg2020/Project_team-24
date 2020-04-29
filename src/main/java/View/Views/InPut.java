package View.Views;

import java.util.Scanner;

public class InPut {

    private OutPut output = new OutPut();

    private static boolean isRunning = true;

    public static void setIsRunning(boolean isRunning) {
        InPut.isRunning = isRunning;
    }

    public void start() {

        Scanner scanner = MenuHandler.getScanner();

        while (isRunning) {

            String command = scanner.nextLine().trim();

            output.handleCommand(command);
        }
    }
}
