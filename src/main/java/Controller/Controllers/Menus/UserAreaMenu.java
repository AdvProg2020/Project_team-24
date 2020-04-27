package Controller.Controllers.Menus;


public class UserAreaMenu extends Menu {

    private static UserAreaMenu menu;

    private UserAreaMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static UserAreaMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new UserAreaMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
