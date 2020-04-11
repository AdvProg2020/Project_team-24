package Model.Models;

import java.util.List;

public class BuyAndSellHistory {

    public long historyId;
    private List<LogHistory> logHistories;

    public List<LogHistory> getLogHistories() {
        return logHistories;
    }

    public BuyAndSellHistory(long historyId, List<LogHistory> logHistories) {
        this.historyId = historyId;
        this.logHistories = logHistories;
    }
}
