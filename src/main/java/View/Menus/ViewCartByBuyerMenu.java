package View.Menus;

import Controller.Controllers.BuyerController;
import Exceptions.FieldDoesNotExistException;
import Model.Models.Product;

import java.util.List;
import java.util.Optional;

public class ViewCartByBuyerMenu extends Menu {

    private static ViewCartByBuyerMenu menu;
    private static BuyerMenu buyerMenu =BuyerMenu.getInstance();
    private static BuyerController buyerController=BuyerController.getInstance();
    public ViewCartByBuyerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    public static ViewCartByBuyerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ViewCartByBuyerMenu(name, parent);
        }
        return menu;
    }

    public void showProducts() {
        buyerController.showProducts().forEach(product -> {
            //System.out.println(product.getName);
            try {
                System.out.println(product.getPrice()
                );
            } catch (FieldDoesNotExistException e) {
                e.printStackTrace();
            }
        });
    }

    public void viewProduct(List<String> inputs) {
       String id=inputs.get(0);

    }

    public void increase(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        //yasi
    }

    public void decrease(List<String> inputs) {
        long id = Long.parseLong(inputs.get(0));
        //yasi
    }

    public void showTotalPrice() {
        // yac
    }

    public void purchase() {
        // yac
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow();
    }

    @Override
    public void show() {
        System.out.println("You're in ViewCartByBuyerMenu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println(
                "showProducts : To show product" + System.lineSeparator() +
                        "viewCart [productId] : To view cart" + System.lineSeparator() +
                        "increase [productId] : To increase number of that product" + System.lineSeparator() +
                        "decrease [productId] : To decrease number of that product" + System.lineSeparator() +
                        "showTotalPrice : To show total price" + System.lineSeparator() +
                        "purchase : To purchase" + System.lineSeparator() +
                        "----------------------------------------------"
        );
    }
}
