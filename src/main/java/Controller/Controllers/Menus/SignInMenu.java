package Controller.Controllers.Menus;

public class SignInMenu extends Menu {

    private static SignInMenu menu;

    private SignInMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static SignInMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SignInMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}