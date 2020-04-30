package View.Menus;

public class AuctionsMenu extends Menu {

    private static AuctionsMenu menu;

    private AuctionsMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static AuctionsMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new AuctionsMenu(name, parent);
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
