package Graphics.Models;

import Model.Models.Auction;
import Model.Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCart implements Initializable {

    @FXML
    private ImageView productImage;
    @FXML
    private Text price_ftx;
    @FXML
    private Text price_ltx;
    @FXML
    private Label discount;

    private Product product;
    private int sellerIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //?
    }

    public void gotoProduct() {
        //?
    }

    public void setProduct(@NotNull Product product) {
        this.productImage.setImage(product.getProductImage());
        this.price_ftx.setText(product.getSellersOfProduct().get(sellerIndex).getPrice() + "");
        if (product.getAuction() != null) newPrice(product.getAuction());
        this.product = product;
    }

    public void nextSeller() {

        if (sellerIndex + 1 < product.getSellersOfProduct().size())
            this.price_ftx.setText(
                    product.getSellersOfProduct()
                            .get(++sellerIndex).getPrice() + ""
            );

        if (product.getAuction() != null) newPrice(product.getAuction());
    }

    public void prevSeller() {

        if (sellerIndex > 0)
            this.price_ftx.setText(
                    product.getSellersOfProduct()
                            .get(--sellerIndex).getPrice() + ""
            );

        if (product.getAuction() != null) newPrice(product.getAuction());
    }

    private void newPrice(@NotNull Auction auction) {

        this.discount.setVisible(true);
        this.price_ltx.setVisible(true);

        double price = product.getSellersOfProduct().get(sellerIndex).getPrice();
        double discountAmount = auction.getAuctionDiscount(price);

        this.price_ltx.setText(price - discountAmount + "");
        this.price_ftx.setStrikethrough(true);
    }
}
