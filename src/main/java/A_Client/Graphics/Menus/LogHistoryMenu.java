package A_Client.Graphics.Menus;

import Structs.MiniLogHistory;
import A_Client.Graphics.Models.LogHistoryCart;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.Graphics.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogHistoryMenu implements SceneBuilder, Initializable {

    private static List<MiniLogHistory> list = new ArrayList<>();
    private static int pageNum = 1;
    @FXML
    private MediaView leftGif;
    @FXML
    private MediaView rightGif;
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

    public static void setLogHistoryList(List<MiniLogHistory> logHistoryList) {
        LogHistoryMenu.list = logHistoryList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MediaPlayer value = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\LogHistory\\loghistory.mp4").toURI().toString()));
        rightGif.setMediaPlayer(value);
        value.setCycleCount(Integer.MAX_VALUE);
        value.play();
        MediaPlayer v = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\LogHistory\\shopping.mp4").toURI().toString()));
        leftGif.setMediaPlayer(v);
        v.setCycleCount(Integer.MAX_VALUE);
        v.play();

    }

    public void next() {
        List<MiniLogHistory> list = new ArrayList<>();
        for (int i = (++pageNum - 1) * 9; i < pageNum * 9 && i < LogHistoryMenu.list.size(); i++)
            list.add(LogHistoryMenu.list.get(i));
        checkButtons();
        LogHistoryCart.setLogHistoryList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void previous() {
        List<MiniLogHistory> list = new ArrayList<>();
        for (int i = (--pageNum - 1) * 9; i < pageNum * 9 && i < LogHistoryMenu.list.size(); i++)
            list.add(LogHistoryMenu.list.get(i));
        checkButtons();
        LogHistoryCart.setLogHistoryList(list);
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
