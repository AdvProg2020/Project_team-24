package View.Menus;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Controller.Controllers.ManagerController;
import Exceptions.DiscountCodeExpiredException;
import Exceptions.LogHistoryDoesNotExistException;
import Model.Models.Accounts.Guest;
import View.MenuHandler;
import View.Tools.Shows;

import java.util.Optional;

public class BuyerMenu extends Menu {

    private static BuyerController buyerController = BuyerController.getInstance();

    private static BuyerMenu menu;

    private BuyerMenu(String name) {
        super(name);
    }

    public static BuyerMenu getInstance(String name) {
        if (menu == null) {
            menu = new BuyerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in buyerMenu."));
    }

    public void openMainMenu() {
        MenuHandler.setCurrentMenu(MainMenu.getMenu());
    }

    public void viewPersonalInfo() {
        System.out.println(
                Shows.getShowInfo().apply(buyerController.viewPersonalInfo())
        );
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void viewCart() {
        MenuHandler.setCurrentMenu(ViewCartByBuyerMenu.getMenu());
    }

    public void viewOrders() {
        try {
            System.out.println("Orders:");
            buyerController.viewOrders().forEach(logHistory ->
                    System.out.println(Shows.getShowLogHistory().apply(logHistory))
            );

        } catch (LogHistoryDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        MenuHandler.setCurrentMenu(ViewOrdersByBuyerMenu.getMenu());
    }

    public void viewBalance() {
        System.out.println(buyerController.viewBalance());
    }

    public void viewDiscountCodes() {
        try {
            buyerController.viewDiscountCodes().forEach(discountCode -> {
                System.out.println(
                        discountCode.getDiscountCode() + " : " + discountCode.getDiscount().getPercent() + " " + discountCode.getDiscount().getAmount()
                );
            });
        } catch (DiscountCodeExpiredException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logout() {
        MainMenu.getMenu().setParentMenu(UserAreaMenu.getMenu());
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println(
                "You're in BuyerMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.MainMenu" + System.lineSeparator() +
                        "2.ManageInfo" + System.lineSeparator() +
                        "3.CartMenu" + System.lineSeparator() +
                        "4.OrdersMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openMainMenu:to open main menu" + System.lineSeparator() +
                        "viewPersonalInfo : To open manageInfo menu" + System.lineSeparator() +
                        "viewCart : To open cart menu" + System.lineSeparator() +
                        "viewBalance : To show balance" + System.lineSeparator() +
                        "viewDiscountCodes : To show discounts" + System.lineSeparator() +
                        "purchase : To purchase" + System.lineSeparator() +
                        "viewOrders : To open Orders menu" + System.lineSeparator() +
                        "logout : To logout" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
