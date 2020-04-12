package Model.RuntimeData;

import Model.Models.BuyAndSellHistory;

import java.io.File;
import java.util.List;

public class BuyAndSellHistorySource {

    private static File buyAndSellHistoryList_File = new File("src/main/resources/allBuyAndSellHistories");

    private List<BuyAndSellHistory> buyAndSellHistoryList;

    public List<BuyAndSellHistory> getBuyAndSellHistoryList() {
        return buyAndSellHistoryList;
    }

    public BuyAndSellHistorySource(List<BuyAndSellHistory> buyAndSellHistoryList) {
        this.buyAndSellHistoryList = buyAndSellHistoryList;
    }
}
