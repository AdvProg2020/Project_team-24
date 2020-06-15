package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ProductsArea extends Application implements SceneBuilder {

    @Override
    public Scene paneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/ProductsArea/ProductsArea.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new ProductsArea().paneBuilder());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
