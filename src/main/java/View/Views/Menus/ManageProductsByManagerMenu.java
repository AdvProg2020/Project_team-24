package View.Views.Menus;

public class ManageProductsByManagerMenu extends Menu {
    private static ManageProductsByManagerMenu menu;
    public ManageProductsByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageProductsByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageProductsByManagerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }


}
