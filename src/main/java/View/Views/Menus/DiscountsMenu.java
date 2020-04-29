package View.Views.Menus;

public class DiscountsMenu extends Menu {

    private static DiscountsMenu menu;

    private DiscountsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static DiscountsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new DiscountsMenu(name, parent);
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
