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

    private static Scene scene;

    @Override
    public void start(@NotNull Stage stage) throws IOException {
        scene = new Scene(loadFXML("Graphics\\resources\\MainMenu\\MainMenu"));
        stage.setTitle("سه سوت");
        stage.setScene(scene);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader( Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

//    public static void main(String[] args) {
//        launch();
//    }
}

