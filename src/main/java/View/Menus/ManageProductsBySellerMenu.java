package View.Menus;


import Controller.Controllers.SellerController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.CategoryDoesNotExistException;
import Exceptions.FieldDoesNotExistException;
import Exceptions.ProductDoesNotExistException;

import java.util.List;

public class ManageProductsBySellerMenu extends Menu {
    private static ManageProductsBySellerMenu menu;

    public ManageProductsBySellerMenu(String name, Menu parentMenu) {
        super(name, parentMenu);
    }

    private static SellerController sellerController = SellerController.getInstance();

    @Override
    public void show() {
        System.out.println("you are in in manage products by seller menu");
    }

    public static ManageProductsBySellerMenu getInstance(String name, Menu parent) {
        if (menu == null) {
            menu = new ManageProductsBySellerMenu(name, parent);
        }
        return menu;
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
            System.out.println("product does not exist");
        }
    }

    public void edit(List<String> inputs) {
        String id = inputs.get(0);
        System.out.println("enter field name or enter finish to stop edit product");
        while (true){
            String fieldName = scanner.nextLine();
            if (fieldName.equals("finish")) break;
            System.out.println("enter new information about product");
            String newInfo=scanner.nextLine();
            try {
                sellerController.editProduct(id,fieldName,newInfo);
            } catch (AuctionDoesNotExistException e) {
                System.out.println("auction does not exist");
            } catch (FieldDoesNotExistException e) {
                System.out.println("field does not exist");
            } catch (CategoryDoesNotExistException e) {
                System.out.println("category does not exist");
            } catch (ProductDoesNotExistException e) {
                System.out.println("product does not exist");
            }
        }

    }

    public static Menu getMenu() {
        return menu;
    }

    @Override
    public void help() {
        super.help();
        System.out.println("view[productId]: to view product" + System.lineSeparator() +
                "viewBuyers [productId]:to show buyers of a product " + System.lineSeparator() +
                "edit[productId]:to edit a product");
    }
}
