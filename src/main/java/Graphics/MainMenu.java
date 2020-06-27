package Graphics;

import Controller.ControllerUnit;
import Controller.Controllers.AuctionController;
import Controller.Controllers.FilterController;
import Controller.Controllers.ProductsController;
import Exceptions.InvalidFilterException;
import Graphics.Menus.AuctionsMenu;
import Graphics.Menus.ProductsMenu;
import Graphics.Models.AuctionCart;
import Graphics.Models.ProductCart;
import Graphics.Tools.SceneBuilder;
import Model.ModelUnit;
import Model.Models.Account;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Manager;
import Model.Models.Accounts.Seller;
import Model.Models.Auction;
import Model.Models.Product;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenu extends Application implements SceneBuilder, Initializable {


    private ProductsController productsController = ProductsController.getInstance();
    private AuctionController auctionController = AuctionController.getInstance();
    private FilterController filterController = FilterController.getInstance();
    private Account account = ControllerUnit.getInstance().getAccount();
    private static Stage primaryStage;
    private static MediaPlayer player;
    private static BorderPane center;

    @FXML
    private MediaView gif;
    @FXML
    private BorderPane changeable;
    @FXML
    private TextField searchArea;
    @FXML
    private Button cart_btn;
    @FXML
    private Button userArea_btn;
    @FXML
    private Button back_btn;
    @FXML
    private Button login_logout_btn;

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
        playMusic("src/main/resources/Graphics/SoundEffect/mainMenu_sound.mp3");
        if (account == null) return;
        userArea_btn.setDisable(false);
        login_logout_btn.setText("خروج ...");
        login_logout_btn.setOnAction(event -> logout());
        if (account instanceof Customer) cart_btn.setDisable(false);
    }

    private void logout() {
        ControllerUnit.getInstance().setAccount(null);
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\Sort\\Sort.fxml").toURI().toURL());
           // return FXMLLoader.load(new File("src/main/resources/Graphics/MainMenu/MainMenu.fxml").toURI().toURL());
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

    public void goAuction() {
        ArrayList<Auction> list = new ArrayList<>(auctionController.offs());
        AuctionsMenu.setList(list);
        AuctionCart.setAuctionList(list);
        MainMenu.change(new AuctionsMenu().sceneBuilder());
    }

    public void goMainMenu() {
        getPrimaryStage().setScene(new MainMenu().sceneBuilder());
        enableBack();
    }

    public void goLogin() {
        MainMenu.change(new Login().sceneBuilder());
        enableBack();
    }

    public void goCart() {
        MainMenu.change(new Cart().sceneBuilder());
        enableBack();
    }

    public void goPopulars() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        List<Product> list = findPopulars(productsController.showProducts());
        setProducts(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    public void goUserArea() {
        if (account instanceof Manager)
            MainMenu.change(new Graphics.Accounts.Manager().sceneBuilder());
        else if (account instanceof Seller)
            MainMenu.change(new Graphics.Accounts.Seller().sceneBuilder());
        else if (account instanceof Customer)
            MainMenu.change(new Graphics.Accounts.Customer().sceneBuilder());
        else return;
        enableBack();
    }

    public void goProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        setProducts(productsController.showProducts());
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    public void goCeSut() {
        MainMenu.change(new CeSut().sceneBuilder());
    }

    public void goSearching() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        addFilter_search();
        List<Product> list = productsController.showProducts();
        setProducts(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    private void setProducts(List<Product> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }

    private void addFilter_search() {
        String filterStr = searchArea.getText();
        try {
            filterController.filter("ProductName", filterStr);
        } catch (InvalidFilterException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ModelUnit.getInstance().preprocess_loadLists();
        launch(args);
    }

    @NotNull
    private List<Product> findPopulars(List<Product> list) {
        List<Product> newList = new ArrayList<>(list);
        newList.sort((o1, o2) -> -1 * Long.compare(o1.getNumberOfVisitors(), o2.getNumberOfVisitors()));
        return newList;
    }

    private void enableBack() {
        back_btn.setDisable(false);
    }

    public static void playMusic(String addr) {
        if (player != null) player.dispose();
        new Thread(() -> {
            player = new MediaPlayer(new Media(new File(addr).toURI().toString()));
            player.setCycleCount(Integer.MAX_VALUE);
            player.play();
        }).start();
    }
}
