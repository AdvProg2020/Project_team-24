package View.Menus;

import View.MenuHandler;

import java.util.Optional;

public class GuestMenu extends Menu {

    private static GuestMenu menu;

    private GuestMenu(String name) {
        super(name);
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in GuestMenu."));
    }

    public static GuestMenu getInstance(String name) {
        if (menu == null) {
            menu = new GuestMenu(name);
        }
        return menu;
    }

//    public void OpenViewCart() {
//        MenuHandler.setCurrentMenu(ViewCartByGuestMenu.getMenu());
//    }

    public void OpenUserArea() {
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println(
                "You're in managerMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.UserArea" + System.lineSeparator() +
//                        "2.viewCart" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        System.out.println(
//                "OpenViewCart : To open cart menu" + System.lineSeparator() +
                        "OpenUserArea : To open user area" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
