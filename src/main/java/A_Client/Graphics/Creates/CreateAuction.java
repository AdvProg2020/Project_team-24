package A_Client.Graphics.Creates;

import B_Server.Controller.ControllerUnit;
import B_Server.Controller.Controllers.SellerController;
import Exceptions.*;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.Tools.SceneBuilder;
import B_Server.Model.Models.Accounts.Seller;
import B_Server.Model.Models.Auction;
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
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreateAuction implements SceneBuilder, Initializable {

    private static SellerController sellerController = SellerController.getInstance();
    private static Mode mode = Mode.New;

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

    public static void setMode(Mode mode) {
        CreateAuction.mode = mode;
    }

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
        if (mode == Mode.New) init_newMode();
        else
            init_editMode();
    }

    private void init_editMode() {
        Auction auction = ControllerUnit.getInstance().getAuction();
        start_time.setText(auction.getStart().toString());
        end_time.setText(auction.getEnd().toString());
        auction_name.setText(auction.getName());
        auction_percent.setText(auction.getDiscount().getPercent() + "");
        auction_limit.setText(auction.getDiscount().getAmount() + "");
    }

    private void init_newMode() {
        try {
            List<CheckMenuItem> checkMenuItems = sellerController.showProducts().stream()
                    .filter(product -> product.getAuction() == null)
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

        try {
            if (mode == Mode.New)
                submit_newMode(start, end, name, percent, limit);
            if (mode == Mode.Edit)
                submit_edit();
            goMainMenu();
        } catch (InvalidInputByUserException | ProductCantBeInMoreThanOneAuction | ProductDoesNotExistException | AuctionDoesNotExistException | FieldDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    private void submit_edit() throws AuctionDoesNotExistException, FieldDoesNotExistException, InvalidInputByUserException {
        Seller seller = (Seller) ControllerUnit.getInstance().getAccount();
        sellerController.editAuction(seller.getId() + "", "auctionName", auction_name.getText(), "edit Auction");
        sellerController.editAuction(seller.getId() + "", "start", start_time.getText(), "edit Auction");
        sellerController.editAuction(seller.getId() + "", "end", end_time.getText(), "edit Auction");
        sellerController.editAuction(seller.getId() + "", "discountMaxAmount", auction_limit.getText(), "edit Auction");
        sellerController.editAuction(seller.getId() + "", "discountPercent", auction_percent.getText(), "edit Auction");
    }

    private void submit_newMode(String start, String end, String name, String percent, String limit) throws InvalidInputByUserException, ProductDoesNotExistException, ProductCantBeInMoreThanOneAuction {
        List<String> ids = selected_products.getItems().stream()
                .filter(menuItem -> ((CheckMenuItem) menuItem).isSelected())
                .map(menuItem -> {

                    String[] units = menuItem.getText().split(" ");
                    return units[units.length - 1];

                }).collect(Collectors.toList());

        Auction auction = sellerController.addOff(name, start, end, percent, limit);
        sellerController.addProductsToAuction(auction, ids);
        sellerController.sendRequest(auction, "new Auction", "new");
    }

    private void goMainMenu() {
        MainMenu.getPrimaryStage().setScene(new MainMenu().sceneBuilder());
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

    public enum Mode {
        Edit, New
    }
}
