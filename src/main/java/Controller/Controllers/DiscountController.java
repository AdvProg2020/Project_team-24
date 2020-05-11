package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Auction;
import Model.Models.Discount;
import Model.Models.Product;

import java.util.ArrayList;

public class DiscountController {
    private ControllerUnit controllerUnit;
    //singleTone
    private static DiscountController discountController;

    private DiscountController(ControllerUnit controllerUnit) {
        this.controllerUnit = controllerUnit;
    }

    public static  DiscountController getInstance(ControllerUnit controllerUnit) {
        if (discountController == null) {
            discountController = new  DiscountController(controllerUnit);
        }
        return  discountController;
    }
    //eemale discount tooye gheymate product tooye sabade kharid
    public ArrayList<Auction> offs() {
        //+m discount list

        return Discount.getDiscountList();}
    public String showProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        controllerUnit.setProduct(product);
        return product.toString();}
}
