package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.IdOnlyContainsNumbersException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Auction;
import Model.Models.Product;

import java.util.List;

public class AuctionController {

    /******************************************************fields*******************************************************/

    private ControllerUnit controllerUnit = ControllerUnit.getInstance();

    private static AuctionController auctionController = new AuctionController();

    /******************************************************singleTone***************************************************/

    public static AuctionController getInstance() {
        return auctionController;
    }

    private AuctionController() {
    }

    /****************************************************methods********************************************************/

    public List<Auction> offs() {
        return Auction.getList();
    }

    public String showProduct(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product product = Auction);
        controllerUnit.setProduct(product);
        return product.toString();
    }
}
