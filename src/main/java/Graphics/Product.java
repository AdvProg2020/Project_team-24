package Graphics;

import Controller.ControllerUnit;
import Exceptions.ProductMediaNotFoundException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Structs.Medias;
import Model.Models.Structs.ProductOfSeller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
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

    public static Model.Models.Product getFirst_Compare() {
        return First_Compare;
    }

    public static void setFirst_Compare(Model.Models.Product first_Compare) {
        First_Compare = first_Compare;
    }

    private void setImage() throws ProductMediaNotFoundException {
        product_image.setImage(
                Medias.getMediasById(productObject.getMediaId()).getImage()
        );
    }

    private void setPrice() {
        product_price.setText(
                productObject.getSellersOfProduct()
                        .get(sellerIndex)
                        .getPrice() + " "
        );
    }

    private void setMedia() throws ProductMediaNotFoundException {
        product_media.setMediaPlayer(
                Medias.getMediasById(productObject.getMediaId()).getPlayer()
        );
    }

    private void setStars() {
        Image filled = new Image(
                new File("src/main/resources/Graphics/Product/icons8-star-filled-16.png")
                        .toURI()
                        .toString()
        );
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
}
