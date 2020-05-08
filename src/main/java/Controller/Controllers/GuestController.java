package Controller.Controllers;

import Controller.ControllerUnit;

import Exceptions.NotEnoughCreditException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.*;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestController {

    private ControllerUnit controllerUnit;
    //singleTone
    private static GuestController guestController;

    private GuestController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static GuestController getInstance(ControllerUnit controllerUnit) {
        if (guestController == null) {
            guestController = new GuestController(controllerUnit);
        }
        return guestController;
    }

    Guest guest = (Guest) controllerUnit.getAccount();

    public Cart viewCart() {
        return guest.getCart();
    }

    public List<Product> showProducts() {
        return guest.getCart().getProductList();
    }

    public Product viewProductInCart(long productId) throws ProductDoesNotExistException {
        //toozihat gofte vorod be sfhe mahsool...yani menu mikhad...man String kol etelat ro bedam ya chi?
        ///+m tostring koore baraye hame chi
        return Product.getProductById(productId);
    }

    public void increase(long productId) throws CloneNotSupportedException {
        Product productClone = (Product) viewCart().getProductById(productId).clone();
        viewCart().addToProductList(productClone);
       guest.getCart().addToProductList(productClone);
    }

    public void decrease(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        viewCart().removeFromProductList(product);
    }

    public double showTotalPrice() {
        return viewCart().getTotalPrice();
    }

}