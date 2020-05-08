package Controller.Controllers;


import Controller.ControllerUnit;
import Exceptions.AcountHasNotLogedIn;
import Exceptions.AddToCartNotConfirmedException;
import Exceptions.CannotShopAsAGuestException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Product;
import Model.Models.ProductInfo;

import java.util.ArrayList;
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
        if(customer==null){
            throw new AcountHasNotLogedIn("AcountHasNotLogedIn");
        }
        customer.getCart().addToProductList(product);
    }

    public void selectSeller(String sellerUsername) {
        /// bahs...

    }

    public String attributes() {
        ///neveshtane to string baraye namayesh vizhegi dar tamame class ha
        String attributes = product.toString()+product.getProductInfo().toString();
        return attributes;
    }

    public String compare(long productId) throws ProductDoesNotExistException {
        Product product2 = Product.getProductById(productId);
        //barrasi tartibe chap kardan
        String comparing = product.getProductInfo().toString()+product2.getProductInfo().toString()+product.toString()+product2.toString();
        return comparing;
    }

    public String comments() {

        return product.getCommentList().toString()+product.getAverageScore();
    }

    public void addComment(String title, String content) {
        product.getCommentList().add(title,content);
    }

}
