package Graphics;

import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import Exceptions.InvalidFilterException;
import Graphics.Models.ProductCart;
import Graphics.Tools.SceneBuilder;
import Model.ModelUnit;
import Model.Models.Product;
import com.gilecode.yagson.com.google.gson.internal.$Gson$Preconditions;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenu extends Application implements SceneBuilder, Initializable {

    private ProductsController productsController = ProductsController.getInstance();
    private FilterController filterController = FilterController.getInstance();
    private static Stage primaryStage;
    private static BorderPane center;
    @FXML
    private MediaView gif;
    @FXML
    private BorderPane changeable;
    @FXML
    private TextField searchArea;

    @Override
    public void start(@NotNull Stage stage) {
        Scene scene = sceneBuilder();
        stage.setResizable(false);
        stage.setTitle("سه سوت");
        setPrimaryStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCenter(changeable);
        gif.getMediaPlayer().play();
        gif.getMediaPlayer().setCycleCount(Integer.MAX_VALUE);
        playMusic();
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/MainMenu/MainMenu.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainMenu.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setCenter(BorderPane center) {
        MainMenu.center = center;
    }

    public static BorderPane getCenter() {
        return center;
    }

    public static void change(@NotNull Scene scene) {
        center.setCenter(scene.getRoot());
    }

    public void doCeSuT() {
        // new Scene need.
    }

    public void goLogin() {
        MainMenu.change(new Login().sceneBuilder());
    }

    public void goCart() {
        MainMenu.change(new Cart().sceneBuilder());
    }

    public void goPopulars() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        List<Product> list = findPopulars(productsController.showProducts());
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void goAuction() {
        // new Scene need.
    }

    public void goProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        ProductsMenu.setList(productsController.showProducts());
        ProductCart.setProductList(Product.getList());
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void goSearch() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        String filterStr = searchArea.getText();
        try {
            filterController.filter("ProductName", filterStr);
        } catch (InvalidFilterException e) {
            e.printStackTrace();
        }
        List<Product> list = productsController.showProducts();
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public static void main(String[] args) {
        ModelUnit.getInstance().preprocess_loadLists();
        launch(args);
    }

    public static void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()).start();
    }

    @NotNull
    private List<Product> findPopulars(List<Product> list) {
        List<Product> newList = new ArrayList<>(list);
        newList.sort((o1, o2) -> -1 * Long.compare(o1.getNumberOfVisitors(),o2.getNumberOfVisitors()));
        return newList;
    }

    private void playMusic() {
        new Thread(() -> {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/Graphics/SoundEffect/mainMenu_sound.mp3").toURI().toString()));
            mediaPlayer.setCycleCount(Integer.MAX_VALUE);
            mediaPlayer.play();
        }).start();
    }
}
