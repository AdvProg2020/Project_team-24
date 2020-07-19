package A_Client.Graphics.Models;

import A_Client.Graphics.Pages.PaymentInformation;
import Structs.MiniLogHistory;
import Structs.MiniProductLog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogHistoryCart implements Initializable {

    private static List<MiniLogHistory> logHistoryList = new ArrayList<>();
    private MiniLogHistory logHistory;
    @FXML
    private Label ActionDiscount;
    @FXML
    private Label DiscountCodeDiscount;
    @FXML
    private Label PendingState;
    @FXML
    private Label finalPriceOfAll;
    @FXML
    private Label DateOfPurchase;
    @FXML
    private TableColumn<MiniProductLog, String> finalPrice;
    @FXML
    private TableColumn<MiniProductLog, String> auctionDiscount;
    @FXML
    private TableColumn<MiniProductLog, String> productPrice;
    @FXML
    private TableColumn<MiniProductLog, String> productName;
    @FXML
    private TableView<MiniProductLog> logHistoryTable;

    public static void setLogHistoryList(List<MiniLogHistory> logHistoryList) {
        LogHistoryCart.logHistoryList = logHistoryList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (logHistoryList.isEmpty()) return;
        logHistory = logHistoryList.get(0);
        logHistoryList.remove(0);
        init();
    }

    private void init() {
        PaymentInformation.setTable(logHistoryTable, logHistory, productName, productPrice, auctionDiscount, finalPrice, ActionDiscount, DiscountCodeDiscount, PendingState, finalPriceOfAll, DateOfPurchase);
    }
}
