package View.Views.Menus;

public class ManageCategoriesByManagerMenu extends Menu {
    private static ManageCategoriesByManagerMenu menu;
    public ManageCategoriesByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageCategoriesByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageCategoriesByManagerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }

}
