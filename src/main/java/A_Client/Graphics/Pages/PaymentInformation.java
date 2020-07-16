package A_Client.Graphics.Pages;

import A_Client.Graphics.MiniModels.Structs.MiniLogHistory;
import A_Client.Graphics.MiniModels.Structs.MiniProductLog;
import A_Client.Graphics.Tools.SceneBuilder;
import Exceptions.FieldDoesNotExistException;
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
import java.util.ResourceBundle;

public class PaymentInformation implements SceneBuilder, Initializable {

    private static MiniLogHistory logHistory;

    public Label ActionDiscount;
    public Label DiscountCodeDiscount;
    public Label PendingState;
    public Label finalPriceOfAll;
    public Label DateOfPurchase;
    public TableColumn<MiniProductLog, String> finalPrice;
    public TableColumn<MiniProductLog, String> auctionDiscount;
    public TableColumn<MiniProductLog, String> productPrice;
    public TableColumn<MiniProductLog, String> productName;
    public TableView<MiniProductLog> logHistoryTable;

    public static void setLogHistory(MiniLogHistory logHistory) {
        PaymentInformation.logHistory = logHistory;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (logHistory == null) return;
        init();
    }

    private void init() {
        setTable(logHistoryTable, logHistory, productName, productPrice, auctionDiscount, finalPrice, ActionDiscount, DiscountCodeDiscount, PendingState, finalPriceOfAll, DateOfPurchase);
    }

    public static void setTable(TableView<MiniProductLog> logHistoryTable, MiniLogHistory logHistory, TableColumn<MiniProductLog, String> productName, TableColumn<MiniProductLog, String> productPrice, TableColumn<MiniProductLog, String> auctionDiscount, TableColumn<MiniProductLog, String> finalPrice, Label actionDiscount, Label discountCodeDiscount, Label pendingState, Label finalPriceOfAll, Label dateOfPurchase) {
        logHistoryTable.setItems(FXCollections.observableList(logHistory.getProductLogList()));
        productName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProductName()));
        productPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPrice() + ""));
        auctionDiscount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAuctionDiscount() + ""));
        finalPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFinalPrice() + ""));
        actionDiscount.setText(logHistory.getAuctionDiscount() + "");
        discountCodeDiscount.setText(logHistory.getDiscountAmount() + "");
        pendingState.setText("در حال ارسال");
        finalPriceOfAll.setText(logHistory.getFinalAmount() + "");

        try {
            dateOfPurchase.setText(logHistory.getFieldList().getFieldByName("date") + "");
        } catch (FieldDoesNotExistException e) {
            e.printStackTrace();
        }
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

