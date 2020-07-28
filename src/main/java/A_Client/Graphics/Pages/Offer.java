package A_Client.Graphics.Pages;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import A_Client.Graphics.Tools.SceneBuilder;
import Structs.MiniAccount;
import Structs.MiniOffer;
import Structs.MiniProduct;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Offer implements Initializable, SceneBuilder {

    private static MiniProduct productObject;
    private final Client client = SendAndReceive.getClient();

    @FXML
    private Button submit_btn;
    @FXML
    private TextField price;
    @FXML
    private TableColumn<String, String> usernameColumn;
    @FXML
    private TableColumn<String, String> priceColumn;
    @FXML
    private TableView<String> offerTable;
    @FXML
    private ImageView product_image;
    @FXML
    private Label productName;

    @Override
    public Scene sceneBuilder() {
        try {
            return FXMLLoader.load(new File("src\\main\\resources\\Graphics\\Offer\\OfferPage.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productObject = SendAndReceive.getProductById(client.getClientInfo().getProductId());

        if (productObject == null) return;

        if (client.getClientInfo().getAccountTy().equals("Customer")) {
            submit_btn.setDisable(false);
            price.setDisable(false);
        }

        if (productObject.getMediasId().equals("0")) {
            Image image = SendAndReceive
                    .getImageById(productObject.getMediasId());

            if (image != null)
                product_image.setImage(image);
        }
        setName();
        setTable(offerTable, usernameColumn, priceColumn);

    }

    public void submitOffer() {
        String accountId = client.getClientInfo().getAccountId();
        MiniAccount accountById = SendAndReceive.getAccountById(accountId);
        if (accountById != null) {
            String username = accountById.getUsername();
            SendAndReceive.addNewBuyerToOfferById(Arrays.asList(productObject.getOfferId(), username, price.getText()));
        }
    }

    public void setName() {
        productName.setText(productObject.getProductName());
    }

    public void setTable(TableView<String> offerTable, TableColumn<String, String> usernameColumn, TableColumn<String, String> priceColumn) {
        MiniOffer offerById = SendAndReceive.getOfferById(productObject.getOfferId());
        Map<String, Double> auctioneersPrices = offerById.getAuctioneersPrices();
        offerTable.setItems(FXCollections.observableList(auctioneersPrices.entrySet()
                .stream().map(entry -> entry.getKey() + "#" + entry.getValue()).collect(Collectors.toList())));
        usernameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().split("#")[0]));
        priceColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().split("#")[1]));

    }

}
