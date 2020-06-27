package Graphics;

import Graphics.Tools.SceneBuilder;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUp implements Initializable, SceneBuilder {

    public WebView webView;
    public Button viewPage;
    public WebEngine engine;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/PopUp/PopUp.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
    }

    public void setViewPage() {
        webView.setVisible(true);
        engine.load("https://www.zoomit.ir/2018/12/3/309989/complete-guide-buying-best-sms-panel-and-sms-advertising");
        viewPage.setVisible(false);
    }
}
