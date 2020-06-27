package Graphics.Menus;

import Graphics.MainMenu;
import Graphics.Models.ProductCart;
import Graphics.Tools.SceneBuilder;
import Model.Models.LogHistory;
import Model.Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogHistoryMenu implements SceneBuilder, Initializable {

    private static List<LogHistory> list = new ArrayList<>();
    private static int pageNum = 1;
    @FXML
    private Button next_btn;
    @FXML
    private Button previous_btn;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/LogHistory/LogHistory.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static void setLogHistoryList(List<LogHistory> logHistoryList) {
        LogHistoryMenu.list = logHistoryList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //?
    }

    public void next() {
        List<Product> list = new ArrayList<>();
        for (int i = (++pageNum - 1) * 9; i < pageNum * 9 && i < list.size(); i++) {
            list.add(list.get(i));
        }
        checkButtons();
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void previous() {
        List<Product> list = new ArrayList<>();
        for (int i = (--pageNum - 1) * 9; i < pageNum * 9 && i < list.size(); i++) {
            list.add(list.get(i));
        }
        checkButtons();
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void checkButtons() {

        if (pageNum == list.size() / 9 + 1) {
            next_btn.setDisable(true);
            return;
        }
        if (pageNum == 1) {
            previous_btn.setDisable(true);
            return;
        }
        next_btn.setDisable(false);
        previous_btn.setDisable(false);
    }
}
