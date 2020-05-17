package View;

import java.util.Scanner;

// start.

public class InPut {

    private static boolean isRunning = true;

    private static boolean show = true;

    public static void setIsRunning(boolean isRunning) {
        InPut.isRunning = isRunning;
    }

    public void start(OutPut outPut) {

        Scanner scanner = MenuHandler.getScanner();

        while (isRunning) {

            if (show) {
                MenuHandler.getCurrentMenu().show();
            }

            String command = scanner.nextLine().trim();

            if (command.matches("^help$")) {
                show = false;
            } else
                show = true;

            outPut.handleCommand(command);
        }
    }
}
