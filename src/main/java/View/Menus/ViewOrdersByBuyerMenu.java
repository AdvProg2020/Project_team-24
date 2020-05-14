package View.Menus;

import Controller.Controllers.BuyerController;
import Exceptions.CannotRateException;
import Exceptions.HaveNotBoughtThisProductException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;

import java.util.List;

public class ViewOrdersByBuyerMenu extends Menu {
    private static ViewOrdersByBuyerMenu menu;
    private static BuyerController buyerController = BuyerController.getInstance();

    public ViewOrdersByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    @Override
    public void show() {
        System.out.println("view orders by buyer menu");
    }

    public static ViewOrdersByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewOrdersByBuyerMenu(name, parent);
        }
        return menu;
    }

    public void showOrder(List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(buyerController.showOrder(id));
        } catch (HaveNotBoughtThisProductException e) {
            System.out.println("have not bought these products");
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

    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("showOrder[order id]:to show orders from you" + System.lineSeparator() +
                "rate [product id][1-5]:to rate a product from 1 to 5");

    }
}
