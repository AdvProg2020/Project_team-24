package Graphics.Creates;

import Controller.ControllerUnit;
import Controller.Controllers.SellerController;
import Exceptions.InvalidInputByUserException;
import Exceptions.ProductCantBeInMoreThanOneAuction;
import Exceptions.ProductDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Accounts.Seller;
import Model.Models.Auction;
import Model.Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CreateAuction implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private Seller seller = (Seller) ControllerUnit.getInstance().getAccount();

    @FXML
    private TextField start_time;
    @FXML
    private TextField end_time;
    @FXML
    private TextField auction_name;
    @FXML
    private TextField auction_percent;
    @FXML
    private TextField auction_limit;
    @FXML
    private MenuButton selected_products;
    @FXML
    private Button submit_btn;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/CreatePages/CreateAuction/CreateAuction.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<CheckMenuItem> checkMenuItems = sellerController.showProducts().stream()
                    .map(product -> product.getName() + " " + product.getId())
                    .map(CheckMenuItem::new).collect(Collectors.toList());

            selected_products.getItems().addAll(checkMenuItems);
        } catch (ProductDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    public void submit() {

        String start = start_time.getText();
        String end = end_time.getText();
        String name = auction_name.getText();
        String percent = auction_percent.getText();
        String limit = auction_limit.getText();

        reset();

        if (start.isEmpty() || end.isEmpty() || name.isEmpty() || percent.isEmpty() || limit.isEmpty()) {
            mustBeFilled();
            return;
        }

        List<String> ids = selected_products.getItems().stream()
                .filter(menuItem -> ((CheckMenuItem) menuItem).isSelected())
                .map(menuItem -> {

                    String[] units = menuItem.getText().split(" ");
                    return units[units.length - 1];

                }).collect(Collectors.toList());

        try {
            Auction auction = sellerController.addOff(name, start, end, percent, limit);
            sellerController.addProductsToAuction(auction, ids);
            sellerController.sendRequest(auction, "new Auction", "new");
        } catch (InvalidInputByUserException | ProductCantBeInMoreThanOneAuction | ProductDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private void reset() {
        submit_btn.setTooltip(null);
        submit_btn.setStyle("-fx-border-color: white;");
    }

    private void mustBeFilled() {
        Tooltip mustFilled = new Tooltip();
        mustFilled.setText("این فیلد را باید پر کنید");
        mustFilled.setStyle("-fx-background-color: #C6C6C6;-fx-text-fill: #bf2021;");
        submit_btn.setTooltip(mustFilled);
        submit_btn.setStyle("-fx-border-color: #bf2021;-fx-border-width: 2px");
        failSound();
    }

    private void failSound() {
        new Thread(() -> new MediaPlayer(
                new Media(
                        new File("src/main/resources/Graphics/SoundEffect/failSound.mp3").toURI().toString()
                )).play()).start();
    }
}
