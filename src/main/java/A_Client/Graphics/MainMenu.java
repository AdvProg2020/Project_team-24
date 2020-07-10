package A_Client.Graphics;

import A_Client.Graphics.Menus.AuctionsMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import A_Client.Graphics.Models.AuctionCart;
import A_Client.Graphics.Models.ProductCart;
import A_Client.Graphics.Tools.SceneBuilder;
import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.AuctionController;
import B_Server.Controller.Controllers.FilterController;
import B_Server.Controller.Controllers.ManagerController;
import B_Server.Controller.Controllers.ProductsController;
import Controller.Controllers.*;
import Exceptions.InvalidFilterException;
import Exceptions.ProductDoesNotExistException;
import B_Server.Model.ModelUnit;
import B_Server.Model.Models.Account;
import B_Server.Model.Models.Accounts.Customer;
import B_Server.Model.Models.Accounts.Manager;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Auction;
import B_Server.Model.Models.Category;
import B_Server.Model.Models.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenu extends Application implements SceneBuilder, Initializable {

    private ProductsController productsController = ProductsController.getInstance();
    private AuctionController auctionController = AuctionController.getInstance();
    private FilterController filterController = FilterController.getInstance();
    private Account account = ControllerUnit.getInstance().getAccount();
    private static Stage primaryStage;
    private static MediaPlayer player;
    private static BorderPane center;
    private static Pane filter;

    @FXML
    private MenuButton category_select;
    @FXML
    private Pane filterArea;
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

    public static void FilterEnable() {
        filter.setDisable(false);
        filter.setVisible(true);
    }

    public static void FilterDisable() {
        filter.setDisable(true);
        filter.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCenter(changeable);
        filter = filterArea;
        List<MenuItem> collect = ManagerController.getInstance().showAllCategories().stream().map(this::getCategorySelection).collect(Collectors.toList());
        category_select.getItems().addAll(FXCollections.observableArrayList(collect));
        gif.getMediaPlayer().play();
        gif.getMediaPlayer().setCycleCount(Integer.MAX_VALUE);
        playMusic("src/main/resources/Graphics/SoundEffect/mainMenu_sound.mp3");
        if (account == null) return;
        userArea_btn.setDisable(false);
        login_logout_btn.setText("خروج ...");
        login_logout_btn.setOnAction(event -> logout());
        if (account instanceof Customer) cart_btn.setDisable(false);
    }

    @NotNull
    private MenuItem getCategorySelection(@NotNull Category category) {
        MenuItem menuItem = new MenuItem();
        menuItem.setText(category.getName());
        menuItem.setOnAction(event ->showCategoryProducts(category));
        return menuItem;
    }

    private void showCategoryProducts(@NotNull Category category) {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        List<Product> list = findPopulars(
                category.getProductList().stream().map(aLong -> {
                    try {
                        return Product.getProductById(aLong);
                    } catch (ProductDoesNotExistException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList())
        );
        setProducts(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    private void logout() {
        ControllerUnit.getInstance().setAccount(null);
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
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

    public void goAuction() {
        ArrayList<Auction> list = new ArrayList<>(auctionController.offs());
        AuctionsMenu.setList(list);
        AuctionCart.setAuctionList(list);
        MainMenu.change(new AuctionsMenu().sceneBuilder());
        MainMenu.FilterDisable();
        enableBack();
    }

    public void goMainMenu() {
        getPrimaryStage().setScene(new MainMenu().sceneBuilder());
        enableBack();
    }

    public void goLogin() {
        MainMenu.change(new Login().sceneBuilder());
        MainMenu.FilterDisable();
        enableBack();
    }

    public void goCart() {
        MainMenu.change(new Cart().sceneBuilder());
        MainMenu.FilterDisable();
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
            MainMenu.change(new A_Client.Graphics.Accounts.Manager().sceneBuilder());
        else if (account instanceof Seller)
            MainMenu.change(new A_Client.Graphics.Accounts.Seller().sceneBuilder());
        else if (account instanceof Customer)
            MainMenu.change(new A_Client.Graphics.Accounts.Customer().sceneBuilder());
        else return;
        MainMenu.FilterDisable();
        popUp();
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
        MainMenu.FilterDisable();
        enableBack();
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

    private void popUp() {
        Stage stage = new Stage();
        stage.setScene(new PopUp().sceneBuilder());
        stage.showAndWait();
    }
}
