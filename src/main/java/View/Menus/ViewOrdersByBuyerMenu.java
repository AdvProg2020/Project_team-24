package View.Menus;

import Controller.Controllers.BuyerController;
import Exceptions.CannotRateException;
import Exceptions.HaveNotBoughtThisProductException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;

import java.util.List;
import java.util.Optional;

public class ViewOrdersByBuyerMenu extends Menu {

    private static ViewOrdersByBuyerMenu menu;
    private static BuyerController buyerController = BuyerController.getInstance();

    public ViewOrdersByBuyerMenu(String name) {
        super(name);
    }

    public static ViewOrdersByBuyerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ViewOrdersByBuyerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ViewOrdersByBuyerMenu."));
    }

    public void showOrder(List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(buyerController.showOrder(id));
        } catch (LogHistoryDoesNotExistException e) {
            System.out.println("log history does not exist");
        }
    }

    public void rate(List<String> inputs) {
        String id = inputs.get(0);
        String number = inputs.get(1);
        try {
            buyerController.rate(id, number);
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist ");
        } catch (CannotRateException e) {
            System.out.println("sorry you can not rate");
        }
    }

    @Override
    public void show() {
        System.out.println("view orders by buyer menu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("showOrder[order id]:to show orders from you" + System.lineSeparator() +
                "rate [product id][1-5]:to rate a product from 1 to 5" + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
