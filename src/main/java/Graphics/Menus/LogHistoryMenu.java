package Graphics.Menus;

import Graphics.Tools.SceneBuilder;
import Model.Models.LogHistory;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogHistoryMenu implements SceneBuilder, Initializable {

    private static List<LogHistory> logHistoryList = new ArrayList<>();

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src\\main\\java\\Graphics\\Menus\\LogHistoryMenu.java").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static void setLogHistoryList(List<LogHistory> logHistoryList) {
        LogHistoryMenu.logHistoryList = logHistoryList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void nextPage(ActionEvent event) {
    }

    public void previousPage(ActionEvent event) {
    }
}
