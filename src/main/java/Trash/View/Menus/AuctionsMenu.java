package Trash.View.Menus;

import B_Server.Controller.Controllers.AuctionController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import B_Server.Model.Models.Auction;
import B_Server.Model.Models.Product;
import Trash.View.Tools.Shows;
import org.jetbrains.annotations.NotNull;

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
            System.out.println(Shows.getShowAuction().apply(auction));
            try {
                List<Product> productList = auctionController.getProductOfAuction(auction.getId());
                productList.forEach(product -> Shows.getShowProduct().apply(product));
            } catch (AuctionDoesNotExistException | ProductDoesNotExistException e) {
                System.out.println(e.getMessage());
            }

        });
    }

    public void showProduct(@NotNull List<String> inputs) {
        String id =inputs.get(0);
        try {
            Product product = auctionController.showProduct(id);
            System.out.println(Shows.getShowProduct().apply(product));
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
