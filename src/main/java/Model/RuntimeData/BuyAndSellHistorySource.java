package Model.RuntimeData;

import Model.Models.BuyAndSellHistory;

import java.io.File;
import java.util.List;

public class BuyAndSellHistorySource {

    private static File buyAndSellHistoryList_File = new File("src/main/resources/allBuyAndSellHistories");

    private static List<BuyAndSellHistory> buyAndSellHistoryList;

    static {

    }

    public static List<BuyAndSellHistory> getBuyAndSellHistoryList() {
        return buyAndSellHistoryList;
    }
}
