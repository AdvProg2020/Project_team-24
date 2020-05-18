package View.Menus;


import Controller.Controllers.SellerController;
import Exceptions.*;

import java.util.List;
import java.util.Optional;

public class ManageProductsBySellerMenu extends Menu {

    private static ManageProductsBySellerMenu menu;

    public ManageProductsBySellerMenu(String name) {
        super(name);
    }

    private static SellerController sellerController = SellerController.getInstance();

    public static ManageProductsBySellerMenu getInstance(String name) {
        if (menu == null) {
            menu = new ManageProductsBySellerMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in ManageProductsBySellerMenu."));
    }

    public void view(List<String> inputs) {
        String id = inputs.get(0);
        try {
            sellerController.viewProduct(id);
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist");
        }
    }

    public void viewBuyers(List<String> inputs) {
        String id = inputs.get(0);
        try {
            sellerController.viewBuyers(id).forEach(buyer -> {
                System.out.println(buyer.getUserName());
            });
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void edit(List<String> inputs) {
        String id = inputs.get(0);
        System.out.println("enter field name or enter 'finish' to stop edit product");
        while (true){
            String fieldName = scanner.nextLine();
            if (fieldName.equals("finish")) break;
            System.out.println("enter new information about product");
            String newInfo=scanner.nextLine();
            try {
                sellerController.editProduct(id,fieldName,newInfo);
            } catch (AuctionDoesNotExistException | CategoryDoesNotExistException | ProductDoesNotExistException | FieldDoesNotExistException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @Override
    public void show() {
        System.out.println("you are in manage products by seller menu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("view[productId] : To view product" + System.lineSeparator() +
                "viewBuyers [productId] : To show buyers of a product " + System.lineSeparator() +
                "edit[productId] : To edit a product" + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
