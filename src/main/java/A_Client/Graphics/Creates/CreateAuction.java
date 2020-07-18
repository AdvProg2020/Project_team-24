package A_Client.Graphics.Creates;

import A_Client.Client.Client;
import MessageFormates.MessageSupplier;
import A_Client.Graphics.MainMenu;
import A_Client.Graphics.MiniModels.Structs.MiniProduct;
import A_Client.Graphics.Tools.SceneBuilder;
import A_Client.JsonHandler.JsonHandler;
import B_Server.Controller.ControllerUnit;
import B_Server.Model.Models.Auction;
import com.gilecode.yagson.YaGson;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CreateAuction implements SceneBuilder, Initializable {

    private final Client client = MainMenu.getClient();
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
        if (mode == Mode.New)
            init_newMode();
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
        List<String> answers = client.sendAndReceive(
                MessageSupplier.RequestType.GetAllProducts, Collections.singletonList(client.getClientInfo().getToken())
        );
        List<MiniProduct> miniProducts = new JsonHandler<MiniProduct>()
                .JsonsToObjectList(answers, MiniProduct.class);

        List<CheckMenuItem> checkMenuItems = miniProducts.stream().filter(product -> product.getAuctionId() == null)
                .map(product -> product.getProductName() + " " + product.getProductId())
                .map(CheckMenuItem::new).collect(Collectors.toList());

        selected_products.getItems().addAll(checkMenuItems);
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

        if (mode == Mode.New)
            submit_newMode(start, end, name, percent, limit);
        if (mode == Mode.Edit)
            submit_edit();

        goMainMenu();
    }

    private void submit_edit() {
        RequestForEdit("auctionName", auction_name.getText());
        RequestForEdit("start", start_time.getText());
        RequestForEdit("end", end_time.getText());
        RequestForEdit("discountMaxAmount", auction_limit.getText());
        RequestForEdit("discountPercent", auction_percent.getText());
    }

    private void RequestForEdit(String fieldName, String fieldValue) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(client.getClientInfo().getToken());
        objects.add(client.getClientInfo().getAccountId());
        objects.add(fieldName);
        objects.add(fieldValue);
        objects.add("edit Auction");
        client.sendAndReceive(MessageSupplier.RequestType.EditFieldOfAuction, objects);
    }

    private void submit_newMode(String start, String end, String name, String percent, String limit) {
        List<String> ids = selected_products.getItems().stream()
                .filter(menuItem -> ((CheckMenuItem) menuItem).isSelected())
                .map(menuItem -> {

                    String[] units = menuItem.getText().split(" ");
                    return units[units.length - 1];

                }).collect(Collectors.toList());

        requestForAdd(start, end, name, percent, limit, ids);

//        Auction auction = sellerController.addOff(name, start, end, percent, limit);
//        sellerController.addProductsToAuction(auction, ids);
//        sellerController.sendRequest(auction, "new Auction", "new");
    }

    private void requestForAdd(String start, String end, String name, String percent, String limit, List<String> ids) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add(client.getClientInfo().getToken());
        objects.add(name);
        objects.add(start);
        objects.add(end);
        objects.add(percent);
        objects.add(limit);
        objects.add(new YaGson().toJson(ids));
        client.sendAndReceive(MessageSupplier.RequestType.EditFieldOfAuction, objects);
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
                )).play()
        ).start();
    }

    public enum Mode {
        Edit, New
    }
}
