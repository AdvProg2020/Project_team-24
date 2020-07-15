package A_Client.Graphics;

import A_Client.Client.Client;
import A_Client.Client.MessageInterfaces.MessageSupplier;
import A_Client.Graphics.Menus.AuctionsMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import A_Client.Graphics.Models.AuctionCart;
import A_Client.Graphics.Models.ProductCart;
import A_Client.Graphics.Other.PopUp;
import A_Client.Graphics.Pages.Cart;
import A_Client.Graphics.Pages.CeSut;
import A_Client.Graphics.Pages.Login;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.JsonHandler.JsonHandler;
import A_Client.Graphics.MiniModels.Structs.MiniAuction;
import A_Client.Graphics.MiniModels.Structs.MiniCate;
import A_Client.Graphics.MiniModels.Structs.MiniProduct;
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
import java.util.*;
import java.util.stream.Collectors;

public class MainMenu extends Application implements SceneBuilder, Initializable {

    private static final Client client = new Client("localhost", 5431);
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

    public static void setFilter(Pane filter) {
        MainMenu.filter = filter;
    }

    public static Client getClient() {
        return client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCenter(changeable);
        setFilter(filterArea);
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.GetAllCategories, Collections.singletonList(client.getClientInfo().getToken()));
        List<MenuItem> collect = new JsonHandler<MiniCate>().JsonsToObjectList(answers, MiniCate.class)
                .stream().map(this::getCategorySelection).collect(Collectors.toList());
        category_select.getItems().addAll(FXCollections.observableArrayList(collect));
        gif.getMediaPlayer().play();
        gif.getMediaPlayer().setCycleCount(Integer.MAX_VALUE);
        playMusic("src/main/resources/Graphics/SoundEffect/mainMenu_sound.mp3");
        if (client.getClientInfo().getAccountId() == null) return;
        userArea_btn.setDisable(false);
        login_logout_btn.setText("خروج ...");
        login_logout_btn.setOnAction(event -> logout());
        if (client.getClientInfo().getAccountTy().equals("Customer")) cart_btn.setDisable(false);
    }

    @NotNull
    private MenuItem getCategorySelection(@NotNull MiniCate category) {
        MenuItem menuItem = new MenuItem();
        menuItem.setText(category.getCateName());
        menuItem.setOnAction(event -> showCategoryProducts(category));
        return menuItem;
    }

    private void showCategoryProducts(@NotNull MiniCate category) {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.add(category.getCateId());
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.GetAllProductsOfCategory, list);
        List<MiniProduct> miniProducts = new JsonHandler<MiniProduct>()
                .JsonsToObjectList(answers, MiniProduct.class);
        setProducts(miniProducts);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    private void logout() {
        client.sendAndReceive(MessageSupplier.RequestType.Logout, Collections.singletonList(client.getClientInfo().getToken()));
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
        List<String> answers = client.sendAndReceive(
                MessageSupplier.RequestType.GetAllAuctions, Collections.singletonList(client.getClientInfo().getToken())
        );
        List<MiniAuction> miniAuctions = new JsonHandler<MiniAuction>()
                .JsonsToObjectList(answers, MiniAuction.class);
        AuctionsMenu.setList(miniAuctions);
        AuctionCart.setAuctionList(miniAuctions);
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
        GetAllProducts(MessageSupplier.RequestType.GetAllPopularProducts);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    public void goUserArea() {
        String accountTy = client.getClientInfo().getAccountTy();
        switch (accountTy) {
            case "Manager":
                MainMenu.change(new A_Client.Graphics.Accounts.Manager().sceneBuilder());
                break;
            case "Seller":
                MainMenu.change(new A_Client.Graphics.Accounts.Seller().sceneBuilder());
                break;
            case "Customer":
                MainMenu.change(new A_Client.Graphics.Accounts.Customer().sceneBuilder());
                break;
            default:
                return;
        }
        MainMenu.FilterDisable();
        popUp();
        enableBack();
    }

    public void goProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        GetAllProducts(MessageSupplier.RequestType.GetAllProducts);
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
        GetAllProducts(MessageSupplier.RequestType.GetAllProducts);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    private void GetAllProducts(MessageSupplier.RequestType getAllProducts) {
        List<String> answers = client.sendAndReceive(
                getAllProducts, Collections.singletonList(client.getClientInfo().getToken())
        );
        List<MiniProduct> miniProducts = new JsonHandler<MiniProduct>()
                .JsonsToObjectList(answers, MiniProduct.class);
        setProducts(miniProducts);
    }

    private void setProducts(List<MiniProduct> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }

    private void addFilter_search() {
        String filterStr = searchArea.getText();
        List<String> list = new ArrayList<>();
        list.add(client.getClientInfo().getToken());
        list.add(filterStr);
        client.sendAndReceive(MessageSupplier.RequestType.SetNewFilter, list);
    }

    public static void main(String[] args) {
        List<String> answers = client.sendAndReceive(MessageSupplier.RequestType.SetNewToken, null);
        client.getClientInfo().setToken(answers.get(0));
        launch(args);
    }

    private void popUp() {
        Stage stage = new Stage();
        stage.setScene(new PopUp().sceneBuilder());
        stage.showAndWait();
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
