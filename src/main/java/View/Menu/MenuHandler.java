package View.Menu;
import Controller.Controllers.Menu.LoggedInMenu;
import Controller.Controllers.Menu.Menu;
import Controller.Controllers.Menu.UserAreaMenu;

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


}