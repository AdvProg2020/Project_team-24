package View;

import java.util.Scanner;

// start.

public class InPut {

    private static boolean isRunning = true;

    public static void setIsRunning(boolean isRunning) {
        InPut.isRunning = isRunning;
    }

    public void start(OutPut outPut) {

        Scanner scanner = MenuHandler.getScanner();

        while (isRunning) {

            MenuHandler.getCurrentMenu().show();

            String command = scanner.nextLine().trim();

            outPut.handleCommand(command);
        }
    }
}
