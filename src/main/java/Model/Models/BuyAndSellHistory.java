package Model.Models;

import java.util.List;

public class BuyAndSellHistory {

    public long historyId;
    private List<LogHistory> logHistoryList;

    public List<LogHistory> getLogHistoryList() {
        return logHistoryList;
    }

    public BuyAndSellHistory(long historyId, List<LogHistory> logHistoryList) {
        this.historyId = historyId;
        this.logHistoryList = logHistoryList;
    }
}
