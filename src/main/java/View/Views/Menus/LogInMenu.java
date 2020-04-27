package View.Views.Menus;

public class LogInMenu extends Menu {

    private static LogInMenu menu;

    private LogInMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static LogInMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new LogInMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
