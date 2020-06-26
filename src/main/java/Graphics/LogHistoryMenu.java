package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogHistoryMenu implements SceneBuilder, Initializable {
    public void nextPage(ActionEvent event) {
    }

    public void previosPage(ActionEvent event) {
    }

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\LogHistory\\LogHistory.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
