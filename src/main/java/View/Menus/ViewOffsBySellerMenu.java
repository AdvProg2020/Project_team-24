package View.Menus;

import Controller.Controllers.SellerController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import Exceptions.ProductDoesNotExistException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewOffsBySellerMenu extends Menu {

    private static ViewOffsBySellerMenu menu;

    public ViewOffsBySellerMenu(String name) {
        super(name);
    }

    private static SellerController sellerController = SellerController.getInstance();

    public static ViewOffsBySellerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ViewOffsBySellerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ViewOffsBySellerMenu."));
    }

    public void viewOff(List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(sellerController.viewOff(id));
        } catch (AuctionDoesNotExistException e) {
            System.out.println(e.getMessage());
        }

    }

    public void editOff(List<String> inputs) {
        String id = inputs.get(0);
        System.out.println("enter field name or enter 'finish' to stop edit product");
        while (true) {
            String fieldName = scanner.nextLine();
            if (fieldName.equals("finish")) break;
            System.out.println("enter new information about product");
            String newInfo = scanner.nextLine();
            try {
                sellerController.editAuction(id, fieldName, newInfo);
            } catch (AuctionDoesNotExistException | FieldDoesNotExistException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void addOff() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "AuctionInfo :[auctionName] :[Start] :[End] :[percent] :[maxAmount]"
        );
        Matcher matcher = Pattern.compile("AuctionInf :(\\w+) :(\\w+) :(\\w+) :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format");
        }
        try {
            sellerController.addOff(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.println("You're in ViewOffsBySellerMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "viewOff [OffId] : To show off by id" + System.lineSeparator() +
                        "editOff [OffId] : To edit an off" + System.lineSeparator() +
                        "addOff : To add an off" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
