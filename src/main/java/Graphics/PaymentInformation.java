package Graphics;

import Controller.ControllerUnit;
import Exceptions.LogHistoryDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Accounts.Customer;
import Model.Models.LogHistory;
import Model.Models.Structs.ProductLog;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentInformation implements SceneBuilder, Initializable {

    private static List<LogHistory> logHistoryList = new ArrayList<>();
    private LogHistory logHistory;

    public Label ActionDiscount;
    public Label DiscountCodeDiscount;
    public Label PendingState;
    public Label finalPriceOfAll;
    public Label DateOfPurchase;
    public TableColumn<ProductLog,String> finalPrice;
    public TableColumn<ProductLog,String> auctionDiscount;
    public TableColumn<ProductLog,String> productPrice;
    public TableColumn<ProductLog,String> productName;
    public TableView<ProductLog> logHistoryTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (logHistoryList.isEmpty()) return;
        logHistory = logHistoryList.get(0);
        logHistoryList.remove(0);
        init();
    }

    private void init() {
        logHistoryTable.setItems(FXCollections.observableList(logHistory.getProductLogList()));
        productName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProductName()));
        productPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPrice() + ""));
        auctionDiscount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAuctionDiscount() + ""));
        finalPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFinalPrice() + ""));
        ActionDiscount.setText(logHistory.getAuctionDiscount()+"");
        DiscountCodeDiscount.setText(logHistory.getDiscountAmount()+"");

    }

    @Override
    public Scene sceneBuilder() {

        try {
            return FXMLLoader.load(new File("src/main/resources/Graphics/PaymentInformation/PaymentInformation.fxml").toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }


}

