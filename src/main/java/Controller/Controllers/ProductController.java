package Controller.Controllers;


import Controller.ControllerUnit;
import Exceptions.*;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Field.Fields.SingleString;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Product product = controllerUnit.getProduct();
    private Customer customer = (Customer) controllerUnit.getAccount();
    private Seller selectedSeller;
    private BuyerController buyerController = BuyerController.getInstance();
    /****************************************************singleTone***************************************************/

    private static ProductController productController;

    private ProductController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static ProductController getInstance(ControllerUnit controllerUnit) {
        if (productController == null) {
            productController = new ProductController(controllerUnit);
        }
        return productController;
    }

    /**************************************************methods********************************************************/
    public List<Info> digest() {
        return (List<Info>) product.getProductInfo();
    }

    public List<Seller> ListOfSellersOfChosenProsuct() {
        return product.getSellerList();
    }

    public Seller selectSellerOFProduct(String sellerIdAsString) throws AccountDoesNotExistException, ThisSellerDoseNotSellChosenProduct, IdOnlyContainsNumbersException {
        if (sellerIdAsString.matches("\\d+")) {
            Long sellerId = Long.parseLong(sellerIdAsString);
            Seller seller = (Seller) Seller.getAccountById(sellerId);
            if (!ListOfSellersOfChosenProsuct().contains(seller)) {
                throw new ThisSellerDoseNotSellChosenProduct("ThisSellerDoseNotSellChosenProduct");
            } else selectedSeller = seller;
            return selectedSeller;
        } else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    public void addToCart(String sellerIdAsString) throws AcountHasNotLogedIn, CanNotAddException, ProductIsOutOfStockException, CloneNotSupportedException, AccountDoesNotExistException, CanNotSaveToDataBaseException, IOException, FieldDoesNotExistException {
        Long sellerId = Long.parseLong(sellerIdAsString);
        Seller seller = (Seller) Seller.getAccountById(sellerId);

        if (customer == null) {
            throw new AcountHasNotLogedIn("AcountHasNotLogedIn");
        }
        if (product.getNumberOfThis() <= 0) {
            throw new ProductIsOutOfStockException("ProductIsOutOfStockException");
        }
        Product productClone = (Product) product.clone();
        customer.getCart().addProductToCart(seller, productClone);
    }


    public List<Comment> comments() {

        return product.getCommentList();
    }

    public void addComment(String title, String content) throws AccountDoesNotExistException, ProductDoesNotExistException, CannotRateException {
        //need to set an id for comment
        Account account = controllerUnit.getAccount();
        List<Field> fields = Arrays.asList(new SingleString("Title", title), new SingleString("Content", content));
        FieldList fieldList = new FieldList(fields);
        buyerController.checkIfProductBoughtToRate(product.getId() + "");
        Comment comment = new Comment(0, account, product, fieldList, "ziba bood");


    }

}
