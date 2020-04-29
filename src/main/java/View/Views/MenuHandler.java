package View.Views;

import View.Views.Menus.Menu;
import View.Views.Menus.UserAreaMenu;
import java.util.Scanner;

public class MenuHandler {

    private static Menu currentMenu;

    private static Scanner scanner = new Scanner(System.in);



    private static void initMenus() {

    }

    public static void main(String[] args) {
        initMenus();

        InPut input = new InPut();
        input.start();
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static Scanner getScanner() {
        return scanner;
    }
}