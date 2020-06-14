package Graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


/**
 * JavaFX App
 */
public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(@NotNull Stage stage) throws IOException {
//        scene =
        stage.setResizable(false);
        stage.setTitle("سه سوت");
        stage.setScene(scene);
        stage.show();
        setPrimaryStage(stage);
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}

