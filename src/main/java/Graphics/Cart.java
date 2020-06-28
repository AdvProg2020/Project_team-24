package Graphics;

import Controller.ControllerUnit;
import Controller.Controllers.BuyerController;
import Exceptions.ProductDoesNotExistException;
import Exceptions.ProductIsOutOfStockException;
import Exceptions.ProductMediaNotFoundException;
import Exceptions.SellerDoesNotSellOfThisProduct;
import Graphics.Tools.SceneBuilder;
import Model.Models.Accounts.Customer;
import Model.Models.Product;
import Model.Models.Structs.Medias;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Cart implements Initializable, SceneBuilder {

    private static BuyerController buyerController = BuyerController.getInstance();
    private Model.Models.Cart cart = ((Customer) ControllerUnit.getInstance().getAccount()).getCart();
    @FXML
    private TableView<Product> cart_Table;
    @FXML
    private Label totalPrice;
    @FXML
    private MediaView cartGif;
    @FXML
    public TableColumn<Product, Pane> inc_dec_product;
    @FXML
    public TableColumn<Product, Double> product_f_price;
    @FXML
    public TableColumn<Product, Long> product_number;
    @FXML
    public TableColumn<Product, Double> product_price;
    @FXML
    public TableColumn<Product, ImageView> product_image;
    @FXML
    public TableColumn<Product, String> Product_name;

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
        try {
            List<Product> list = buyerController.showProducts();
            cart_Table.setItems(FXCollections.observableList(list));
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
        setProductsName();
        setProductsImage();
        setProductsPrice();
        setProductsNumber();
        setProductFinalPrice();
        setProductsButton();
        playMedia();
    }

    private void playMedia() {
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\Cart\\openGif.mp4").toURI().toString()));
        cartGif.setMediaPlayer(mediaPlayer);
        mediaPlayer.setCycleCount(Integer.MAX_VALUE);
        mediaPlayer.play();
    }

    private void setProductsButton() {
        inc_dec_product.setCellValueFactory(this::getPaneObservableValue);
    }

    @Contract("_ -> new")
    @NotNull
    private ObservableValue<Pane> getPaneObservableValue(@NotNull TableColumn.CellDataFeatures<Product, Pane> param) {
        HBox hBox = new HBox();
        Product value = param.getValue();
        long sellerId = cart.getProductSellers().get(cart.getProductList().indexOf(value.getId()));
        Button increase = new Button();
        Button decrease = new Button();
//            increase.setStyle("-fx-background-image: url(../Graphics/Cart/icons8-add-48.png)");
//            decrease.setStyle("-fx-background-image: url(../Graphics/Cart/icons8-minus-48.png)");
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
        });
        decrease.setOnAction(event -> {
            try {
                buyerController.decrease(value.getId() + "", sellerId + "");
            } catch (ProductDoesNotExistException e) {
                e.printStackTrace();
            }
        });
        hBox.getChildren().addAll(increase,decrease);
        return new SimpleObjectProperty<>(new Pane(hBox));
    }

    private void setProductFinalPrice() {
        product_price.setCellValueFactory(param -> {
            Product value = param.getValue();
            long sellerId = cart.getProductSellers()
                    .get(cart.getProductList().indexOf(value.getId()));
            long number = cart.getProductList().stream()
                    .filter(LoNg -> LoNg == param.getValue().getId())
                    .count();
            try {
                return new SimpleObjectProperty<>(number * value.getProductOfSellerById(sellerId).getPrice());
            } catch (SellerDoesNotSellOfThisProduct e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private void setProductsNumber() {
        product_number.setCellValueFactory(param ->
                new SimpleObjectProperty<>(cart.getProductList().stream()
                        .filter(LoNg -> LoNg == param.getValue().getId()).count())
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
            } catch (SellerDoesNotSellOfThisProduct e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    private void setProductsImage() {
        product_image.setCellValueFactory(param ->
                {
                    try {
                        return new SimpleObjectProperty<>(new ImageView(Medias.getImage(Medias.getMediasById(param.getValue().getMediaId()).getImageSrc())));
                    } catch (ProductMediaNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        );
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
