package Graphics.Creates;

import Graphics.Tools.SceneBuilder;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateDiscountCode implements SceneBuilder, Initializable {
    public TextField startDate;
    public TextField endDate;
    public TextField Percent;
    public Button registerData;
    public TextField MaxAmount;

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\CreatePages\\CreateDiscountCode\\CreateDiscountCode.fxml").toURI().toURL());
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
