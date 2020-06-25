package Graphics.Models;

import Controller.ControllerUnit;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductMediaNotFoundException;
import Graphics.MainMenu;
import Model.Models.Auction;
import Model.Models.Product;
import Model.Models.Structs.ProductMedia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductCart implements Initializable {

    private static List<Product> productList = new ArrayList<>();
    private Product product;
    private int sellerIndex;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;
    @FXML
    private ImageView discountImage;
    @FXML
    private ImageView productImage;
    @FXML
    private Text price_ftx;
    @FXML
    private Text price_ltx;
    @FXML
    private Label discount;
    @FXML
    private Text product_name;
    @FXML
    private Pane mainPane;

    public static void setProductList(List<Product> productList) {
        ProductCart.productList = productList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (productList.isEmpty()) {
            mainPane.setVisible(false);
            return;
        }
        this.product = productList.get(0);
        productList.remove(0);
        setProductCartFields();
    }

    public void gotoProduct() {
        setProductList(getSimilar());
        MainMenu.change(new Graphics.Product().sceneBuilder());
        Product first_compare = Graphics.Product.getFirst_Compare();
        if (first_compare == null) {
            ControllerUnit.getInstance().setProduct(product);
        } else ControllerUnit.getInstance().setProduct(first_compare);
        setProductList(new ArrayList<>());
    }

    private List<Product> getSimilar() {
        return product.getCategory().getProductList().stream()
                .map(id -> {
                    try {
                        return Product.getProductById(id);
                    } catch (ProductDoesNotExistException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
    }

    private void setProductCartFields() {
        if (product == null) return;
        try {
            Image image = ProductMedia.getProductMediaById(product.getId()).getImage();
            this.productImage.setImage(image);
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
        this.product_name.setText(product.getName());
        this.price_ftx.setText(product.getSellersOfProduct().get(sellerIndex).getPrice() + "");
        if(product.getAuction() == null) return;
        newPrice(product.getAuction());
    }

    public void nextSeller() {

        if (sellerIndex + 1 < product.getSellersOfProduct().size())
            this.price_ftx.setText(
                    product.getSellersOfProduct()
                            .get(++sellerIndex).getPrice() + ""
            );

        if (product.getAuction() != null) newPrice(product.getAuction());

        checkButtons();
    }

    public void prevSeller() {

        if (sellerIndex > 0)
            this.price_ftx.setText(
                    product.getSellersOfProduct()
                            .get(--sellerIndex).getPrice() + ""
            );

        if (product.getAuction() != null) newPrice(product.getAuction());

        checkButtons();
    }

    private void checkButtons() {

        if (sellerIndex == product.getSellersOfProduct().size() - 1) {
            nextButton.setDisable(true);
        } else nextButton.setDisable(false);

        if (sellerIndex == 0) {
            prevButton.setDisable(true);
        } else prevButton.setDisable(false);
    }

    private void newPrice(@NotNull Auction auction) {

        this.discount.setVisible(true);
        this.price_ltx.setVisible(true);
        this.discountImage.setVisible(true);

        double price = product.getSellersOfProduct().get(sellerIndex).getPrice();
        double discountAmount = auction.getAuctionDiscount(price);

        this.price_ltx.setText(price - discountAmount + "");
        this.price_ftx.setStrikethrough(true);
    }
}
