package View.Menus;

import View.MenuHandler;

import java.util.Optional;

public class BuyerMenu extends Menu{

    private static BuyerMenu menu;

    private BuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static BuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new BuyerMenu(name, parent);
        }
        return menu;
    }

    public void viewPersonalInfo() {
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void viewCart() {
        MenuHandler.setCurrentMenu(ViewCartByBuyerMenu.getMenu());
    }

    public void viewBalance() {
        // yac
    }

    public void viewDiscountCodes() {
        // yac
    }

    public void purchase() {
        // yac
    }

    public void viewOrders() {
        MenuHandler.setCurrentMenu(ViewOrdersByBuyerMenu.getMenu());
    }

    public static Menu getMenu(){
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println(
                "You're in BuyerMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.ManageInfo" + System.lineSeparator() +
                        "2.CartMenu" + System.lineSeparator() +
                        "3.OrdersMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "viewPersonalInfo : To open manageInfo menu" + System.lineSeparator() +
                        "viewCart : To open cart menu" + System.lineSeparator() +
                        "viewBalance : To show balance" + System.lineSeparator() +
                        "viewDiscountCodes : To show discounts" + System.lineSeparator() +
                        "purchase : To purchase" + System.lineSeparator() +
                        "viewOrders : To open Orders menu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
