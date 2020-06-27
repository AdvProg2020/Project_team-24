package Graphics;

import Controller.Controllers.BuyerController;
import Controller.Controllers.SellerController;
import Exceptions.LogHistoryDoesNotExistException;
import Graphics.Tools.SceneBuilder;
import Model.Models.Accounts.Customer;
import Model.Models.Accounts.Seller;
import Model.Models.LogHistory;
import Model.Models.Product;
import Model.Models.Structs.ProductLog;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
        logHistoryTable.setItems(FXCollections.observableList(logHistory.getProductLogList()));
        productName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProductName()));
        productPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPrice() + ""));
        auctionDiscount.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAuctionDiscount() + ""));
        finalPrice.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFinalPrice() + ""));
    }
}
