package Graphics.Menus;

import Graphics.MainMenu;
import Graphics.Models.ProductCart;
import Graphics.Sort;
import Graphics.Tools.SceneBuilder;
import Model.Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsMenu implements Initializable, SceneBuilder {

    private static List<Product> list = new ArrayList<>();
    private static Modes mode = Modes.NormalMode;
    private static int pageNum = 1;
    @FXML
    private Button next_btn;
    @FXML
    private Button previous_btn;
    @FXML
    private ImageView auctionImage_01;
    @FXML
    private ImageView auctionImage_02;
    @FXML
    private Label title;

    public static void setMode(Modes mode) {
        ProductsMenu.mode = mode;
    }

    public static void setList(List<Product> list) {
        ProductsMenu.list = list;
    }

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/ProductsMenu/ProductsMenu.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (mode == Modes.NormalMode)
            setNormalMode();
        else if (mode == Modes.AuctionMode) setAuctionMode();
        MainMenu.FilterEnable();
    }

    @Override
    protected void finalize() {
        MainMenu.FilterDisable();
    }

    public void next() {
        List<Product> list = new ArrayList<>();
        for (int i = (++pageNum - 1) * 9; i < pageNum * 9 && i < ProductsMenu.list.size(); i++) {
            list.add(ProductsMenu.list.get(i));
        }
        checkButtons();
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    public void previous() {
        List<Product> list = new ArrayList<>();
        for (int i = (--pageNum - 1) * 9; i < pageNum * 9 && i < ProductsMenu.list.size(); i++) {
            list.add(ProductsMenu.list.get(i));
        }
        checkButtons();
        ProductCart.setProductList(list);
        MainMenu.change(new ProductsMenu().sceneBuilder());
    }

    private void setAuctionMode() {
        auctionImage_01.setVisible(true);
        auctionImage_02.setVisible(true);
        title.setText("صفحه حراج");
        MainMenu.playMusic("src/main/resources/Graphics/SoundEffect/Adventure - AShamaluevMusic.mp3");
    }

    private void setNormalMode() {
        auctionImage_01.setVisible(false);
        auctionImage_02.setVisible(false);
        title.setText("صفحه محصولات");
        MainMenu.playMusic("src/main/resources/Graphics/SoundEffect/Motivated - AShamaluevMusic.mp3");
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

    public enum Modes {
        AuctionMode, NormalMode
    }
}
