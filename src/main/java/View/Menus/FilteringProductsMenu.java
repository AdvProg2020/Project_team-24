package View.Menus;

public class FilteringProductsMenu extends Menu {

    private static FilteringProductsMenu menu;

    private FilteringProductsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static FilteringProductsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new FilteringProductsMenu(name, parent);
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
