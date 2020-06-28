package Graphics;

import Controller.ControllerUnit;
import Controller.Controllers.SellerController;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductMediaNotFoundException;
import Graphics.Creates.CreateProduct;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.Field.Field;
import Model.Models.Structs.Medias;
import Model.Models.Structs.ProductOfSeller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Product implements Initializable, SceneBuilder {

    private static Model.Models.Product First_Compare;
    private static Model.Models.Product productObject;
    private List<ImageView> stars = new ArrayList<>();
    private int sellerIndex = 0;
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

    public static Model.Models.Product getFirst_Compare() {
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
            setStars();
            setMedia();
            setPrice();
            setImage();
            setName();
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }

        Account account = ControllerUnit.getInstance().getAccount();
        if (account instanceof Seller && ((Seller) account).getProductList().contains(productObject.getId())) {
            edit_btn.setDisable(false);
        }

        MediaPlayer value = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\Product\\addToCart.mp4").toURI().toString()));
        gif.setMediaPlayer(value);
        value.setCycleCount(Integer.MAX_VALUE);
        value.play();

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
        }
    }

    public static void setFirst_Compare(Model.Models.Product first_Compare) {
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
        // ?
    }

    private void goComparingArea() {
        // ?
    }

    public void AddMeAsSeller() {
        //?
    }

    public void deleteProduct() {
        //?
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

    public void addComment() {
        MainMenu.change(new CreateComment().sceneBuilder());
    }

    public void editProduct() {
        CreateProduct.setMode(CreateProduct.Mode.Edit);
        MainMenu.change(new CreateProduct().sceneBuilder());
    }
}
