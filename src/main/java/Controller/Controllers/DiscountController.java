package Controller.Controllers;

import Controller.ControllerUnit;
import Model.Models.Discount;

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
    public ArrayList<Discount> offs() {return null;}
    public void showProduct(long productId){}
}
