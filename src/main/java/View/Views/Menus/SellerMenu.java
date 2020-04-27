package View.Views.Menus;

public class SellerMenu extends Menu {

    private static SellerMenu menu;

    private SellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static SellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SellerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
