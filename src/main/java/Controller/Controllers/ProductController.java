package Controller.Controllers;


import Controller.ControllerUnit;
import Exceptions.AcountHasNotLogedIn;
import Model.Models.Accounts.Customer;
import Model.Models.Product;
import Model.Models.Info.ProductInfo;

import java.util.List;

public class ProductController {


    private ControllerUnit controllerUnit;

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

    Product product = controllerUnit.getProduct();
    Customer customer = (Customer) controllerUnit.getAccount();

    public List<ProductInfo> digest() {
        return (List<ProductInfo>) product.getProductInfo();
    }

    public void addToCart() throws AcountHasNotLogedIn {
        if (customer == null) {
            throw new AcountHasNotLogedIn("AcountHasNotLogedIn");
        }
        customer.getCart().addToProductList(product);
    }

    public void selectSeller(String sellerUsername) {
        /// bahs...
//        customer.getLogHistoryList().

    }

    public String comments() {

        return product.getCommentList().toString() + product.getAverageScore();
    }

    public void addComment(String title, String content) {
        // +m product.addComent(title,content);
    }

}
