package Trash.View.Menus.ByBuyers;

import B_Server.Controller.Controllers.BuyerController;
import Exceptions.CannotRateException;
import Exceptions.LogHistoryDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Trash.View.Menus.Menu;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

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

    public void showOrder(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(
                    Shows.getShowLogHistory().apply(buyerController.showOrder(id))
            );
        } catch (LogHistoryDoesNotExistException e) {
            System.out.println("log history does not exist");
        }
        System.out.println("Ok.");
    }

    public void rate(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        String number = inputs.get(1);
        try {
            buyerController.rate(id, number);
        } catch (ProductDoesNotExistException | CannotRateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("OK.");
    }

    @Override
    public void show() {
        System.out.println("view orders by buyer menu.");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("showOrder [order id] :To show orders from you" + System.lineSeparator() +
                "rate [product id] [1-5] :To rate a product from 1 to 5" + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
