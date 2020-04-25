package View.Views.Menus;
import Controller.Controllers.Menus.LoggedInMenu;
import Controller.Controllers.Menus.Menu;
import Controller.Controllers.Menus.UserAreaMenu;
import java.util.Scanner;
import java.util.Scanner;
public class MenuHandler {


    public static Menu currentMenu;

    private static void initMenus() {
        UserAreaMenu.getMenu().addSubMenu(LoggedInMenu.getMenu());

    }


    public static void main(String[] args) {
    }


    public static void showMenu() {
        MenuHandler.currentMenu.showMenu();
    }


    public static void nextMove() {

    }

    public static Scanner getCommand() {
        return null;
    }

}