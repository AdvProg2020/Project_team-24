package View.Menus;

import Controller.Controllers.BuyerController;
import Exceptions.*;
import View.MenuHandler;

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

//    public void showProducts() {
//       List<Product>productList=new ArrayList<>();
//       for(Long aLong :productId){
//           Product productById=null;
//           try {
//               productById=Product.getProductById(aLong);
//           } catch (ProductDoesNotExistException e) {
//               e.printStackTrace();
//           }
//           productList.add(productById);
//       }
//       for(int i=0;i<productList.size();i++){
//           Product product=productList.get(i);
//           long sellerId=sellerId.get(i);
//           System.out.println(product.getPrice(sellerId));
//       }
//    }

    public void viewProduct(List<String> inputs) {
        String id = inputs.get(0);
        try {
            System.out.println(buyerController.viewProductInCart(id));
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void increase(List<String> inputs) {
        String idProduct = inputs.get(0);
        System.out.println("please enter seller id of this product");
        String idSeller = scanner.nextLine();

        try {
            buyerController.increase(idProduct, idSeller);
        } catch (ProductDoesNotExistException | SellerDoesNotSellOfThisProduct | ProductIsOutOfStockException e) {
            System.out.println(e.getMessage());
        }

    }

    public void decrease(List<String> inputs) {
        String idProduct = inputs.get(0);
        System.out.println("please enter seller id of this product");
        String idSeller = scanner.nextLine();
        try {
            buyerController.decrease(idProduct, idSeller);
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist");
        }
    }

    public void showTotalPrice() {
        try {
            System.out.println(buyerController.showTotalPrice());
        } catch (ProductDoesNotExistException e) {
            System.out.println("product does not exist ");
        } catch (SellerDoesNotSellOfThisProduct sellerDoesNotSellOfThisProduct) {
            System.out.println("seller not found.");
        }
    }

    public void purchase() {
        MenuHandler.setCurrentMenu(PurchaseByBuyerMenu.getMenu());
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
