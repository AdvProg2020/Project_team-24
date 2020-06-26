package Graphics.Lists;

import Graphics.Tools.SceneBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DiscountCodeList implements SceneBuilder, Initializable {
    public void back(ActionEvent event) {
    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\DiscountCodeList\\DiscountCodeList.fxml").toURI().toURL());
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