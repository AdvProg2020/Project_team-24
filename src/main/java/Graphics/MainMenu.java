package Graphics;

import Graphics.Models.ProductCart;
import Graphics.Tools.SceneBuilder;
import Model.ModelUnit;
import Model.Models.Product;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainMenu extends Application implements SceneBuilder, Initializable {

    private static Stage primaryStage;
    private static BorderPane center;
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
        ModelUnit.getInstance().preprocess_loadLists();
        setCenter(changeable);
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
    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\Account\\ManagerAccount.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
    ///jafar

    public void doCeSuT() {
        //?
    }

    public void goLogin() {
        MainMenu.getPrimaryStage().setScene(new Login().sceneBuilder());
    }

    public void goCart() {
        MainMenu.change(new Cart().sceneBuilder());
    }

    public void goPopulars() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        List<Product> list = new ArrayList<>(Product.getList());
        list.sort((o1, o2) -> -1 * Long.compare(o1.getNumberOfVisitors(),o2.getNumberOfVisitors()));
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void goAuction() {
        ProductsMenu.setMode(ProductsMenu.Modes.AuctionMode);
        // ?
    }

    public void goProducts() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        List<Product> list = Product.getList();
        ProductsMenu.setList(list);
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void goSearch() {
        ProductsMenu.setMode(ProductsMenu.Modes.NormalMode);
        MainMenu.change(new ProductsMenu().sceneBuilder());
        String filterStr = searchArea.getText();
        List<Product> collect = Product.getList().stream()
                .filter(product -> product.getName().matches(filterStr))
                .collect(Collectors.toList());
        ProductsMenu.setList(collect);
        ProductCart.setProductList(collect);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
