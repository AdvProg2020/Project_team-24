package View.Menus;

import Controller.ControllerUnit;
import Controller.Controllers.ManagerController;
import Exceptions.InvalidStartAndEndDateForDiscountCodeException;
import Model.Models.Account;
import Model.Models.Accounts.Guest;
import Model.Models.Accounts.Manager;
import Model.Models.Field.Fields.SingleString;
import View.MenuHandler;
import View.Menus.ByManagers.*;

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
        Manager manager = (Manager) managerController.viewPersonalInfo();
        System.out.println();
        MenuHandler.setCurrentMenu(ManageInfoMenu.getMenu());
    }

    public void openManageUsersMenu() {
        managerController.viewAllAccounts().forEach(account -> {
            System.out.println("----------------------------------------------");
            System.out.println(
                    "account id: " + account.getId() + System.lineSeparator() +
                            "account username: " + account.getUserName()
            );
            account.getPersonalInfo().getList().getFieldList().forEach(field -> {
                System.out.println(
                        field.getFieldName() + ": " + ((SingleString) field).getString()
                );
            });
            System.out.println("----------------------------------------------");
        });
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
        } catch (InvalidStartAndEndDateForDiscountCodeException e) {
            System.out.println(e.getMessage());
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

    public void logout() {
        UserAreaMenu.getMenu().setParentMenu(MainMenu.getMenu());
        MenuHandler.setCurrentMenu(UserAreaMenu.getMenu());
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
                        "openManageUsersMenu : To open users menu and view users " + System.lineSeparator() +
                        "openManageProductsMenu : To open products menu" + System.lineSeparator() +
                        "createDiscountCode : To create new discount code" + System.lineSeparator() +
                        "viewDiscountCode : To open discount code menu" + System.lineSeparator() +
                        "openManageRequestsMenu : To open request menu" + System.lineSeparator() +
                        "openManageCategoriesMenu : To open categories menu" + System.lineSeparator() +
                        "logout : To logout" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
