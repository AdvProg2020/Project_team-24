package A_Client.Graphics.Pages;

import A_Client.Graphics.Creates.CreateProduct;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import A_Client.Graphics.Models.CommentCart;
import A_Client.Graphics.Models.ProductCart;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.MiniModels.FieldAndFieldList.Field;
import Exceptions.*;
import Structs.MiniProduct;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Product implements Initializable, SceneBuilder {

    private static MiniProduct First_Compare;
    private static MiniProduct productObject;
    private List<ImageView> stars = new ArrayList<>();
    private int sellerIndex = 0;
    @FXML
    private Button setPoint_btn;
    @FXML
    private TextField pointArea;
    @FXML
    private Button submit_comment;
    @FXML
    private TextField title;
    @FXML
    private TextField sender;
    @FXML
    private TextArea content;
    @FXML
    private ImageView product_image;
    @FXML
    private Label product_price;
    @FXML
    private ImageView star_01;
    @FXML
    private ImageView star_02;
    @FXML
    private ImageView star_03;
    @FXML
    private ImageView star_04;
    @FXML
    private ImageView star_05;
    @FXML
    private MediaView product_media;
    @FXML
    private MediaView gif;
    @FXML
    private Label productName;
    @FXML
    private Button edit_btn;
    @FXML
    private TableView<Field> categoryFeature;
    @FXML
    private TableView<Field> ProductFeatures;
    @FXML
    private TableColumn<Field, String> vizhegiC;
    @FXML
    private TableColumn<Field, String> meghdarC;
    @FXML
    private TableColumn<Field, String> vizhegiP;
    @FXML
    private TableColumn<Field, String> meghdarP;
    @FXML
    private MediaView leftGif;
    @FXML
    private Button addMe_btn;
    @FXML
    private Button deleteProduct_btn;
    @FXML
    private Button addToCart_btn;

    public static MiniProduct getFirst_Compare() {
        return First_Compare;
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Product/Product.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stars.addAll(Arrays.asList(star_01, star_02, star_03, star_04, star_05));
        productObject = ControllerUnit.getInstance().getProduct();

        if (productObject == null) return;
        try {
            if (productObject.getMediaId() != 0) {
                Medias medias = Medias.getMediasById(productObject.getMediaId());
                if (medias.getImageSrc() != null) setImage();
                if (medias.getPlayerSrc() != null) setMedia();
            }
            setStars();
            setPrice();
            setName();
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }

        Account account = ControllerUnit.getInstance().getAccount();
        if (account instanceof Seller) {
            if (((Seller) account).getProductList().contains(productObject.getId())) {
                edit_btn.setDisable(false);
                deleteProduct_btn.setDisable(false);
            } else addMe_btn.setDisable(false);
        }

        if (account instanceof Customer) {
            addToCart_btn.setDisable(false);
            sender.setText(account.getUserName());
            pointArea.setDisable(false);
            setPoint_btn.setDisable(false);
        }

        MediaPlayer value = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\Product\\addToCart.mp4").toURI().toString()));
        gif.setMediaPlayer(value);
        value.setCycleCount(Integer.MAX_VALUE);
        value.play();

        MediaPlayer v = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\Product\\productPage.mp4").toURI().toString()));
        leftGif.setMediaPlayer(v);
        v.setCycleCount(Integer.MAX_VALUE);
        v.play();

        setCategoryFeature();
        setProductFeatures();
    }

    private void setName() {
        productName.setText(productObject.getName());
    }

    public void compare() {
        if (getFirst_Compare() == null) {
            setFirst_Compare(productObject);
            goProductsArea();
        } else goComparingArea();
    }

    public void play() {
        product_media.getMediaPlayer().play();
    }

    public void addToCart() {
        Account account = ControllerUnit.getInstance().getAccount();
        if (account instanceof Customer) {
            ProductOfSeller productOfSeller = productObject
                    .getSellersOfProduct().get(sellerIndex);

            ((Customer) account)
                    .getCart()
                    .addProductToCart(
                            productOfSeller.getSellerId(), productObject.getId()
                    );

            show();
        }
    }

    private void show() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ثبت سفارش");
        alert.setContentText("سفارش شما با موفقیت ثبت شد");
        alert.showAndWait();
    }

    public static void setFirst_Compare(B_Server.Model.Models.Product first_Compare) {
        First_Compare = first_Compare;
    }

    private void setImage() throws ProductMediaNotFoundException {
        product_image.setImage(Medias.getImage(Medias.getMediasById(productObject.getMediaId()).getImageSrc()));
    }

    private void setPrice() {
        product_price.setText(productObject.getSellersOfProduct().get(sellerIndex).getPrice() + "");
    }

    private void setMedia() throws ProductMediaNotFoundException {
        Medias mediasById = Medias.getMediasById(productObject.getMediaId());
        if (mediasById.getPlayerSrc() != null) {
            product_media.setMediaPlayer(Medias.getMediaPlayer(mediasById.getPlayerSrc()));
        }
    }

    private void setStars() {
        Image filled = new Image(new File("src/main/resources/Graphics/Product/icons8-star-filled-16.png").toURI().toString());
        for (int i = 0; i < productObject.getAverageScore(); i++) {
            stars.get(i).setImage(filled);
        }
    }

    private void goProductsArea() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        setProducts(ProductsController.getInstance().showProducts());
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void setProducts(List<B_Server.Model.Models.Product> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }

    private void goComparingArea() {
        // ?
    }

    public void AddMeAsSeller() {
        CreateProduct.setMode(CreateProduct.Mode.AddSeller);
        MainMenu.change(new CreateProduct().sceneBuilder());
    }

    public void deleteProduct() {
        Account account = ControllerUnit.getInstance().getAccount();
        new Request(account.getId(), "remove product", "remove", productObject);
    }

    public void setCategoryFeature() {
        if (productObject.getCategoryInfo().getList().getFieldList() != null) {
            List<Field> list = productObject.getCategoryInfo().getList().getFieldList();
            categoryFeature.setItems(FXCollections.observableList(list));
            vizhegiC.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFieldName()));
            meghdarC.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString()));
        }
    }

    public void setProductFeatures() {
        if (productObject.getProduct_Info().getList().getFieldList() != null) {
            List<Field> list = productObject.getProduct_Info().getList().getFieldList();
            ProductFeatures.setItems(FXCollections.observableList(list));
            vizhegiP.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFieldName()));
            meghdarP.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getString()));
        }
    }

    public void editProduct() {
        CreateProduct.setMode(CreateProduct.Mode.Edit);
        MainMenu.change(new CreateProduct().sceneBuilder());
    }

    public void submit_comment_btn() {

        reset_btn();

        if (ControllerUnit.getInstance().getAccount() instanceof Customer)
            try {
                ProductController.getInstance().addComment(title.getText(), content.getText());
                MainMenu.change(new Product().sceneBuilder());
            } catch (ProductDoesNotExistException | CannotRateException e) {
                e.printStackTrace();
            }
        else commentError();

        if (productObject.getCommentList() != null) {
            setCommentListToShow();
        }
        MainMenu.change(new Product().sceneBuilder());
    }

    private void setCommentListToShow() {
        try {
            List<Comment> commentList = ProductController.getInstance().viewComments();
            commentList.sort(Collections.reverseOrder());
            CommentCart.setCommentList(commentList);
        } catch (CommentDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private void reset_btn() {
        submit_comment.setStyle("-fx-border-color: #eeba00;");
    }

    private void commentError() {
        Tooltip toolTip_username = new Tooltip();
        toolTip_username.setText("نمی توانید کامنت بگذارید");
        toolTip_username.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        submit_comment.setTooltip(toolTip_username);
        submit_comment.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
    }

    public void PointButton() {
        long id = ControllerUnit.getInstance().getAccount().getId();
        if (productObject.getBuyerList().contains(id) &&
                productObject.getScoreList().stream().noneMatch(aLong -> {
                    try {
                        Score score = Score.getScoreById(aLong);
                        return score.getUserId() == id;
                    } catch (ScoreDoesNotExistException e) {
                        e.printStackTrace();
                    }
                    return false;
                })) try {
            BuyerController.getInstance().rate(productObject.getId() + "", pointArea.getText());
        } catch (ProductDoesNotExistException e) {
            alertError("Product not found", e.getMessage());
        } catch (CannotRateException e) {
            alertError("Can't rate", e.getMessage());
        }
        else alertError("Can't score", "You did it before , Or you didn't buy it.");
        setStars();
    }

    private void alertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
