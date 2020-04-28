package View.Views.Menus;

public class ManageInfoBySellerMenu extends Menu {
    private static ManageInfoBySellerMenu menu;
    public ManageInfoBySellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageInfoBySellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageInfoBySellerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
