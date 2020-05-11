package Controller.Controllers;


import Controller.ControllerUnit;
import Exceptions.AcountHasNotLogedIn;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Info;
import Model.Models.Product;
import Model.Models.Info.ProductInfo;

import java.util.List;

public class ProductController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    private Product product = controllerUnit.getProduct();
    private Customer customer = (Customer) controllerUnit.getAccount();
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

    public void addToCart() throws AcountHasNotLogedIn {
        if (customer == null) {
            throw new AcountHasNotLogedIn("AcountHasNotLogedIn");
        }
        Seller seller = selectSeller();
        customer.getCart().addProductToCart(seller.getId(),product);
    }

    public void selectSeller(String sellerUsername) {

    }

    public String comments() {

        return product.getCommentList().toString() + product.getAverageScore();
    }

    public void addComment(String title, String content) {
        // +m product.addComent(title,content);
    }

}
