package A_Client.Graphics.Pages;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniCart;
import Structs.MiniProduct;
import Structs.ProductVsSeller.ProductOfSeller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Cart implements Initializable, SceneBuilder {

    private final Client client = SendAndReceive.getClient();
    public MediaView cartGif1;
    private MiniCart cart;
    @FXML
    private TableView<MiniProduct> cart_Table;
    @FXML
    private Label totalPrice;
    @FXML
    private TableColumn<MiniProduct, Pane> inc_dec_product;
    @FXML
    private TableColumn<MiniProduct, Double> product_f_price;
    @FXML
    private TableColumn<MiniProduct, Long> product_number;
    @FXML
    private TableColumn<MiniProduct, Double> product_price;
    @FXML
    private TableColumn<MiniProduct, ImageView> product_image;
    @FXML
    private TableColumn<MiniProduct, String> Product_name;
    @FXML
    private MediaView cartGif;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/Cart/Cart.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }

    private void init() {

        cart = SendAndReceive.getCartByUserId(client.getClientInfo().getAccountId());

        if (cart == null) return;

        List<MiniProduct> list = SendAndReceive.getAllProductOfUserCart(cart.getId());

        cart_Table.setItems(FXCollections.observableList(list));

        setProductsName();
        setProductsImage();
        setProductsPrice();
        setProductsNumber();
        setProductFinalPrice();
        setProductsButton();
        setTotalPrice();

        playMedia();
    }

    private void setTotalPrice() {
        totalPrice.setText(cart.getTotalPrice() + "");
    }

    private void playMedia() {
        String source = new File("src\\main\\resources\\Graphics\\Cart\\openGif.mp4").toURI().toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(source));
        cartGif.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        mediaPlayer.play();
    }

    private void setProductsButton() {
        inc_dec_product.setCellValueFactory(param -> {

            MiniProduct value = param.getValue();
            long sellerId = cart.getSellersId()
                    .get(cart.getProductsId().indexOf(Long.parseLong(value.getProductId())));

            HBox hBox = new HBox();
            Button increase = new Button("+");
            Button decrease = new Button("-");

            increase.setOnAction(event -> {
                SendAndReceive.increaseProduct(value.getProductId(), sellerId + "");
                init();
            });

            decrease.setOnAction(event -> {
                SendAndReceive.decreaseProduct(value.getProductId(), sellerId + "");
                init();
            });

            hBox.getChildren().addAll(increase, decrease);
            Pane pane = new Pane(hBox);
            return new SimpleObjectProperty<>(pane);
        });
    }

    private void setProductFinalPrice() {
        product_f_price.setCellValueFactory(param -> {
            MiniProduct value = param.getValue();

            long sellerId = cart.getSellersId()
                    .get(cart.getProductsId().indexOf(Long.parseLong(value.getProductId())));

            long number = cart.getProductsId().stream()
                    .filter(LoNg -> value.getProductId().equals(LoNg + ""))
                    .count();

            double price = value.getProfSell().stream().filter(productOfSeller ->
                    productOfSeller.getSellerId() == sellerId).findFirst()
                    .orElse(new ProductOfSeller(0,0,0)).getPrice();

            return new SimpleObjectProperty<>(number * price);
        });
    }

    private void setProductsNumber() {
        product_number.setCellValueFactory(param ->
                new SimpleObjectProperty<>(cart.getProductsId()
                        .stream().filter(LoNg -> param.getValue()
                                .getProductId().equals(LoNg + "")).count())
        );
    }

    private void setProductsPrice() {
        product_price.setCellValueFactory(param -> {
            MiniProduct value = param.getValue();
            long sellerId = cart.getSellersId()
                    .get(cart.getProductsId().indexOf(Long.parseLong(value.getProductId())));

            double price = value.getProfSell().stream().filter(productOfSeller ->
                    productOfSeller.getSellerId() == sellerId).findFirst()
                    .orElse(new ProductOfSeller(0,0,0)).getPrice();

            return new SimpleObjectProperty<>(price);
        });
    }

    private void setProductsImage() {
        product_image.setCellValueFactory(param -> new SimpleObjectProperty<>(
                new ImageView(SendAndReceive.getImageById(param.getValue().getMediasId()))));
    }

    private void setProductsName() {
        Product_name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProductName()));
    }

    public void back() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    public void purchase() {
        MainMenu.change(new Payment().sceneBuilder());
    }

    private void productOutOfStack() {
        alertError("productOutOfStack", "نداریم ...");
    }

    private void productDoesNotExist() {
        alertError("productDoesNotExist", "محصول نامعتبر است");
    }

    private void alertError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
