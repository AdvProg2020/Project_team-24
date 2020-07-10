package View.Menus.ByBuyers;

import Controller.Controllers.BuyerController;
import Exceptions.*;
import View.Menus.Menu;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseByBuyerMenu extends Menu {

    private static PurchaseByBuyerMenu menu;

    private static BuyerController buyerController = BuyerController.getInstance();

    public static PurchaseByBuyerMenu getInstance(String name) {
        if (menu == null) {
            menu = new PurchaseByBuyerMenu(name);
        }
        return menu;
    }

    public PurchaseByBuyerMenu(String name) {
        super(name);
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in PurchaseByBuyerMenu."));
    }

    public boolean receiveInfo() {
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "receiveInfo :[postCode] :[address]"
        );
        Matcher matcher = Pattern.compile("receiveInfo :(.+) :(.+)").matcher(scanner.nextLine().trim());

        if(!matcher.find()) {
            System.out.println("Invalid format...");
            return false;
        }

        try {
            buyerController.receiveInformation(matcher.group(1), matcher.group(2));
        } catch (PostCodeInvalidException | FieldDoesNotExistException | AddresInvalidException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Information received.");
        return true;
    }

    public boolean discountCode() {
        System.out.println("Enter discountCode if you have or enter next:");
        String discountCode = scanner.nextLine();

        if (discountCode.equals("next")) {
            System.out.println("Entered no discountCode.");
            return true;
        }

        try {
            buyerController.discountCodeUse(discountCode);
        } catch (InvalidDiscountCodeException | AccountDoesNotExistException | DiscountCodeExpiredException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("Code entered.");
        return true;
    }

    public void payment() {
        if(!(receiveInfo() && discountCode())) return;

        try {
            buyerController.buyProductsOfCart();
        } catch (NotEnoughCreditException | SellerDoesNotSellOfThisProduct | AccountDoesNotExistException | ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Purchase ended.");
    }

    @Override
    public void show() {
        System.out.println("You are going to purchase your cart.");
    }

    @Override
    public void help() {
        System.out.println("payment : To pay your cart" + System.lineSeparator() +
                "----------------------------------------------"

        );
    }
}
