package View.Menus;

public class ViewCartByGuestMenu extends Menu {
    private static ViewCartByGuestMenu menu;
    public ViewCartByGuestMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewCartByGuestMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewCartByGuestMenu(name, parent);
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
