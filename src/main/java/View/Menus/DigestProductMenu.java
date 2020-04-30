package View.Menus;

public class DigestProductMenu extends Menu {
    private static DigestProductMenu menu;

    private DigestProductMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static DigestProductMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new DigestProductMenu(name, parent);
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
