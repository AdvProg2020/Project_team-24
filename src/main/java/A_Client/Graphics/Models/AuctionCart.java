package A_Client.Graphics.Models;

import A_Client.Client.SendAndReceive.SendAndReceive;
import Structs.MiniAuction;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import Structs.MiniProduct;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URL;
import java.util.*;

public class AuctionCart implements Initializable {

    private static final List<Image> images = Arrays.asList(
        new Image(new File("src/main/resources/DataBase/AuctionsImage-src/auctionImage-1.jpg").toURI().toString()),
        new Image(new File("src/main/resources/DataBase/AuctionsImage-src/auctionImage-1.jpg").toURI().toString()),
        new Image(new File("src/main/resources/DataBase/AuctionsImage-src/auctionImage-1.jpg").toURI().toString())
    );
    private static List<MiniAuction> auctionList = new ArrayList<>();
    private MiniAuction auction;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView auction_image;
    @FXML
    private Label auction_name;
    @FXML
    private Label auction_per;

    public static void setAuctionList(List<MiniAuction> auctionList) {
        AuctionCart.auctionList = auctionList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (auctionList.isEmpty()) {
            mainPane.setVisible(false);
            mainPane.setDisable(true);
            return;
        }
        auction = auctionList.get(0);
        auctionList.remove(0);
        init(auction);
    }

    public void gotoProductsMenu() {
        ProductsMenu.setMode(ProductsMenu.Modes.AuctionMode);
        List<MiniProduct> productOfAuction = SendAndReceive.getAllProductOfAuction(auction.getAuctionId());
        ProductsMenu.setList(productOfAuction);
        ProductCart.setProductList(productOfAuction);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void init(@NotNull MiniAuction auction) {
        auction_image.setImage(images.get(new Random().nextInt(images.size())));
        auction_name.setText(auction.getAuctionName());
        auction_per.setText(auction.getAuctionPercent() + "%");
    }
}
