package View.Menus;

public class ManageCategoriesByManagerMenu extends Menu {
    private static ManageCategoriesByManagerMenu menu;
    public ManageCategoriesByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {

    }

    public static ManageCategoriesByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageCategoriesByManagerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe
    public static Menu getMenu(){
        return menu;
    }
    @Override
    public void help() {

    }

}
