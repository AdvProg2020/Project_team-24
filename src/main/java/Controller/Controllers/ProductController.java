package Controller.Controllers;


import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Guest;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Tools.AddingNew;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductController {

    /******************************************************fields*******************************************************/

    private static ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static ProductController productController = new ProductController();

    /*****************************************************singleTone****************************************************/

    private ProductController() {
    }

    public static ProductController getInstance() {
        return productController;
    }

    /****************************************************methods********************************************************/

    public Product digest() {
        return controllerUnit.getProduct();
    }

    public List<Seller> ListOfSellersOfChosenProduct() {
        return controllerUnit.getProduct().getSellerList();
    }

    public List<Comment> viewComments() {
        return controllerUnit.getProduct().getCommentList();
    }

//    public Seller selectSellerOfProduct(String sellerIdString) throws AccountDoesNotExistException, ThisSellerDoseNotSellChosenProduct, NumberFormatException {
//        long sellerId = Long.parseLong(sellerIdString);
//        Seller seller = (Seller) Seller.getAccountById(sellerId);
//
//        if (!ListOfSellersOfChosenProduct().contains(seller)) {
//            throw new ThisSellerDoseNotSellChosenProduct("ThisSellerDoseNotSellChosenProduct");
//        }
//        return seller;
//    }

    public void addToCart(String sellerIdString) throws AcountHasNotLogedIn, ProductIsOutOfStockException, CloneNotSupportedException, AccountDoesNotExistException, CanNotSaveToDataBaseException, IOException {
        long sellerId = Long.parseLong(sellerIdString);
        Seller seller = (Seller) Seller.getAccountById(sellerId);

        if (controllerUnit.getAccount() instanceof Guest) {
            throw new AcountHasNotLogedIn("Guest can't add to cart. Go to login menu ...");
        }

        Customer customer = (Customer) controllerUnit.getAccount();

        if (controllerUnit.getProduct().getNumberOfThis() <= 0) {
            throw new ProductIsOutOfStockException("ProductIsOutOfStockException");
        }
        Product productClone = (Product) controllerUnit.getProduct().clone();
        customer.getCart().addProductToCart(seller, productClone);
    }

    public void addComment(String title, String content) throws ProductDoesNotExistException, CannotRateException, CanNotAddException, IOException, CanNotSaveToDataBaseException {
        Account account = controllerUnit.getAccount();
        Product product = controllerUnit.getProduct();
        List<Field> fields = Arrays.asList(new SingleString("Title", title), new SingleString("Content", content));
        FieldList fieldList = new FieldList(fields);
        BuyerController.getInstance().checkIfProductBoughtToRate(product.getId() + "");
        Comment comment = new Comment(AddingNew.getRegisteringId().apply(Comment.getList()), account, product, fieldList, "ziba bood");
        Comment.addComment(comment);
    }
}
