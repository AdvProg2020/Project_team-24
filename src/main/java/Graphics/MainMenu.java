package Graphics;

import Graphics.Tools.SceneBuilder;
import Model.ModelUnit;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu extends Application implements SceneBuilder, Initializable {

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
        ModelUnit.getInstance().preprocess_loadLists();
        setCenter(changeable);
        gif.getMediaPlayer().play();
        gif.getMediaPlayer().setAutoPlay(true);
        gif.getMediaPlayer().setCycleCount(10000);
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
            return FXMLLoader.load(new File("src/main/resources/Graphics/CreatePages/CreateProduct/CreateProduct.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
    ///jafar

    public void doCeSuT() {

    }

    public void goLogin() {
        MainMenu.getPrimaryStage().setScene(new Login().sceneBuilder());
    }

    public void goPopulars() {

    }

    public void goAuction() {

    }

    public void goProducts() {
    }

    public void goCart() {

    }

    public void goSearch() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}
