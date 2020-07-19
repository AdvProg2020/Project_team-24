package A_Client.Graphics.Models;

import Structs.MiniLogHistory;
import Structs.MiniProductLog;
import A_Client.Graphics.Pages.PaymentInformation;
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
    public Label ActionDiscount;
    public Label DiscountCodeDiscount;
    public Label PendingState;
    public Label finalPriceOfAll;
    public Label DateOfPurchase;
    public TableColumn<MiniProductLog,String> finalPrice;
    public TableColumn<MiniProductLog,String> auctionDiscount;
    public TableColumn<MiniProductLog,String> productPrice;
    public TableColumn<MiniProductLog,String> productName;
    public TableView<MiniProductLog> logHistoryTable;

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
