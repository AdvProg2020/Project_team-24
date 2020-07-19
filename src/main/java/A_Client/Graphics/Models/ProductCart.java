package A_Client.Graphics.Models;

import Structs.MiniProduct;
import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.ProductController;
import Exceptions.CommentDoesNotExistException;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductMediaNotFoundException;
import A_Client.Graphics.MainMenu;
import B_Server.Model.Models.Auction;
import B_Server.Model.Models.Comment;
import B_Server.Model.Models.Product;
import B_Server.Model.Models.Structs.Medias;
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
import java.util.*;
import java.util.stream.Collectors;

public class ProductCart implements Initializable {

    private static List<MiniProduct> productList = null;/*ProductsController
            .getInstance()
            .showProducts();*/
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

        if (sellerIndex + 1 < product.getSellersOfProduct().size())
            this.price_ftx.setText(/);

        if (product.getAuction() != null) newPrice(product.getAuction());

        checkButtons();
    }

    public void prevSeller() {

        if (sellerIndex > 0)
            this.price_ftx.setText(product.getSellersOfProduct().get(--sellerIndex).getPrice() + "");

        if (product.getAuction() != null) newPrice(product.getAuction());

        checkButtons();
    }

    public void gotoProduct() {
        if (product.getCategory() != null) {
            ProductCart.setProductList(getSimilar());
        }
        Product first_compare = A_Client.Graphics.Pages.Product.getFirst_Compare();
        if (first_compare == null) {
            ControllerUnit.getInstance().setProduct(product);
        } else ControllerUnit.getInstance().setProduct(first_compare);
        if (product.getCommentList() != null) {
            setCommentListToShow();
        }
        MainMenu.change(new A_Client.Graphics.Pages.Product().sceneBuilder());
        MainMenu.FilterDisable();
    }

    private void setCommentListToShow() {
        try {
            List<Comment> commentList = ProductController.getInstance().viewComments()
                    .stream().sorted((c1, c2) -> -1).collect(Collectors.toList());
            CommentCart.setCommentList(commentList);
        } catch (CommentDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private List<MiniProduct> getSimilar() {
        return product.getCategory().getProductList().stream()
                .map(id -> {
                    try {
                        return id == 0 ? null : Product.getProductById(id);
                    } catch (ProductDoesNotExistException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private void init() {
        this.product = productList.get(0);
        productList.remove(0);
        setProductCart();
    }

    private void setProductCart() {
        if (product != null) {
            if (product.getMediaId() != 0) setImage();
            setTexts();
            if (product.getAuction() == null) return;
            newPrice(product.getAuction());
        }
    }

    private void setTexts() {
        this.product_name.setText(product.getName());
        this.price_ftx.setText(product.getSellersOfProduct().get(sellerIndex).getPrice() + "");
    }

    private void setImage() {
        Image image = null;
        try {
            image = Medias.getImage(Medias.getMediasById(product.getId()).getImageSrc());
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
        this.productImage.setImage(image);
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
        this.discount.setText(auction.getDiscount().getPercent() + "%");
    }
}
