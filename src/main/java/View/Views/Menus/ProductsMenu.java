package View.Views.Menus;

public class ProductsMenu extends Menu {

    private static ProductsMenu menu;

    private ProductsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ProductsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ProductsMenu(name, parent);
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
