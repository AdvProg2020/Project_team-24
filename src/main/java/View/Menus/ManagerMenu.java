package View.Menus;

import Controller.ControllerUnit;
import Controller.Controllers.ManagerController;
import Exceptions.AccountDoesNotExistException;
import Model.Models.Account;
import View.MenuHandler;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {

    private static ManagerMenu menu;
    private static ManagerController managerController = ManagerController.getInstance();

    private ManagerMenu(String name) {
        super(name);
    }

    public static ManagerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManagerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManagerMenu."));
    }

    public void openMainMenu() {
        MenuHandler.setCurrentMenu(MainMenu.getMenu());
    }

    public void viewPersonalInfo() {
        System.out.println(managerController.viewPersonalInfo());
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void openManageUsersMenu() {
        System.out.println(managerController.viewAllAccounts());
        MenuHandler.setCurrentMenu(ManageUsersByManagerMenu.getMenu());
    }

    public void openManageProductsMenu() {
        MenuHandler.setCurrentMenu(ManageProductsByManagerMenu.getMenu());
    }

    public void createDiscountCode() {
        System.out.println("Enter discountCode information :" + System.lineSeparator() +
                "Enter information in this format :" + System.lineSeparator() +
                "DiscountCode :[start date] :[end data] :[percent] :[max amount] :[frequent]");

        Matcher matcher = Pattern
                .compile("DiscountCode :(dd/mm/yyyy) :(dd/mm/yyyy) :(\\d{1,2}(\\.\\d+)?) :(\\d+(\\.\\d+)?) :(\\d+)")
                .matcher(scanner.nextLine().trim());


        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }
        try {
            managerController.creatDiscountCode(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDiscountCode() {
        managerController.viewDiscountCodes();
        MenuHandler.setCurrentMenu(ViewDiscountCodesByManagerMenu.getMenu());
    }

    public void openManageRequestsMenu() {
        managerController.showAllRequests();
        MenuHandler.setCurrentMenu(ManageRequestsByManagerMenu.getMenu());
    }

    public void openManageCategoriesMenu() {
        managerController.showAllCategories();
        MenuHandler.setCurrentMenu(ManageCategoriesByManagerMenu.getMenu());
    }


    @Override
    public void show() {
        System.out.println(
                "You are in managerMenu" + System.lineSeparator() +
                        "-------------------SubMenus-------------------" + System.lineSeparator() +
                        "1.MainMenu" + System.lineSeparator() +
                        "2.ManageUsersMenu" + System.lineSeparator() +
                        "3.ManageProductsMenu" + System.lineSeparator() +
                        "4.ManageRequestsMenu" + System.lineSeparator() +
                        "5.ManageCategoriesMenu" + System.lineSeparator() +
                        "6.ManageIfo" + System.lineSeparator() +
                        "7.ViewDiscountCodesByManagerMenu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "openMainMenu:to open main menu" + System.lineSeparator() +
                        "viewPersonalInfo : To open manageInfo menu" + System.lineSeparator() +
                        "openManageUsersMenu : To open users menu" + System.lineSeparator() +
                        "openManageProductsMenu : To open products menu" + System.lineSeparator() +
                        "createDiscountCode : To create new discount code" + System.lineSeparator() +
                        "viewDiscountCode : To open discount code menu" + System.lineSeparator() +
                        "openManageRequestsMenu : To open request menu" + System.lineSeparator() +
                        "openManageCategoriesMenu : To open categories menu" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
