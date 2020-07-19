package A_Client.Graphics.Pages;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.MainMenu;
import Exceptions.*;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniProduct;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Cart implements Initializable, SceneBuilder {

    private final Client client = SendAndReceive.getClient();
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

        List<MiniProduct> list = SendAndReceive
                .getAllProductOfUserCart(client.getClientInfo().getAccountId());
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
        try {
            totalPrice.setText(cart.getTotalPrice() + "");
        } catch (ProductDoesNotExistException e) {
            alertError("Error product" , e.getMessage());
        } catch (SellerDoesNotSellOfThisProduct e) {
            alertError("Error seller" , e.getMessage());
        }
    }

    private void playMedia() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\Cart\\openGif.mp4").toURI().toString()));
        cartGif.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        mediaPlayer.play();
    }

    private void setProductsButton() {
        inc_dec_product.setCellValueFactory(param -> {
            Product value = param.getValue();
            long sellerId = cart.getProductSellers().get(cart.getProductList().indexOf(value.getId()));
            HBox hBox = new HBox();
            Button increase = new Button("+");
            Button decrease = new Button("-");
            increase.setOnAction(event -> {
                try {
                    buyerController.increase(value.getId() + "", sellerId + "");
                } catch (ProductDoesNotExistException e) {
                    productDoesNotExist();
                } catch (ProductIsOutOfStockException e) {
                    productOutOfStack();
                } catch (SellerDoesNotSellOfThisProduct e) {
                    e.printStackTrace();
                }
                init();
            });
            decrease.setOnAction(event -> {
                try {
                    buyerController.decrease(value.getId() + "", sellerId + "");
                } catch (ProductDoesNotExistException e) {
                    e.printStackTrace();
                }
                init();
            });
            hBox.getChildren().addAll(increase, decrease);
            Pane pane = new Pane(hBox);
            return new SimpleObjectProperty<>(pane);
        });
    }

    private void setProductFinalPrice() {
        product_f_price.setCellValueFactory(param -> {
            Product value = param.getValue();
            long sellerId = cart.getProductSellers()
                    .get(cart.getProductList().indexOf(value.getId()));
            long number = cart.getProductList().stream()
                    .filter(LoNg -> LoNg == param.getValue().getId())
                    .count();
            try {
                return new SimpleObjectProperty<>(number * value.getProductOfSellerById(sellerId).getPrice());
            } catch (SellerDoesNotSellOfThisProduct sellerDoesNotSellOfThisProduct) {
                sellerDoesNotSellOfThisProduct.printStackTrace();
            }
            return null;
        });
    }

    private void setProductsNumber() {
        product_number.setCellValueFactory(param ->
                new SimpleObjectProperty<>(cart.getProductList().stream().filter(LoNg -> LoNg == param.getValue().getId()).count())
        );
    }

    private void setProductsPrice() {
        product_price.setCellValueFactory(param -> {
            Product value = param.getValue();
            long sellerId = cart.getProductSellers()
                    .get(cart.getProductList().indexOf(value.getId()));
            try {
                double price = value.getProductOfSellerById(sellerId).getPrice();
                return new SimpleObjectProperty<>(price);
            } catch (SellerDoesNotSellOfThisProduct sellerDoesNotSellOfThisProduct) {
                sellerDoesNotSellOfThisProduct.printStackTrace();
            }
            return null;
        });
    }

    private void setProductsImage() {
        product_image.setCellValueFactory(this::getImageViewObservableValue);
    }

    @Nullable
    private ObservableValue<ImageView> getImageViewObservableValue(@NotNull TableColumn CellDataFeatures<Product, ImageView> param) {
        try {
            return new SimpleObjectProperty<>(new ImageView(SendAndReceive.getImageById()));
        } catch (ProductMediaNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setProductsName() {
        Product_name.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
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
