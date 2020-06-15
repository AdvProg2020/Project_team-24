package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class MainMenu extends Application implements SceneBuilder {

    private static Stage primaryStage;
    private static BorderPane center;

    @FXML
    private BorderPane changeable;
    @FXML
    private TextField searchArea;
    @FXML
    private Pane auctionPane;
    @FXML
    private Pane popularsPane;
    @FXML
    private Pane productsPane;
    @FXML
    private Pane aboutPane;

    @Override
    public void start(@NotNull Stage stage) {
        Scene scene = sceneBuilder();
        stage.setResizable(false);
        stage.setTitle("سه سوت");
        stage.setScene(scene);
        stage.show();
        setPrimaryStage(stage);
        System.out.println(changeable);
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

    public void doCeSuT() {

    }

    public void goLogin() {

    }

    public void goAuction() {

    }

    public void goProducts() {

    }

    public void goCart() {

    }

    public void goPopulars() {

    }

    public void goSearch() {

    }

    private void changeSubScene(Scene newScene) {
        //?
    }

    public static void main(String[] args) {
        launch(args);
    }
}
