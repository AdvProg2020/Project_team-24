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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentInformation implements SceneBuilder, Initializable {

    private static LogHistory logHistory;

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

    public static void setLogHistory(LogHistory logHistory) {
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

    static void setTable(TableView<ProductLog> logHistoryTable, LogHistory logHistory, TableColumn<ProductLog, String> productName, TableColumn<ProductLog, String> productPrice, TableColumn<ProductLog, String> auctionDiscount, TableColumn<ProductLog, String> finalPrice, Label actionDiscount, Label discountCodeDiscount, Label pendingState, Label finalPriceOfAll, Label dateOfPurchase) {
        logHistoryTable.setItems(FXCollections.observableList(logHistory.getProductLogList()));
        productName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProductName()));
        productPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPrice() + ""));
        auctionDiscount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAuctionDiscount() + ""));
        finalPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFinalPrice() + ""));
        actionDiscount.setText(logHistory.getAuctionDiscount()+"");
        discountCodeDiscount.setText(logHistory.getDiscountAmount()+"");
        pendingState.setText("در حال ارسال");
        finalPriceOfAll.setText(logHistory.getFinalAmount()+"");
        dateOfPurchase.setText(LocalDate.now()+"");
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

