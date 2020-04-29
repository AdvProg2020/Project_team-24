package View.Views.Menus;

public class SortingProductsMenu extends Menu{

    private static SortingProductsMenu menu;

    private SortingProductsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static SortingProductsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new SortingProductsMenu(name, parent);
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
