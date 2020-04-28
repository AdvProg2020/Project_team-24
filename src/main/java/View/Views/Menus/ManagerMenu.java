package View.Views.Menus;

import Model.Models.Accounts.Manager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerMenu extends Menu {

    private static ManagerMenu menu;

    private ManagerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ManagerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManagerMenu(name, parent);
        }
        return menu;
    }

    public void createNewManager() {

    }

    public void createDiscount() {

        System.out.println("Enter discountCode information :" + System.lineSeparator() +
                "Enter information in this format :" + System.lineSeparator() +
                "DiscountCode :[start date] :[end data] :[percent] :[max amount] :[frequent]");

        String command = scanner.nextLine().trim();

        Matcher matcher = Pattern
                .compile("DiscountCode :(dd/mm/yyyy) :(dd/mm/yyyy) :(\\d{1,2}) :(\\d+) :(\\d+)")
                .matcher(command);

        if (!matcher.find()) {
            System.out.println("Sogol : Enter information in correct format.");
            return;
        }

        // controller manager . . .

    }

    @Override
    public void help() {
        super.help();
        System.out.println("username[username]");
        System.out.println("information  :[name] :[lastname] :[email] :[phonenumber] :[ramz]");

    }
}
