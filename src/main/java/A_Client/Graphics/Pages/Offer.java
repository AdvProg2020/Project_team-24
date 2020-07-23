package A_Client.Graphics.Pages;

import A_Client.Client.Client;
import A_Client.Client.SendAndReceive.SendAndReceive;
import Structs.MiniOffer;
import Structs.MiniProduct;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Offer implements Initializable {
    private static MiniProduct productObject;
    private final Client client = SendAndReceive.getClient();
    public TextField price;
    public TableColumn<String,String> usernameColumn;
    public TableColumn<String,String> priceColumn;
    public TableView<String> offerTable;
    public ImageView product_image;
    public Label productName;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productObject = SendAndReceive.getProductById(client.getClientInfo().getProductId());

        if (productObject == null) return;
        
        if (productObject.getMediasId() != null) {
            Image image = SendAndReceive
                    .getImageById(productObject.getMediasId());

            if (image != null)
                product_image.setImage(image);
        }
        setName();
        setTable(offerTable,usernameColumn,priceColumn);

    }

    public void submitOffer() {
        String newPrice = price.getText();
        String username = SendAndReceive.getAccountById(client.getClientInfo().getAccountId()).getUsername();
        SendAndReceive.addNewBuyerToOfferById(Arrays.asList(productObject.getOfferId(),username,newPrice));

    }
    public void setName(){
        productName.setText(productObject.getProductName());
    }
    public void setTable(TableView<String> offerTable, TableColumn<String,String> usernameColumn, TableColumn<String,String> priceColumn ){
        MiniOffer offerById = SendAndReceive.getOfferById(productObject.getOfferId());
        HashMap<String, Double> auctioneersPrices = offerById.getAuctioneersPrices();
        offerTable.setItems(FXCollections.observableList(auctioneersPrices.entrySet()
                .stream().map(entry -> entry.getKey() + "#" + entry.getValue()).collect(Collectors.toList())));
        usernameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().split("#")[0]));
        priceColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().split("#")[1]));

    }

}
