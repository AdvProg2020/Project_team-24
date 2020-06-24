package Graphics;

import Controller.ControllerUnit;
import Graphics.Tools.PaneBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Product implements Initializable, PaneBuilder {

    private Model.Models.Product productObject;
    private List<ImageView> stars;
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
    public Pane paneBuilder() {

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
        stars = new ArrayList<>(Arrays.asList(star_01, star_02, star_03, star_04, star_05));
        productObject = ControllerUnit.getInstance().getProduct();
        setStars();
        setMedia();
        setPrice();
        setImage();
    }

    private void setImage() {
        product_image.setImage(

        );
    }

    private void setPrice() {
        product_price.setText(
                productObject.getSellersOfProduct()
                        .get(0)
                        .getPrice() + ""
        );
    }

    private void setMedia() {
        product_media.setMediaPlayer(
                new MediaPlayer(
                        productObject.getProductMedia()
                )
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

    public void compare() {
    }

    public void play() {
    }

    public void addToCart() {
    }
}
