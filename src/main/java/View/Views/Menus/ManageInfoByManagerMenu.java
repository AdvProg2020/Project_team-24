package View.Views.Menus;

public class ManageInfoByManagerMenu extends Menu{
    private static ManageInfoByManagerMenu menu;
    public ManageInfoByManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageInfoByManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageInfoByManagerMenu(name, parent);
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
