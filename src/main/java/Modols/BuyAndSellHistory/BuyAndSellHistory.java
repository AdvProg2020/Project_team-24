package Modols.BuyAndSellHistory;

import Modols.LogHistory.LogHistory;

import java.util.List;

public class BuyAndSellHistory {

    protected int historyId;
    protected List<LogHistory> logs;

    //

    public BuyAndSellHistory(int historyId, List<LogHistory> logs) {
        this.historyId = historyId;
        this.logs = logs;
    }
}
