package Graphics.Menus;

import Graphics.MainMenu;
import Graphics.Models.AuctionCart;
import Graphics.Models.ProductCart;
import Graphics.Tools.SceneBuilder;
import Model.Models.Auction;
import Model.Models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AuctionsMenu implements Initializable, SceneBuilder {

    private static List<Auction> list = new ArrayList<>();
    private static int pageNum = 1;
    public MediaView rightGif;
    public MediaView leftGif;
    @FXML
    private Button next_btn;
    @FXML
    private Button previous_btn;

    public static void setList(List<Auction> list) {
        AuctionsMenu.list = list;
    }

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/AuctionMenu/Auctionpage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MediaPlayer value = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\AuctionMenu\\shopFromHome.mp4").toURI().toString()));
        rightGif.setMediaPlayer(value);
        value.setCycleCount(Integer.MAX_VALUE);
        value.play();
        MediaPlayer v = new MediaPlayer(new Media(new File("src\\main\\resources\\Graphics\\AuctionMenu\\shopingBulding.mp4").toURI().toString()));
        leftGif.setMediaPlayer(v);
        v.setCycleCount(Integer.MAX_VALUE);
        v.play();
        MainMenu.playMusic("");
        
    }

    public void nextPage() {
        List<Auction> list = new ArrayList<>();
        for (int i = (++pageNum - 1) * 9; i < pageNum * 9 && i < list.size(); i++) {
            list.add(list.get(i));
        }
        checkButtons();
        AuctionCart.setAuctionList(list);
        MainMenu.change(new AuctionsMenu().sceneBuilder());
    }

    public void previousPage() {
        List<Auction> list = new ArrayList<>();
        for (int i = (--pageNum - 1) * 9; i < pageNum * 9 && i < list.size(); i++) {
            list.add(list.get(i));
        }
        checkButtons();
        AuctionCart.setAuctionList(list);
        MainMenu.change(new AuctionsMenu().sceneBuilder());
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
