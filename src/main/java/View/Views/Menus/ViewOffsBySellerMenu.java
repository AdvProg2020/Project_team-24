package View.Views.Menus;

public class ViewOffsBySellerMenu extends Menu{
    private static ViewOffsBySellerMenu menu;
    public ViewOffsBySellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewOffsBySellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewOffsBySellerMenu(name, parent);
        }
        return menu;
    }

    // Tavabe

    @Override
    public void help() {

    }
}
