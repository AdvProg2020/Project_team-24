package Model.Models;

import Model.ModelUnit.LogHistory.LogHistory;

import java.util.List;

public class BuyAndSellHistory {

    protected int historyId;
    protected List<LogHistory> logHistories;

    //

    public BuyAndSellHistory(int historyId, List<LogHistory> logHistories) {
        this.historyId = historyId;
        this.logHistories = logHistories;
    }
}
