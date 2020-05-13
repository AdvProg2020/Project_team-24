package Controller.Controllers;


import Controller.ControllerUnit;
import Exceptions.AccountDoesNotExistException;
import Exceptions.AcountHasNotLogedIn;
import Exceptions.IdOnlyContainsNumbersException;
import Exceptions.ThisSellerDoseNotSellChosenProduct;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Comment;
import Model.Models.Info;
import Model.Models.Product;


import java.util.List;

public class ProductController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Product product = controllerUnit.getProduct();
    private Customer customer = (Customer) controllerUnit.getAccount();
    private Seller selectedSeller;
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

    public List<Account> ListOfSellersOfChosenProsuct() {
        return product.getSellerList();
    }

    public Seller selectSellerOFProduct(String sellerIdAsString) throws AccountDoesNotExistException, ThisSellerDoseNotSellChosenProduct, IdOnlyContainsNumbersException {
        if(sellerIdAsString.matches("\\d+")) {
            Long sellerId = Long.parseLong(sellerIdAsString);
            Seller seller = (Seller) Seller.getAccountById(sellerId);
            if (!ListOfSellersOfChosenProsuct().contains(seller)) {
                throw new ThisSellerDoseNotSellChosenProduct("ThisSellerDoseNotSellChosenProduct");
            } else selectedSeller = seller;
            return selectedSeller;
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }

    public void addToCart() throws Exception {
        if (customer == null) {
            throw new AcountHasNotLogedIn("AcountHasNotLogedIn");
        }
        customer.getCart().addProductToCart(selectedSeller.getId(), product);
        //customer.getCart().setTotalPrice(product.getPrice()+);
    }


    public List<Comment> comments() {

        return product.getCommentList();
    }

    public void addComment(String title, String content) {
        ///toooye field list ezafe kon
        /* +m product.addComent(title,content);
        product.addComment();
        Comment.
        */

    }

}
