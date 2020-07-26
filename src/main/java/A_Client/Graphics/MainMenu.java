package A_Client.Graphics;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Accounts.Roles.Customer;
import A_Client.Graphics.Accounts.Roles.Manager;
import A_Client.Graphics.Accounts.Roles.Seller;
import A_Client.Graphics.Accounts.Roles.Supporter;
import A_Client.Graphics.Menus.AuctionsMenu;
import A_Client.Graphics.Menus.OffersMenu;
import A_Client.Graphics.Menus.ProductsMenu;
import A_Client.Graphics.Models.AuctionCart;
import A_Client.Graphics.Models.OfferCart;
import A_Client.Graphics.Models.ProductCart;
import A_Client.Graphics.Other.PopUp;
import A_Client.Graphics.Pages.Cart;
import A_Client.Graphics.Pages.CeSut;
import A_Client.Graphics.Pages.Login;
import A_Client.Graphics.Pages.ViewAllSupporters;
import A_Client.Graphics.Tools.SceneBuilder;
import MessageFormates.MessageSupplier;
import Structs.MiniAuction;
import Structs.MiniCate;
import Structs.MiniOffer;
import Structs.MiniProduct;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

// MainMenu finished.
public class MainMenu extends Application implements SceneBuilder, Initializable {

    private static Client client;
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

    public static Client getClient() {
        return client;
    }

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCenter(changeable);
        setFilter(filterArea);
        List<MiniCate> allCategories = SendAndReceive.getAllCategories();
        List<MenuItem> collect = allCategories.stream().map(this::getCategorySelection).collect(Collectors.toList());
        category_select.getItems().addAll(FXCollections.observableArrayList(collect));

        gif.getMediaPlayer().play();
        gif.getMediaPlayer().setCycleCount(Integer.MAX_VALUE);
        playMusic("src/main/resources/Graphics/SoundEffect/mainMenu_sound.mp3");

        if (client.getClientInfo().getAccountId() != null) {
            userArea_btn.setDisable(false);
            login_logout_btn.setText("خروج ...");
            login_logout_btn.setOnAction(event -> logout());

            if (client.getClientInfo()
                    .getAccountTy().equals("Customer")) cart_btn.setDisable(false);
        }

//        primaryStage.setOnCloseRequest(event -> SendAndReceive.closeApp());
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
        List<MiniProduct> miniProducts = SendAndReceive.getAllProductsOfCategoryById(category.getCateId());
        setProducts(miniProducts);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    private void logout() {
        client.sendAndReceive(MessageSupplier.RequestType.Logout, Collections.singletonList(client.getClientInfo().getToken()));
        client.getClientInfo().setAccountTy(null);
        client.getClientInfo().setAccountId(null);
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
        List<MiniAuction> miniAuctions = SendAndReceive.getAllAuctions();
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
        setProducts(SendAndReceive.getAllPopularProducts());
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    public void goUserArea() {
        String accountTy = client.getClientInfo().getAccountTy();
        switch (accountTy) {
            case "Manager":
                MainMenu.change(new Manager().sceneBuilder());
                break;
            case "Seller":
                MainMenu.change(new Seller().sceneBuilder());
                break;
            case "Customer":
                MainMenu.change(new Customer().sceneBuilder());
                break;
            case "Supporter":
//                MainMenu.change();
            default:
                return;
        }
        MainMenu.FilterDisable();
        popUp();
        enableBack();
    }

    public void goProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        setProducts(SendAndReceive.getAllProductsPrime());
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
        setProducts(SendAndReceive.getAllProductsPrime());
        MainMenu.change(new ProductsMenu().sceneBuilder());
        enableBack();
    }

    private void setProducts(List<MiniProduct> list) {
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
    }

    private void addFilter_search() {
        String filterStr = searchArea.getText();
        SendAndReceive.addNewFilter(Collections.singletonList(filterStr));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Host__Port: ");
        String[] host_Port = scanner.nextLine().split(" ");

        client = new Client(host_Port[0],
                Integer.parseInt(host_Port[1]));

        SendAndReceive.getToken();
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

    private void setOffers(List<MiniOffer> list) {
        OffersMenu.setList(list);
        OfferCart.setOfferList(list);
    }

    public void viewOffers() {
        setOffers(SendAndReceive.getAllOffers());
        MainMenu.change(new OffersMenu().sceneBuilder());
        enableBack();
    }

    public void viewSupporters(ActionEvent event) {
        MainMenu.change(new ViewAllSupporters().sceneBuilder());
    }
}
