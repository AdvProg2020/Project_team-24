package View.Menus;

import java.util.Optional;

public class GuestMenu extends Menu{

    private static GuestMenu menu;

    private GuestMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static GuestMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new GuestMenu(name, parent);
        }
        return menu;
    }

    @Override
    public void show() {

    }

    public static Menu getMenu(){
        return Optional.ofNullable(menu).orElseThrow();
    }
    @Override
    public void help() {
        System.out.println(
                "----------------------------------------------"
        );
    }
}
