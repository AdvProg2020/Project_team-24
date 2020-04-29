package View.Views.Menus;

public class MainMenu extends Menu {

    private static MainMenu menu;

    private MainMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static MainMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new MainMenu(name, parent);
        }
        return menu;
    }

    @Override
    public void patternToCommand(String command) {

    }

    // Tavabe

    public static Menu getMenu(){
        return menu;
    }

    @Override
    public void help() {

    }
}