package View.Menus;

import Controller.Controllers.AccountController;
import Controller.Controllers.AuctionController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Model.Models.Auction;
import Model.Models.Product;

import java.util.List;
import java.util.Optional;

public class AuctionsMenu extends Menu {

    private static AuctionController auctionController = AuctionController.getInstance();

    private static AuctionsMenu menu;

    private AuctionsMenu(String name) {
        super(name);
    }

    public static AuctionsMenu getInstance(String name) {
        if (menu == null) {
            menu = new AuctionsMenu(name);
        }
        return menu;
    }

    public static Menu getMenu() {
        return Optional.ofNullable(menu).orElseThrow(() -> new NullPointerException("getting null in auctionMenu."));
    }

    public void offs() {
        List<Auction> auctionList = auctionController.offs();
        auctionList.forEach(auction -> {

            System.out.println(
                    "AuctionName: " + auction.getAuctionName() + System.lineSeparator() +
                            "End: " + auction.getEnd() + System.lineSeparator() +
                            "Discount percent: " + auction.getDiscount().getPercent() + System.lineSeparator() +
                            "Discount limit: " + auction.getDiscount().getAmount()
            );

            try {

                List<Product> productList = auctionController.getProductOfAuction(auction.getId());
                productList.forEach(product -> {

                    System.out.println("ProductName: " + product.getProductName());

                    product.getSellersOfProduct().forEach(productOfSeller -> {

                        System.out.println(
                                "Old price: " + productOfSeller.getPrice() + System.lineSeparator() +
                                        "Price with discount: " + (productOfSeller.getPrice() - auction.getAuctionDiscount(productOfSeller.getPrice()))
                        );

                    });

                });

            } catch (AuctionDoesNotExistException | ProductDoesNotExistException e) {
                System.out.println(e.getMessage());
            }

        });
    }

    public void showProduct(List<String> inputs) {
        String id =inputs.get(0);
        try {
            auctionController.showProduct(id);
        } catch (ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.println("you are in auction menu");
    }

    @Override
    public void help() {
        super.help();
        System.out.println("offs : To show all the Auctions." + System.lineSeparator() +
                "showProduct [productId] : To product by the id." + System.lineSeparator() +
                "----------------------------------------------"
        );
    }
}
