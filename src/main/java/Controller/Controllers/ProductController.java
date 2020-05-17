package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Guest;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;
import Model.Models.Structs.ProductOfSeller;


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

    public List<Seller> ListOfSellersOfChosenProduct() throws AccountDoesNotExistException {
        List<Seller> list = new ArrayList<>();
        for (ProductOfSeller productOfSeller : controllerUnit.getProduct().getSellersOfProduct()) {
            long sellerId = productOfSeller.getSellerId();
            Account accountById = Account.getAccountById(sellerId);
            list.add((Seller) accountById);
        }
        return list;
    }

    public List<Comment> viewComments() throws CommentDoesNotExistException {
        List<Comment> list = new ArrayList<>();
        for (Long aLong : controllerUnit.getProduct().getCommentList()) {
            Comment commentById = Comment.getCommentById(aLong);
            list.add(commentById);
        }
        return list;
    }

    public Product getProductById(String productIdString) throws NumberFormatException, ProductDoesNotExistException {
        long id = Long.parseLong(productIdString);
        return Product.getProductById(id);
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

    public void addToCart(String sellerIdString) throws AcountHasNotLogedIn, ProductIsOutOfStockException, CanNotSaveToDataBaseException, ProductDoesNotExistException, SellerDoesNotSellThisProduct {
        long sellerId = Long.parseLong(sellerIdString);

        if (controllerUnit.getAccount() instanceof Guest) {
            throw new AcountHasNotLogedIn("Guest can't add to cart. Go to login menu ...");
        }

        Customer customer = (Customer) controllerUnit.getAccount();

        if (Product.getProductById(controllerUnit.getProduct().getId()).getProductOfSellerById(sellerId).getNumber() <= 0) {
            throw new ProductIsOutOfStockException("Product is out of stock.");
        }
        customer.getCart().addProductToCart(sellerId, controllerUnit.getProduct().getId());
    }

    public void addComment(String title, String content) throws ProductDoesNotExistException, CannotRateException, CanNotSaveToDataBaseException {
        Account account = controllerUnit.getAccount();
        Product product = controllerUnit.getProduct();
        List<Field> fields = Arrays.asList(new SingleString("Title", title), new SingleString("Content", content));
        FieldList fieldList = new FieldList(fields);
        BuyerController.getInstance().checkIfProductBoughtToRate(product.getId() + "");
        Comment comment = new Comment("ziba bood", account.getId(), product.getId(), fieldList);
        Comment.addComment(comment);
    }
}
