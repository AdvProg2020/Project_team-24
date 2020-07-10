package Trash.View.Menus.ByBuyers;

import B_Server.Controller.Controllers.BuyerController;
import Exceptions.*;
import Trash.View.MenuHandler;
import Trash.View.Menus.Menu;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class ViewCartByBuyerMenu extends Menu {

    private static ViewCartByBuyerMenu menu;

    private static BuyerController buyerController = BuyerController.getInstance();

    public ViewCartByBuyerMenu(String name) {
        super(name);
    }

    public static ViewCartByBuyerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ViewCartByBuyerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ViewCartByBuyerMenu."));
    }

    public void showProducts() {
        try {
            buyerController.showProducts().forEach(product -> {
                System.out.println(Shows.getShowProduct().apply(product));
            });
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewProduct(@NotNull List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(
                    Shows.getShowProduct().apply(buyerController.viewProductInCart(id))
            );
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void increase(@NotNull List<String> inputs) {
        String idProduct = inputs.get(0);
        System.out.println("Please enter seller id of this product: ");
        String idSeller = scanner.nextLine();

        try {
            buyerController.increase(idProduct, idSeller);
        } catch (ProductDoesNotExistException | SellerDoesNotSellOfThisProduct | ProductIsOutOfStockException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Ok.");
    }

    public void decrease(@NotNull List<String> inputs) {
        String idProduct = inputs.get(0);
        System.out.println("Please enter seller id of this product: ");
        String idSeller = scanner.nextLine();
        try {
            buyerController.decrease(idProduct, idSeller);
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Ok.");
    }

    public void showTotalPrice() {
        try {
            System.out.println("TotalPrice: " + buyerController.showTotalPrice());
        } catch (ProductDoesNotExistException | SellerDoesNotSellOfThisProduct e) {
            System.out.println(e.getMessage());
        }
    }

    public void purchase() {
        MenuHandler.setCurrentMenu(PurchaseByBuyerMenu.getMenu());
    }

    @Override
    public void show() {
        System.out.println("You're in ViewCartByBuyerMenu.");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "show products : To show product" + System.lineSeparator() +
                        "viewProduct [productId] : To view cart" + System.lineSeparator() +
                        "increase [productId] : To increase number of that product" + System.lineSeparator() +
                        "decrease [productId] : To decrease number of that product" + System.lineSeparator() +
                        "show total price : To show total price" + System.lineSeparator() +
                        "purchase : To purchase" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
