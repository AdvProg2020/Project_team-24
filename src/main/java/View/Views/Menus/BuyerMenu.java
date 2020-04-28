package View.Views.Menus;

public class BuyerMenu extends Menu{

    private static BuyerMenu menu;

    private BuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static BuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new BuyerMenu(name, parent);
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
