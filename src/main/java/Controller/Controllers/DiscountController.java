package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.IdOnlyContainsNumbersException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Auction;
import Model.Models.Product;
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
    public List<Auction> offs() {
        return Auction.getList();
    }

    public String showProduct(String productIdAsString) throws ProductDoesNotExistException, IdOnlyContainsNumbersException {
        if (productIdAsString.matches("\\d+")) {
            Long productId = Long.parseLong(productIdAsString);
            Product product = Product.getProductById(productId);
            controllerUnit.setProduct(product);
            return product.toString();
        }else throw new IdOnlyContainsNumbersException("IdOnlyContainsNumbersException");
    }
}
