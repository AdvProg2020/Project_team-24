package View.Views.Menus;

public class ManageUsersByManagerMenu extends Menu {
    private static ManageUsersByManagerMenu menu;

    private ManageUsersByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageUsersByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageUsersByManagerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe
    public void addProduct(){

    }

    @Override
    public void help() {

    }
}
