package View.Menus;

public class ManageProductsBySellerMenu extends Menu {
    private static ManageProductsBySellerMenu menu;
    public ManageProductsBySellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManageProductsBySellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageProductsBySellerMenu(name, parent);
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
