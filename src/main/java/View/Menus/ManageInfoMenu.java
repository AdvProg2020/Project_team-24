package View.Menus;

public class ManageInfoMenu extends  Menu{
    private static ManageInfoMenu menu;
    public ManageInfoMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageInfoMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageInfoMenu(name, parent);
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
