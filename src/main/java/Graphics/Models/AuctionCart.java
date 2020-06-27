package Graphics.Models;

import Controller.Controllers.AuctionController;
import Exceptions.AuctionDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Graphics.MainMenu;
import Graphics.Menus.ProductsMenu;
import Model.Models.Auction;
import Model.Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class AuctionCart implements Initializable {

    private static List<Image> images = Arrays.asList(
        new Image("src/main/resources/DataBase/AuctionsImage-src/auctionImage-1.jpg"),
        new Image("src/main/resources/DataBase/AuctionsImage-src/auctionImage-2.jpg"),
        new Image("src/main/resources/DataBase/AuctionsImage-src/auctionImage-3.jpg")
    );
    private static AuctionController auctionController = AuctionController.getInstance();
    private static List<Auction> auctionList;
    private Auction auction;

    @FXML
    private ImageView auction_image;
    @FXML
    private Label auction_name;
    @FXML
    private Label auction_per;

    public static void setAuctionList(List<Auction> auctionList) {
        AuctionCart.auctionList = auctionList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        auction = auctionList.get(0);
        auctionList.remove(0);
        init(auction);
    }

    public void gotoProductsMenu() {
        ProductsMenu.setMode(ProductsMenu.Modes.AuctionMode);
        List<Product> productOfAuction = null;
        try {
            productOfAuction = auctionController.getProductOfAuction(auction.getId());
        } catch (AuctionDoesNotExistException | ProductDoesNotExistException e) {
            e.printStackTrace();
        }
        ProductsMenu.setList(productOfAuction);
        ProductCart.setProductList(productOfAuction);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void init(@NotNull Auction auction) {
        auction_image.setImage(images.get(new Random().nextInt(images.size())));
        auction_name.setText(auction.getName());
        auction_per.setText(auction.getDiscount().getPercent() + "%");
    }
}
