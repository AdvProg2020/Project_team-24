package Controller.Controllers;

import Controller.ControllerUnit;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Auction;
import Model.Models.Product;

import java.util.ArrayList;
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

    public List<Product> getProductOfAuction(long auctionId) throws AuctionDoesNotExistException, ProductDoesNotExistException {
        Auction auction = Auction.getAuctionById(auctionId);
        List<Product> list = new ArrayList<>();
        for (Long aLong : auction.getProductList()) {
            Product productById = Product.getProductById(aLong);
            list.add(productById);
        }
        return list;
    }

    public Product showProduct(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product.checkExistOfProductById(productId, controllerUnit.getAuction().getProductList(), controllerUnit.getAuction());
        Product product = Product.getProductById(productId);
        controllerUnit.setProduct(product);
        return product;
    }
}
