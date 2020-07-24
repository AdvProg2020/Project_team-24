package B_Server.Controller.Controllers;

import B_Server.Controller.Controllers.AccountControllers.BuyerController;
import B_Server.Controller.Tools.LocalClientInfo;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Comment;
import Structs.FieldAndFieldList.FieldList;
import B_Server.Model.Models.Product;
import Exceptions.*;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Guest;
import B_Server.Model.Models.Accounts.Seller;
import Structs.FieldAndFieldList.Field;

import Structs.ProductVsSeller.ProductOfSeller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductController extends LocalClientInfo {

    /*****************************************************singleTone****************************************************/

    private static ProductController productController = new ProductController();

    public static ProductController getInstance() {
        return productController;
    }

    private ProductController() {
    }

    /****************************************************methods********************************************************/

    public Product digest() {
        return clientInfo.get().getProduct();
    }

    public List<Seller> ListOfSellersOfChosenProduct() throws AccountDoesNotExistException {
        List<Seller> list = new ArrayList<>();
        for (ProductOfSeller productOfSeller : clientInfo.get().getProduct().getSellersOfProduct()) {
            long sellerId = productOfSeller.getSellerId();
            Account accountById = Account.getAccountById(sellerId);
            list.add((Seller) accountById);
        }
        return list;
    }

    public List<Comment> viewComments() throws CommentDoesNotExistException {
        List<Comment> list = new ArrayList<>();
        for (Long aLong : clientInfo.get().getProduct().getCommentList()) {
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

    public void addToCart(String sellerIdString) throws AccountHasNotLogin, ProductIsOutOfStockException, ProductDoesNotExistException, SellerDoesNotSellOfThisProduct {
        long sellerId = Long.parseLong(sellerIdString);

        if (clientInfo.get().getAccount() instanceof Guest) {
            throw new AccountHasNotLogin("Guest can't add to cart. Go to login menu ...");
        }

        Customer customer = (Customer) clientInfo.get().getAccount();

        ProductOfSeller productOfSellerById = Product.getProductById(clientInfo.get().getProduct().getId()).getProductOfSellerById(sellerId);
        if (productOfSellerById.getNumber() <= 0) {
            throw new ProductIsOutOfStockException("Product is out of stock.");
        }
        productOfSellerById.setNumber(productOfSellerById.getNumber() - 1);
        customer.getCart().addProductToCart(sellerId, clientInfo.get().getProduct().getId());
    }

    public void addComment(String title, String content) throws ProductDoesNotExistException, CannotRateException {
        Account account = clientInfo.get().getAccount();
        Product product = clientInfo.get().getProduct();
        List<Field> fields = Arrays.asList(new Field("Title", title), new Field("Content", content));
        FieldList fieldList = new FieldList(fields);
        BuyerController.getInstance().checkIfProductBoughtToRate(product.getId());
        Comment comment = new Comment("ziba bood", account.getId(), product.getId(), fieldList);
        Comment.addComment(comment);
        product.addComment(comment.getId());
    }
}
