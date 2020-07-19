package A_Client.Graphics.Models;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Pages.Product;
import Structs.MiniAuction;
import Structs.MiniComment;
import Structs.MiniProduct;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductCart implements Initializable {

    private final Client client = SendAndReceive.getClient();
    private static List<MiniProduct> productList = SendAndReceive.getAllMyProducts();
    private MiniProduct product;
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

    public static void setProductList(List<MiniProduct> productList) {
        ProductCart.productList = productList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (productList.isEmpty()) {
            mainPane.setVisible(false);
            mainPane.setDisable(true);
            return;
        }
        init();
    }

    public void nextSeller() {

        if (sellerIndex + 1 < product.getProfSell().size())
            this.price_ftx.setText(product.getProfSell().get(++sellerIndex).getPrice() + "");

        if (product.getAuctionId() != null) newPrice(SendAndReceive
                .getAuctionById(product.getAuctionId()));

        checkButtons();
    }

    public void prevSeller() {

        if (sellerIndex > 0)
            this.price_ftx.setText(product.getProfSell().get(--sellerIndex).getPrice() + "");

        if (product.getAuctionId() != null) newPrice(SendAndReceive
                .getAuctionById(product.getAuctionId()));

        checkButtons();
    }

    public void gotoProduct() {
        if (product.getCateId() != null)
            ProductCart.setProductList(getSimilar());

        MiniProduct first_compare = Product.getFirst_Compare();
        if (first_compare == null) setCurrentProduct(product);
        else setCurrentProduct(first_compare);

        setCommentListToShow();

        MainMenu.change(new Product().sceneBuilder());
        MainMenu.FilterDisable();
    }

    private void setCurrentProduct(MiniProduct miniProduct) {
        SendAndReceive.SetCurrentProduct(miniProduct.getProductId());
        client.getClientInfo().setProductId(miniProduct.getProductId());
    }

    private void setCommentListToShow() {
        List<MiniComment> commentList = SendAndReceive.getAllCommentOfProduct(product.getProductId())
                .stream().sorted((c1, c2) -> -1).collect(Collectors.toList());
        CommentCart.setCommentList(commentList);
    }

    private List<MiniProduct> getSimilar() {
        return SendAndReceive.getAllProductsOfCategoryById(product.getCateId());
    }

    private void init() {
        this.product = productList.get(0);
        productList.remove(0);
        setProductCart();
    }

    private void setProductCart() {
        if (product != null) {
            if (product.getMediasId() != null) setImage();
            setTexts();
            if (product.getAuctionId() != null) newPrice(SendAndReceive.getAuctionById(product.getAuctionId()));
        }
    }

    private void setTexts() {
        this.product_name.setText(product.getProductName());
        this.price_ftx.setText(product.getProfSell()
                .get(sellerIndex).getPrice() + "");
    }

    private void setImage() {
        Image image = SendAndReceive.getImageById(product.getMediasId());
        this.productImage.setImage(image);
    }

    private void checkButtons() {

        if (sellerIndex == product.getProfSell().size() - 1) {
            nextButton.setDisable(true);
        } else nextButton.setDisable(false);

        if (sellerIndex == 0) {
            prevButton.setDisable(true);
        } else prevButton.setDisable(false);
    }

    private void newPrice(@NotNull MiniAuction auction) {

        this.discount.setVisible(true);
        this.price_ltx.setVisible(true);
        this.discountImage.setVisible(true);

        double price = product.getProfSell().get(sellerIndex).getPrice();
        double discountAmount = auction.getAuctionDiscount(price);

        this.price_ltx.setText(price - discountAmount + "");
        this.price_ftx.setStrikethrough(true);
        this.discount.setText(auction.getAuctionPercent() + "%");
    }
}
