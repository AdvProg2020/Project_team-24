package View.Menus;

public class ProductMenu extends Menu{
    private static ProductMenu menu;
    public ProductMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ProductMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ProductMenu(name, parent);
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
