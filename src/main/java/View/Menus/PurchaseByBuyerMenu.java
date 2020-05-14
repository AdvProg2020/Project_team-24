package View.Menus;

import Controller.Controllers.BuyerController;
import Exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PurchaseByBuyerMenu extends Menu {
    private static PurchaseByBuyerMenu menu;

    private static BuyerController buyerController=BuyerController.getInstance();

    public static PurchaseByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new PurchaseByBuyerMenu(name, parent);
        }
        return menu;
    }
    public PurchaseByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }
    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }
    public void receiveInfo(){
        System.out.println("Enter information in this pattern :" + System.lineSeparator() +
                "receiveInfo :[postCode] :[address]"
        );
        Matcher matcher = Pattern.compile("receiveInfo :(\\w+) :(\\w+)").matcher(scanner.nextLine().toLowerCase().trim());
        try {
            buyerController.receiveInformation(matcher.group(1),matcher.group(2));
        } catch (PostCodeInvalidexception postCodeInvalidexception) {
            System.out.println("your post code characters is not valid");
        } catch (AddresInvalidException e) {
            System.out.println("address is not valid");
        } catch (FieldDoesNotExistException | CanNotAddException e) {
            e.printStackTrace();
        }
    }
    public void discountCode(){
        System.out.println("enter discount if you have or enter next");
        String id=scanner.nextLine();
        if (!id.equals("next")){
            try {
                buyerController.discountCodeUse(id);
            } catch (InvalidDiscountCodeException e) {
                System.out.println("use valid characters for discount code");
            } catch (DiscountCodeExpiredException e) {
                System.out.println("your discount code is expired or does not exist");
            }
        }
    }
    public void payment(){
        receiveInfo();
        discountCode();
        try {
            buyerController.buyProductsOfCart();
        } catch (NotEnoughCreditException e) {
            System.out.println("you have not enough money");
        } catch (AccountDoesNotExistException e) {
            System.out.println("account does not exist");
        } catch (PurchaseFailException e) {
            System.out.println("sorry your purchase failed but we will not pay you back");
        } catch (IOException | CanNotAddException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void show() {
        System.out.println("you are going to purchase your cart");
    }
    @Override
    public void help(){
        System.out.println("payment:to pay your cart"+System.lineSeparator()+
                                     "----------------------------------------------"

        );
    }
}
