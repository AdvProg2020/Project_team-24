package B_Server.Controller.Controllers;

import B_Server.Controller.Tools.LocalClientInfo;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import B_Server.Model.Models.Auction;
import B_Server.Model.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class AuctionController extends LocalClientInfo {

    /******************************************************singleTone***************************************************/

    private static AuctionController auctionController = new AuctionController();

    public static AuctionController getInstance() {
        return auctionController;
    }

    private AuctionController() {
    }

    /****************************************************methods********************************************************/

    public List<Auction> offs() {
        return Auction.getList();
    }

    public Product showProduct(String productIdString) throws ProductDoesNotExistException, NumberFormatException {
        long productId = Long.parseLong(productIdString);
        Product.checkExistOfProductById(productId, clientInfo.get().getAuction().getProductList(), clientInfo.get().getAuction());
        Product product = Product.getProductById(productId);
        clientInfo.get().setProduct(product);
        return product;
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
}
