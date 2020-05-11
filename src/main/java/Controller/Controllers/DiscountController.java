package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Auction;
import Model.Models.Discount;
import Model.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class DiscountController {
    /****************************************************fields*******************************************************/
    private ControllerUnit controllerUnit;
    /****************************************************singleTone***************************************************/
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
    /**************************************************methods********************************************************/
    //eemale discount tooye gheymate product tooye sabade kharid
    public List<Auction> offs() {
        //+m discount list

        return Auction.getList();}
    public String showProduct(long productId) throws ProductDoesNotExistException {
        Product product = Product.getProductById(productId);
        controllerUnit.setProduct(product);
        return product.toString();}
}
