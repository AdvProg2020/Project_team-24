package View.Menus.BySellers;

import Controller.Controllers.SellerController;
import Exceptions.*;
import Model.Models.Auction;
import View.Menus.Menu;
import View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

    public void viewOff(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(
                    Shows.getShowAuction().apply(sellerController.viewOff(id))
            );
        } catch (AuctionDoesNotExistException e) {
            System.out.println(e.getMessage());
        }

    }

    public void editOff(@NotNull List<String> inputs) {
        String auctionId = inputs.get(0);

        System.out.println("Enter field name:");
        String fieldName = scanner.nextLine();
        System.out.println("Enter new value:");
        String newInfo = scanner.nextLine();

        System.out.println("Enter information for request: ");
        String information = scanner.nextLine();

        try {
            sellerController.editAuction(auctionId, fieldName, newInfo, information);
            System.out.println("Auction changed.");
        } catch (AuctionDoesNotExistException | FieldDoesNotExistException | InvalidInputByUserException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addOff() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "AuctionInfo :[auctionName] :[Start] :[End] :[percent] :[maxAmount]"
        );
        Matcher matcher = Pattern.compile("^AuctionInfo :(.+) :(.+) :(.+) :(.+) :(.+)$").matcher(scanner.nextLine().trim());
        if (!matcher.find()) {
            System.out.println("Incorrect format.");
            return;
        }
        try {

            Auction auction = sellerController.addOff(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));

            List<String> stringIds = new ArrayList<>();
            System.out.println("Enter product ids(or finish):");
            while (true) {
                System.out.println("Product id:");
                String strId = scanner.nextLine();
                if (strId.matches("finish")) break;
                stringIds.add(strId);
            }

            sellerController.addProductsToAuction(auction, stringIds);

            System.out.println("Enter information for request: ");
            String information = scanner.nextLine();

            sellerController.sendRequest(auction, information, "new");

            System.out.println("Auction created.");

        } catch (NumberFormatException | DateTimeParseException | ProductCantBeInMoreThanOneAuction | ProductDoesNotExistException | InvalidInputByUserException e) {
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
