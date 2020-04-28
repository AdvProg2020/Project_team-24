package View.Views.Menus;

public class ViewOrdersByBuyerMenu extends Menu{
    private static ViewOrdersByBuyerMenu menu;
    public ViewOrdersByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewOrdersByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewOrdersByBuyerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
