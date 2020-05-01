package View;

import java.util.Scanner;

// start.

public class InPut {

    private OutPut output = OutPut.getInstance();

    private static boolean isRunning = true;

    public static void setIsRunning(boolean isRunning) {
        InPut.isRunning = isRunning;
    }

    public void start() {

        Scanner scanner = MenuHandler.getScanner();

        while (isRunning) {

            MenuHandler.getCurrentMenu().show();

            String command = scanner.nextLine().trim().toLowerCase();

            output.handleCommand(command);
        }
    }
}
