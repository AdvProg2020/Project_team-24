package Controller.Controllers.Menus;

public class GuestMenu extends Menu{

    private static GuestMenu menu;

    private GuestMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static GuestMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new GuestMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
