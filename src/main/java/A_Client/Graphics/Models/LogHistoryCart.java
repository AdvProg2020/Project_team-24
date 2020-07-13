package A_Client.Graphics.Models;

import A_Client.Graphics.Pages.PaymentInformation;
import B_Server.Model.Models.LogHistory;
import B_Server.Model.Models.Structs.ProductLog;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogHistoryCart implements Initializable {

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

    public static void setLogHistoryList(List<LogHistory> logHistoryList) {
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
