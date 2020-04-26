package Controller.Controllers.Menus;

public class LoggedInMenu extends Menu {

    private static LoggedInMenu menu;

    public LoggedInMenu(String name) {
        super(name);
    }

    public static LoggedInMenu getMenu() {

        if (LoggedInMenu.menu == null) {
            LoggedInMenu.menu = new LoggedInMenu("Logged In Menu");
        }

        return menu;
    }

    @Override
    public void help() {

    }
}
